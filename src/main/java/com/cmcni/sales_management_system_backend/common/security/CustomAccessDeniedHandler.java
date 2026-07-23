package com.cmcni.sales_management_system_backend.common.security;

import com.cmcni.sales_management_system_backend.common.exception.CustomErrorCode;
import com.cmcni.sales_management_system_backend.common.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.HttpStatus.FORBIDDEN;

// 인증이 완료되었으나 해당 엔드포인트에 접근할 권한이 없다면, 403 Forbidden 오류가 발생한다.
// HttpStatus 403 Forbidden은 서버가 해당 요청을 이해하였으나, 사용자의 권한이 부족하여 요청이 거부된 상태를 말한다.

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("No Authorities", accessDeniedException);
        log.error("Request Uri : {}", request.getRequestURI());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());

        try (OutputStream os = response.getOutputStream()) {
            String message = CustomErrorCode.NOT_VALID_REQUEST.getMessage();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, ErrorResponse.create(FORBIDDEN.value(), message));
            os.flush();
        }
    }
}
