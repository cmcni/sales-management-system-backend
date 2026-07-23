package com.cmcni.sales_management_system_backend.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class UserRoleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(comment = "권한", columnDefinition = "varchar(50)", nullable = false)
    private RoleType roleType;

    public UserRoleType() {}

    public UserRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
