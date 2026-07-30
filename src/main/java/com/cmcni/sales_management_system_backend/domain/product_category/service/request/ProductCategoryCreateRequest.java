package com.cmcni.sales_management_system_backend.domain.product_category.service.request;

import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductCategoryCreateRequest {
    private final Long parentId;
    private final String name;

    public ProductCategory toRootCategory() {
        return new ProductCategory(name);
    }

    public ProductCategory toChildrenCategory(ProductCategory parent) {
        return new ProductCategory(name, parent);
    }
}
