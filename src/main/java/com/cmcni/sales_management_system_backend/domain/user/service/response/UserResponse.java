package com.cmcni.sales_management_system_backend.domain.user.service.response;

import com.cmcni.sales_management_system_backend.domain.user.entity.User;
import com.cmcni.sales_management_system_backend.domain.user.entity.UserRoleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponse {

    private final Long userId;
    private final UserRoleType userRoleType;
    private final String emailAddress;
    private final String phoneNumber;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUserRoleType(),
                user.getEmailAddress(),
                user.getPhoneNumber()
        );
    }

}
