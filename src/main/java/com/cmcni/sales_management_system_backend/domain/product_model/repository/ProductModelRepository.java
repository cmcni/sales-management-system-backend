package com.cmcni.sales_management_system_backend.domain.product_model.repository;

import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_model.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductModelRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findAllByProductCategory(ProductCategory productCategory);
}
