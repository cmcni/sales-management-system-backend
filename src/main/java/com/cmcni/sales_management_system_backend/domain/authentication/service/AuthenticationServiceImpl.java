package com.cmcni.sales_management_system_backend.domain.authentication.service;

import com.cmcni.sales_management_system_backend.common.exception.CustomErrorCode;
import com.cmcni.sales_management_system_backend.common.exception.CustomException;
import com.cmcni.sales_management_system_backend.common.security.CustomUserDetails;
import com.cmcni.sales_management_system_backend.common.security.jwt.JwtProvider;
import com.cmcni.sales_management_system_backend.domain.authentication.controller.request_form.LoginRequestForm;
import com.cmcni.sales_management_system_backend.domain.authentication.controller.response_form.LoginResponseForm;
import com.cmcni.sales_management_system_backend.domain.authentication.service.response.AuthTokenResponse;
import com.cmcni.sales_management_system_backend.domain.user.entity.User;
import com.cmcni.sales_management_system_backend.domain.user.repository.UserRepository;
import com.cmcni.sales_management_system_backend.domain.user.service.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public LoginResponseForm login(LoginRequestForm loginRequestForm) {

        // 입력받은 아이디를 기반으로 유저 정보를 찾는다. 없을 시 예외처리
        User user = userRepository.findByEmailAddress(loginRequestForm.emailAddress()).orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        // 유저 정보를 찾았으나 비밀번호가 일치하지 않는 경우 예외처리
        if (!passwordEncoder.matches(loginRequestForm.password(), user.getPassword())) throw new CustomException(CustomErrorCode.USER_LOGIN_FAILED);

        // 이번 로그인 세션을 식별할 rememberToken 발급 후 재검증에 쓸 수 있도록 DB에 저장
        String rememberToken = JwtProvider.createRememberToken();
        user.setRefreshToken(rememberToken);
        userRepository.save(user);

        CustomUserDetails userDetails = new CustomUserDetails(user, rememberToken);

        // 토큰 발급
        String accessToken = jwtProvider.generateAccessToken(userDetails);
        String refreshToken = jwtProvider.generateRefreshToken(userDetails);
        AuthTokenResponse authTokenResponse = new AuthTokenResponse(accessToken, refreshToken);

        // 회원 정보 객체 생성
        UserResponse userResponse = UserResponse.from(user);

        return new LoginResponseForm(authTokenResponse, userResponse);
    }

    @Override
    public void logout(User user) {
        user.setAccessToken(null);
        user.setRefreshToken(null);
        userRepository.save(user);
    }
}
