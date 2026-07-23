package com.cmcni.sales_management_system_backend.domain.user.controller.request_form;

import com.cmcni.sales_management_system_backend.domain.user.service.request.UserCreateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreateRequestForm {
    private final Long userRoleTypeId;
    private final String emailAddress;
    private final String password;
    private final String name;
    private final String phoneNumber;

    public UserCreateRequest toRequest() {
        return new UserCreateRequest(
                userRoleTypeId, emailAddress, password, name, phoneNumber
        );
    }
}
