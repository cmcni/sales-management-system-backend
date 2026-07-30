package com.cmcni.sales_management_system_backend.domain.product.controller.request_form;

import com.cmcni.sales_management_system_backend.domain.product.service.request.ProductCreateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductCreateRequestForm {
    private final Long productModelId;
    private final Long productCategoryId;
    private final String name;
    private final String note;
    private final Integer recommendedSellingPrice;

    public ProductCreateRequest toRequest() {
        return new ProductCreateRequest(
                productModelId,
                productCategoryId,
                name,
                note,
                recommendedSellingPrice
        );
    }
}
