package com.cmcni.sales_management_system_backend.common.security;

import com.cmcni.sales_management_system_backend.common.response.ErrorResponse;
import com.cmcni.sales_management_system_backend.common.security.jwt.JwtConst;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.io.OutputStream;

// AuthenticationEntryPoint 인터페이스는 인증되지 않은 사용자가 인증이 필요한 요청 엔드포인트로 접근하려 할 때, 예외를 핸들링 할 수 있도록 도와준다.
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    final private DispatcherServlet dispatcherServlet;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        if (!isEndpointExist(request)) {
            log.warn("Not Found", authException);
            log.warn("Endpoint >  {}", request.getRequestURI());

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.NOT_FOUND.value());

            try (OutputStream os = response.getOutputStream()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(os, ErrorResponse.create(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()));
                os.flush();
            }
        }

        log.error("Not Authenticated Request", authException);
        log.error("Request Uri : {}", request.getRequestURI());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // TODO 코드 변환 필요
        String message = (String) request.getAttribute(JwtConst.EXCEPTION);
        if (message == null) {
            message = "인증 실패";
        }

        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, ErrorResponse.create(HttpStatus.UNAUTHORIZED.value(), message));
            os.flush();
        }
    }

    private boolean isEndpointExist(HttpServletRequest request) {
        for (HandlerMapping handlerMapping : dispatcherServlet.getHandlerMappings()) {
            try {
                HandlerExecutionChain foundHandler = handlerMapping.getHandler(request);
                if (foundHandler != null) {
                    Object handler = foundHandler.getHandler();
                    if (handler != null && handler.getClass().getName().contains("org.springframework.web.method")) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
