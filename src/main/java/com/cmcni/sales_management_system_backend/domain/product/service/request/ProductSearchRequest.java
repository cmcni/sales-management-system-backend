package com.cmcni.sales_management_system_backend.domain.product.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductSearchRequest {
    private final Long productCategoryId;
    private final Long productModelId;
    private final String productModelName;
    private final Integer productRecommendedSellingPrice;
}
