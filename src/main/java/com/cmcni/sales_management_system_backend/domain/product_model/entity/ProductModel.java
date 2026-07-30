package com.cmcni.sales_management_system_backend.domain.product_model.entity;

import com.cmcni.sales_management_system_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ProductModel extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, comment = "모델명")
    private String name;

    public ProductModel() {}

    public ProductModel(String name) {
        this.name = name;
    }
}
