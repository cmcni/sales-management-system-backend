package com.cmcni.sales_management_system_backend.domain.product_model.service.response;

import com.cmcni.sales_management_system_backend.domain.product_model.entity.ProductModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductModelFindResponse {
    private final Long id;
    private final String name;

    public static ProductModelFindResponse from(ProductModel productModel) {
        return new ProductModelFindResponse(
                productModel.getId(),
                productModel.getName()
        );
    }
}
