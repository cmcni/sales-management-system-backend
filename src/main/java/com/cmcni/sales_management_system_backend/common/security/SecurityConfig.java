package com.cmcni.sales_management_system_backend.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    final private CustomAccessDeniedHandler customAccessDeniedHandler;
    final private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Content-Type", "Authorization", "X-XSRF-token", "X-Dealer-Host", "X-GA4-Client-Id"));
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // httpBasic disable : 비인증 시 로그인 페이지 redirect 처리(UsernamePasswordAuthenticationFilter) 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)
                // 폼로그인 사용 안함
                .formLogin(AbstractHttpConfigurer::disable)
                // csrf disable : csrf 보안 필요 없음(STATELESS : 세션 사용 안함)
                .csrf(AbstractHttpConfigurer::disable)
                // cors 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // 세션 사용 안하니까 STATELESS로 설정
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 401, 403 Exception 처리
                .exceptionHandling(c -> c
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.GET, SecurityConfigPermitAllRepository.getPaths(HttpMethod.GET)).permitAll()
                        .requestMatchers(HttpMethod.POST, SecurityConfigPermitAllRepository.getPaths(HttpMethod.POST)).permitAll()
                                .anyRequest().authenticated())
                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/"));
        return http.build();
    }
}
