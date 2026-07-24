package com.cmcni.sales_management_system_backend.domain.authentication.service;

import com.cmcni.sales_management_system_backend.domain.authentication.controller.request_form.LoginRequestForm;
import com.cmcni.sales_management_system_backend.domain.authentication.controller.response_form.LoginResponseForm;
import com.cmcni.sales_management_system_backend.domain.user.entity.User;

public interface AuthenticationService {

    LoginResponseForm login(LoginRequestForm loginRequestForm);

    void logout(User user);
}
