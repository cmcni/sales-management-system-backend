package com.cmcni.sales_management_system_backend.domain.product_model.controller.request_form;

import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_model.service.request.ProductModelCreateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductModelCreateRequestForm {
    private final Long productCategoryId;
    private final String name;

    public ProductModelCreateRequest toRequest(ProductCategory productCategory) {
        return new ProductModelCreateRequest(
                productCategory, name
        );
    }
}
