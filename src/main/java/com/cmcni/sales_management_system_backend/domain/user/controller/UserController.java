package com.cmcni.sales_management_system_backend.domain.user.controller;

import com.cmcni.sales_management_system_backend.common.response.ApiResponse;
import com.cmcni.sales_management_system_backend.domain.user.controller.request_form.UserCreateRequestForm;
import com.cmcni.sales_management_system_backend.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "[회원]", description = "회원 도메인")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @Operation(summary = "사용자가 회원 정보를 입력하고 회원 가입을 합니다.")
    public Object signUp(@RequestBody UserCreateRequestForm userCreateRequestForm) {
        return ApiResponse.success(userService.signUp(userCreateRequestForm.toRequest()));
    }
}
