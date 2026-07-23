package com.cmcni.sales_management_system_backend.domain.user.service.response;

import com.cmcni.sales_management_system_backend.domain.user.entity.RoleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreateResponse {
    private final RoleType roleType;
    private final String emailAddress;
    private final String name;
    private final String phoneNumber;
}
