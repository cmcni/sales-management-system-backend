package com.cmcni.sales_management_system_backend.domain.user.service;

import com.cmcni.sales_management_system_backend.domain.user.entity.User;
import com.cmcni.sales_management_system_backend.domain.user.service.request.UserCreateRequest;
import com.cmcni.sales_management_system_backend.domain.user.service.response.UserCreateResponse;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserCreateResponse signUp(UserCreateRequest request);

    User findByEmailAddress(String emailAddress);

    List<Map<String, Object>> getRoleTypeList();
}
