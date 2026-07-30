package com.cmcni.sales_management_system_backend.domain.authentication.controller.response_form;

import com.cmcni.sales_management_system_backend.domain.authentication.service.response.AuthTokenResponse;
import com.cmcni.sales_management_system_backend.domain.user.controller.response_form.UserFindResponseForm;

public record LoginResponseForm(AuthTokenResponse authToken, UserFindResponseForm userInfo) {
}
