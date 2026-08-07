package com.cmcni.sales_management_system_backend.domain.user.entity;

import lombok.Getter;

@Getter
public enum RoleType {
    MANAGEMENT("관리부"),
    TECHNICAL_SALES("기술영업본부");

    private final String label;

    RoleType(String label) {
        this.label = label;
    }
}
