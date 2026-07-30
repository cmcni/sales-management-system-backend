package com.cmcni.sales_management_system_backend.domain.product.service.request;

import com.cmcni.sales_management_system_backend.domain.product.entity.Product;
import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_model.entity.ProductModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductCreateRequest {
    private final Long productModelId;
    private final Long productCategoryId;
    private final String name;
    private final String note;
    private final Integer recommendedSellingPrice;

    public Product toProduct(ProductModel productModel, ProductCategory productCategory) {
        return new Product(
                productModel,
                productCategory,
                name,
                note,
                recommendedSellingPrice
        );
    }
}
