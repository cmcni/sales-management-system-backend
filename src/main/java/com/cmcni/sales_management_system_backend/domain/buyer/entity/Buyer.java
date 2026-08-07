package com.cmcni.sales_management_system_backend.domain.buyer.entity;

import com.cmcni.sales_management_system_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Buyer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, comment = "발주처명")
    private String name;

    public Buyer() {}

    public Buyer(String name) {
        this.name = name;
    }
}
