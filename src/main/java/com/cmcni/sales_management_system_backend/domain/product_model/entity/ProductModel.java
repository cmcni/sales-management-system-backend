package com.cmcni.sales_management_system_backend.domain.product_model.entity;

import com.cmcni.sales_management_system_backend.common.entity.BaseTimeEntity;
import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ProductModel extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;

    @Column(comment = "모델명")
    private String name;

    public ProductModel() {}

    public ProductModel(ProductCategory productCategory, String name) {
        this.productCategory = productCategory;
        this.name = name;
    }
}
