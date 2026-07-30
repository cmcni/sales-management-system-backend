package com.cmcni.sales_management_system_backend.domain.product.repository;

import com.cmcni.sales_management_system_backend.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}
