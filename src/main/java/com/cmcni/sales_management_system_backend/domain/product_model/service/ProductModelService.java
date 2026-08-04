package com.cmcni.sales_management_system_backend.domain.product_model.service;

import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_model.service.request.ProductModelCreateRequest;
import com.cmcni.sales_management_system_backend.domain.product_model.service.response.ProductModelFindResponse;

import java.util.List;

public interface ProductModelService {
    List<ProductModelFindResponse> create(ProductModelCreateRequest productModelCreateRequest);

    List<ProductModelFindResponse> findAll();

    List<ProductModelFindResponse> findAllByProductCategory(ProductCategory productCategory);
}
