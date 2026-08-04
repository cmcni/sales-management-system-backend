package com.cmcni.sales_management_system_backend.domain.product.repository;

import com.cmcni.sales_management_system_backend.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    @Query("select p from Product p join fetch p.model")
    List<Product> findAllWithModel();
}
