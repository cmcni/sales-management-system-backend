package com.cmcni.sales_management_system_backend.common.security;

import com.cmcni.sales_management_system_backend.common.security.jwt.JwtConst;
import com.cmcni.sales_management_system_backend.common.security.jwt.JwtData;
import com.cmcni.sales_management_system_backend.common.security.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    final private JwtProvider jwtProvider;
    final private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtProvider.resolveToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        } else {
            try {
                JwtData jwtData = jwtProvider.verifyToken(token);
                handleAccessToken(jwtData);
            } catch (Exception e) {
                request.setAttribute(JwtConst.EXCEPTION, e.getMessage());
                log.error(e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * ACCESS 토큰 처리 (정회원)
     * - email로 UserDetails 조회
     * - 토큰의 jti(rememberToken)가 DB에 저장된 값과 같은지 검증
     */
    private void handleAccessToken(JwtData jwtData) {
        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtData.getEmail());
        jwtData.verifyTokenId(userDetails);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
