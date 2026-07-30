package com.cmcni.sales_management_system_backend.domain.product_model.controller.request_form;

import com.cmcni.sales_management_system_backend.domain.product_model.service.request.ProductModelCreateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductModelCreateRequestForm {
    private final String name;

    public ProductModelCreateRequest toRequest() {
        return new ProductModelCreateRequest(name);
    }
}
