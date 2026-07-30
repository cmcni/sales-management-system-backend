package com.cmcni.sales_management_system_backend.domain.product_category.controller.request_form;

import com.cmcni.sales_management_system_backend.domain.product_category.service.request.ProductCategoryCreateRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductCategoryCreateRequestForm {
    private final Long parentId;
    @NotBlank(message = "product category name is cannot be null")
    private final String name;

    public ProductCategoryCreateRequest toRequest() {
        return new ProductCategoryCreateRequest(
                parentId, name
        );
    }
}
