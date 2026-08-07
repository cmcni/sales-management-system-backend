package com.cmcni.sales_management_system_backend.domain.company.entity;

import com.cmcni.sales_management_system_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Company extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(comment = "회사명")
    private String name;

    public Company() {}

    public Company(String name) {
        this.name = name;
    }
}
