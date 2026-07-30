package com.cmcni.sales_management_system_backend.domain.product_category.service;

import com.cmcni.sales_management_system_backend.domain.product_category.service.request.ProductCategoryCreateRequest;
import com.cmcni.sales_management_system_backend.domain.product_category.service.response.ProductCategoryFindResponse;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryFindResponse> create(ProductCategoryCreateRequest productCategoryCreateRequest);

    List<ProductCategoryFindResponse> findAll();
}
