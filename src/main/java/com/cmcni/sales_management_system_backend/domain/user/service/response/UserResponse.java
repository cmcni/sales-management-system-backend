package com.cmcni.sales_management_system_backend.domain.user.service.response;

import com.cmcni.sales_management_system_backend.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponse {

    private final Long userId;
    private final String roleType;
    private final String emailAddress;
    private final String phoneNumber;
    private final String name;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUserRoleType().getRoleType().toString(),
                user.getEmailAddress(),
                user.getPhoneNumber(),
                user.getName()
        );
    }

}
