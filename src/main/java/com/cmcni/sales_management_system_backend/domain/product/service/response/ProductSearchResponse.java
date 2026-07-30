package com.cmcni.sales_management_system_backend.domain.product.service.response;

import com.cmcni.sales_management_system_backend.domain.product.entity.Product;
import com.cmcni.sales_management_system_backend.domain.product_category.service.response.ProductCategoryFindResponse;
import com.cmcni.sales_management_system_backend.domain.product_model.service.response.ProductModelFindResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ProductSearchResponse {

    private final Long productId;
    private final ProductModelFindResponse productModel;
    private final ProductCategoryFindResponse productCategory;
    private final String name;
    private final String note;
    private final Integer recommendedSellingPrice;
    private final LocalDateTime createdAt;

    public static ProductSearchResponse from(Product product) {
        return new ProductSearchResponse(
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
