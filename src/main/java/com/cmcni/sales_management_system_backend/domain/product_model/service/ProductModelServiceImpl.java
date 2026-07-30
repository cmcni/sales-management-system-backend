package com.cmcni.sales_management_system_backend.domain.product_model.service;

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
        productModelRepository.save(productModelCreateRequest.toProductModel());
        return findAll();
    }

    @Override
    public List<ProductModelFindResponse> findAll() {
        return productModelRepository.findAll().stream().map(ProductModelFindResponse::from).toList();
    }
}
