package com.cmcni.sales_management_system_backend.domain.user.controller.response_form;

import com.cmcni.sales_management_system_backend.domain.user.service.response.UserFindResponse;
import com.cmcni.sales_management_system_backend.domain.user.service.response.UserRoleTypeResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserFindResponseForm {
    private final Long userId;
    private final Long userRoleTypeId;
    private final String userRoleTypeName;
    private final String userEmailAddress;
    private final String userName;
    private final String userPhoneNumber;

    public static UserFindResponseForm from(UserFindResponse userFindResponse) {
        UserRoleTypeResponse userRoleTypeResponse = UserRoleTypeResponse.from(userFindResponse.getUserRoleType());
        return new UserFindResponseForm(
                userFindResponse.getUserId(),
                userRoleTypeResponse.getRoleTypeId(),
                userRoleTypeResponse.getRoleTypeName().getLabel(),
                userFindResponse.getEmailAddress(),
                userFindResponse.getName(),
                userFindResponse.getPhoneNumber()
        );
    }
}
