package com.cmcni.sales_management_system_backend.domain.product.repository;

import com.cmcni.sales_management_system_backend.domain.product.entity.Product;
import com.cmcni.sales_management_system_backend.domain.product.service.request.ProductSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<Product> search(ProductSearchRequest productSearchRequest, Pageable pageable);
}
