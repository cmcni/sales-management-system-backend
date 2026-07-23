package com.cmcni.sales_management_system_backend.domain.authentication.controller.response_form;

import com.cmcni.sales_management_system_backend.domain.authentication.service.response.AuthTokenResponse;
import com.cmcni.sales_management_system_backend.domain.user.service.response.UserResponse;

public record LoginResponseForm(AuthTokenResponse authToken, UserResponse userInfo) {
}
