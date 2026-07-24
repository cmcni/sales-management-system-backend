package com.cmcni.sales_management_system_backend.domain.user.entity;

import lombok.Getter;

@Getter
public enum RoleType {
    MASTER("마스터"),
    ADMIN("어드민"),
    USER("유저"),
    VIEWER("뷰어");

    private final String label;

    RoleType(String label) {
        this.label = label;
    }
}
