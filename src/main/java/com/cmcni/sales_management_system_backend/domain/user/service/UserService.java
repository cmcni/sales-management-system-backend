package com.cmcni.sales_management_system_backend.domain.user.service;

import com.cmcni.sales_management_system_backend.domain.user.service.request.UserCreateRequest;
import com.cmcni.sales_management_system_backend.domain.user.service.response.UserCreateResponse;

public interface UserService {
    UserCreateResponse signUp(UserCreateRequest request);
}
