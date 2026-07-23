package com.cmcni.sales_management_system_backend.domain.authentication.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthTokenResponse {
    private final String accessToken;
    private final String refreshToken;
}
