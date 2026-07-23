package com.cmcni.sales_management_system_backend.domain.authentication.controller;

import com.cmcni.sales_management_system_backend.common.response.ApiResponse;
import com.cmcni.sales_management_system_backend.domain.authentication.controller.request_form.LoginRequestForm;
import com.cmcni.sales_management_system_backend.domain.authentication.service.AuthenticationService;
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
@RequestMapping("/authentication")
@Tag(name = "[인증/인가]", description = "로그인, 로그아웃 로직")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/get-token")
    @Operation(summary = "로그인을 통한 AccessToken, RefreshToken 발급합니다.")
    public Object getToken(@RequestBody LoginRequestForm loginRequestForm) {
        return ApiResponse.success(authenticationService.getToken(loginRequestForm));
    }
}
