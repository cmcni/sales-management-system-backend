package com.cmcni.sales_management_system_backend.domain.product.controller.request_form;

import com.cmcni.sales_management_system_backend.domain.product.service.request.ProductSearchRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductSearchRequestForm {
    private final Long productCategoryId;
    private final Long productModelId;
    private final String productModelName;
    private final Integer productRecommendedSellingPrice;

    public ProductSearchRequest toRequest() {
        return new ProductSearchRequest(
                productCategoryId,
                productModelId,
                productModelName,
                productRecommendedSellingPrice
        );
    }
}
