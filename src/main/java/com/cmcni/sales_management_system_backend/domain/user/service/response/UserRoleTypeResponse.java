package com.cmcni.sales_management_system_backend.domain.user.service.response;

import com.cmcni.sales_management_system_backend.domain.user.entity.RoleType;
import com.cmcni.sales_management_system_backend.domain.user.entity.UserRoleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRoleTypeResponse {
    private final Long roleTypeId;
    private final RoleType roleTypeName;

    public static UserRoleTypeResponse from(UserRoleType userRoleType) {
        return new UserRoleTypeResponse(
                userRoleType.getId(),
                userRoleType.getRoleType()
        );
    }
}
