package com.cmcni.sales_management_system_backend.domain.product_category.service.response;

import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_model.service.response.ProductModelFindResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ProductCategoryFindResponse {

    private final Long id;
    private final String name;
    private final Integer depth;
    private final Integer sortOrder;
    private final Boolean isActive;
    private final List<ProductCategoryFindResponse> children;
    private final List<ProductModelFindResponse> productModels;

    // 제품이 참조하는 카테고리는 최하위(leaf) 카테고리이므로, 부모 방향으로 거슬러 올라가며 전체 경로를 트리 형태로 감싸서 반환한다.
    public static ProductCategoryFindResponse from(ProductCategory productCategory) {
        ProductCategoryFindResponse response = from(productCategory, List.of());

        ProductCategory parent = productCategory.getParent();
        while (parent != null) {
            response = from(parent, List.of(response));
            parent = parent.getParent();
        }

        return response;
    }

    public static ProductCategoryFindResponse from(ProductCategory productCategory, List<ProductCategoryFindResponse> children) {
        return from(productCategory, children, List.of());
    }

    public static ProductCategoryFindResponse from(ProductCategory productCategory, List<ProductCategoryFindResponse> children, List<ProductModelFindResponse> productModels) {
        return new ProductCategoryFindResponse(
                productCategory.getId(),
                productCategory.getName(),
                productCategory.getDepth(),
                productCategory.getSortOrder(),
                productCategory.getIsActive(),
                children,
                productModels
        );
    }
}
