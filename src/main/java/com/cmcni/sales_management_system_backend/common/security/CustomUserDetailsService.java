package com.cmcni.sales_management_system_backend.common.security;

import com.cmcni.sales_management_system_backend.common.exception.CustomErrorCode;
import com.cmcni.sales_management_system_backend.common.exception.CustomException;
import com.cmcni.sales_management_system_backend.domain.user.entity.User;
import com.cmcni.sales_management_system_backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    final private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        return new CustomUserDetails(user, user.getRefreshToken());
    }
}
