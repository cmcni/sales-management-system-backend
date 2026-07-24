package com.cmcni.sales_management_system_backend.domain.authentication.service;

import com.cmcni.sales_management_system_backend.domain.authentication.controller.request_form.LoginRequestForm;
import com.cmcni.sales_management_system_backend.domain.authentication.controller.response_form.LoginResponseForm;

public interface AuthenticationService {

    LoginResponseForm getToken(LoginRequestForm loginRequestForm);
    LoginResponseForm login(LoginRequestForm loginRequestForm);
}
