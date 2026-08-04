package com.cmcni.sales_management_system_backend.domain.product_model.service;

import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_model.entity.ProductModel;
import com.cmcni.sales_management_system_backend.domain.product_model.repository.ProductModelRepository;
import com.cmcni.sales_management_system_backend.domain.product_model.service.request.ProductModelCreateRequest;
import com.cmcni.sales_management_system_backend.domain.product_model.service.response.ProductModelFindResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductModelServiceImpl implements ProductModelService {

    private final ProductModelRepository productModelRepository;

    @Override
    public List<ProductModelFindResponse> create(ProductModelCreateRequest productModelCreateRequest) {
        ProductModel productModel = productModelRepository.save(productModelCreateRequest.toProductModel());
        return findAllByProductCategory(productModel.getProductCategory());
    }

    @Override
    public List<ProductModelFindResponse> findAll() {
        return productModelRepository.findAll().stream().map(ProductModelFindResponse::from).toList();
    }

    @Override
    public List<ProductModelFindResponse> findAllByProductCategory(ProductCategory productCategory) {
        return productModelRepository.findAllByProductCategory(productCategory).stream().map(ProductModelFindResponse::from).toList();
    }
}
