package com.cmcni.sales_management_system_backend.common.security.jwt;

public class JwtConst {
    public JwtConst() {}
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 60;  // 1시간
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24; // 1일
    public static final long SIXTY_DAYS_REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 60; // 60일
    public static final long TEMPORAL_TOKEN_EXPIRE_TIME = 1000L * 60 * 10; // 10분
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_TYPE = "Bearer ";
    public static final String TOKEN_TYPE = "tokenType";
    public static final String TOKEN_TYPE_ACCESS = "access";
    public static final String TOKEN_TYPE_REFRESH = "refresh";
    public static final String TOKEN_TYPE_TEMPORAL = "temporal";
    public static final String EXCEPTION = "jwt-exception";
    public static final String ROLE_TYPE = "role-type";
    public static final String USER_ID = "user-id";
}
