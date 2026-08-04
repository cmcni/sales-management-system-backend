package com.cmcni.sales_management_system_backend.domain.product.service;

import com.cmcni.sales_management_system_backend.common.exception.CustomErrorCode;
import com.cmcni.sales_management_system_backend.common.exception.CustomException;
import com.cmcni.sales_management_system_backend.domain.product.repository.ProductRepository;
import com.cmcni.sales_management_system_backend.domain.product.service.request.ProductCreateRequest;
import com.cmcni.sales_management_system_backend.domain.product.service.request.ProductExcelExportRequest;
import com.cmcni.sales_management_system_backend.domain.product.service.request.ProductSearchRequest;
import com.cmcni.sales_management_system_backend.domain.product.service.response.ProductExcelExportResponse;
import com.cmcni.sales_management_system_backend.domain.product.service.response.ProductSearchResponse;
import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_category.repository.ProductCategoryRepository;
import com.cmcni.sales_management_system_backend.domain.product_model.entity.ProductModel;
import com.cmcni.sales_management_system_backend.domain.product_model.repository.ProductModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductModelRepository productModelRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public void create(ProductCreateRequest productCreateRequest) {
        ProductModel productModel = null;
        if (productCreateRequest.getProductModelId() != null) {
            productModel = productModelRepository.findById(productCreateRequest.getProductModelId()).orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_MODEL_IS_NOT_EXIST));    
        }
        ProductCategory productCategory = productCategoryRepository.findById(productCreateRequest.getProductCategoryId()).orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_CATEGORY_IS_NOT_EXIST));
        productRepository.save(productCreateRequest.toProduct(productModel, productCategory));
    }

    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductSearchResponse> search(ProductSearchRequest productSearchRequest, Pageable pageable) {
        return productRepository.search(productSearchRequest, pageable).map(ProductSearchResponse::from);
    }

    @Override
    public Page<ProductExcelExportResponse> excelExport(ProductExcelExportRequest productExcelExportRequest, Pageable pageable) {
        return productRepository.excelExport(productExcelExportRequest, pageable).map(ProductExcelExportResponse::from);
    }
}
