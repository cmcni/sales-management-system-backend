package com.cmcni.sales_management_system_backend.domain.product.service.response;

import com.cmcni.sales_management_system_backend.domain.product.entity.Product;
import com.cmcni.sales_management_system_backend.domain.product_category.service.response.ProductCategoryFindResponse;
import com.cmcni.sales_management_system_backend.domain.product_model.service.response.ProductModelFindResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ProductFindResponse {

    private final Long productId;
    private final ProductModelFindResponse productModelFindResponse;
    private final ProductCategoryFindResponse productCategoryFindResponse;
    private final String name;
    private final String note;
    private final Integer recommendedSellingPrice;
    private final LocalDateTime createdAt;

    public static ProductFindResponse from(Product product) {
        return new ProductFindResponse(
                product.getId(),
                ProductModelFindResponse.from(product.getModel()),
                ProductCategoryFindResponse.from(product.getCategory()),
                product.getName(),
                product.getNote(),
                product.getRecommendedSellingPrice(),
                product.getCreatedAt()
        );
    }
}
