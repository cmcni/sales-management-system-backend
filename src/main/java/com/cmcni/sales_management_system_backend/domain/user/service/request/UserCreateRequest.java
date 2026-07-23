package com.cmcni.sales_management_system_backend.domain.user.service.request;

import com.cmcni.sales_management_system_backend.domain.user.entity.User;
import com.cmcni.sales_management_system_backend.domain.user.entity.UserRoleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreateRequest {
    private final Long userRoleTypeId;
    private final String emailAddress;
    private final String password;
    private final String name;
    private final String phoneNumber;

    public User toUser(UserRoleType userRoleType, String name, String phoneNumber, String emailAddress, String encodedPassword) {
        return new User(
                userRoleType, emailAddress, encodedPassword, name, phoneNumber
        );
    }
}
