package com.cmcni.sales_management_system_backend.domain.product_model.service.request;

import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_model.entity.ProductModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductModelCreateRequest {
    private final ProductCategory productCategory;
    private final String name;

    public ProductModel toProductModel() {
        return new ProductModel(
                productCategory,
                name
        );
    }
}
