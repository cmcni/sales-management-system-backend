package com.cmcni.sales_management_system_backend.domain.product_category.service;

import com.cmcni.sales_management_system_backend.common.exception.CustomErrorCode;
import com.cmcni.sales_management_system_backend.common.exception.CustomException;
import com.cmcni.sales_management_system_backend.domain.product.entity.Product;
import com.cmcni.sales_management_system_backend.domain.product.repository.ProductRepository;
import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_category.repository.ProductCategoryRepository;
import com.cmcni.sales_management_system_backend.domain.product_category.service.request.ProductCategoryCreateRequest;
import com.cmcni.sales_management_system_backend.domain.product_category.service.response.ProductCategoryFindResponse;
import com.cmcni.sales_management_system_backend.domain.product_model.service.response.ProductModelFindResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductCategoryFindResponse> create(ProductCategoryCreateRequest productCategoryCreateRequest) {
        ProductCategory category;

        final boolean IS_ROOT_CATEGORY = productCategoryCreateRequest.getParentId() == null;
        if (IS_ROOT_CATEGORY) {
            category = productCategoryCreateRequest.toRootCategory();
        } else {
            ProductCategory parent = productCategoryRepository.findById(productCategoryCreateRequest.getParentId())
                    .orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_CATEGORY_PARENT_IS_NOT_EXIST));
            category = productCategoryCreateRequest.toChildrenCategory(parent);
        }

        productCategoryRepository.save(category);
        return findAll();
    }

    @Override
    public List<ProductCategoryFindResponse> findAll() {
        List<ProductCategory> categories = productCategoryRepository.findAll();

        // parent의 id는 프록시 초기화 없이도 조회 가능하므로, 부모 id 기준으로 자식들을 그룹핑한다.
        Map<Long, List<ProductCategory>> childrenByParentId = categories.stream()
                .filter(category -> !category.isRoot())
                .collect(Collectors.groupingBy(category -> category.getParent().getId()));

        Map<Long, List<ProductModelFindResponse>> productModelsByCategoryId = groupProductModelsByCategoryId();

        return categories.stream()
                .filter(ProductCategory::isRoot)
                .sorted(Comparator.comparing(ProductCategory::getSortOrder))
                .map(root -> toTreeResponse(root, childrenByParentId, productModelsByCategoryId))
                .toList();
    }

    // 카테고리별로 직접 연결된(=해당 카테고리를 참조하는 Product들의) model을 중복 없이 모은다.
    private Map<Long, List<ProductModelFindResponse>> groupProductModelsByCategoryId() {
        return productRepository.findAllWithModel().stream()
                .collect(Collectors.groupingBy(
                        product -> product.getCategory().getId(),
                        Collectors.collectingAndThen(
                                Collectors.toMap(
                                        product -> product.getModel().getId(),
                                        Product::getModel,
                                        (existing, replacement) -> existing,
                                        LinkedHashMap::new
                                ),
                                modelsById -> modelsById.values().stream().map(ProductModelFindResponse::from).toList()
                        )
                ));
    }

    @Override
    public ProductCategory findById(Long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId).orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_CATEGORY_IS_NOT_EXIST));
    }

    private ProductCategoryFindResponse toTreeResponse(ProductCategory category, Map<Long, List<ProductCategory>> childrenByParentId, Map<Long, List<ProductModelFindResponse>> productModelsByCategoryId) {
        List<ProductCategoryFindResponse> children = childrenByParentId
                .getOrDefault(category.getId(), List.of())
                .stream()
                .sorted(Comparator.comparing(ProductCategory::getSortOrder))
                .map(child -> toTreeResponse(child, childrenByParentId, productModelsByCategoryId))
                .toList();

        List<ProductModelFindResponse> productModels = productModelsByCategoryId.getOrDefault(category.getId(), List.of());

        return ProductCategoryFindResponse.from(category, children, productModels);
    }
}
