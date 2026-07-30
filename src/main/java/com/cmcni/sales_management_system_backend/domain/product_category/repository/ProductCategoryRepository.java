package com.cmcni.sales_management_system_backend.domain.product_category.repository;

import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
