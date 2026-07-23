package com.cmcni.sales_management_system_backend.domain.user.service;

import com.cmcni.sales_management_system_backend.common.exception.CustomErrorCode;
import com.cmcni.sales_management_system_backend.common.exception.CustomException;
import com.cmcni.sales_management_system_backend.domain.user.entity.*;
import com.cmcni.sales_management_system_backend.domain.user.repository.UserRepository;
import com.cmcni.sales_management_system_backend.domain.user.repository.UserRoleTypeRepository;
import com.cmcni.sales_management_system_backend.domain.user.service.request.UserCreateRequest;
import com.cmcni.sales_management_system_backend.domain.user.service.response.UserCreateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleTypeRepository userRoleTypeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserCreateResponse signUp(UserCreateRequest userCreateRequest) {
        UserRoleType userRoleType = userRoleTypeRepository.findById(userCreateRequest.getUserRoleTypeId()).orElseThrow(() -> new CustomException(CustomErrorCode.USER_ROLE_NOT_FOUND));
        Name name = new Name(userCreateRequest.getName());
        PhoneNumber phoneNumber = new PhoneNumber(userCreateRequest.getPhoneNumber());
        EmailAddress emailAddress = new EmailAddress(userCreateRequest.getEmailAddress());
        checkDuplicationEmailAddress(emailAddress);

        String encodedPassword = passwordEncoder.encode(userCreateRequest.getPassword());

        User user = userCreateRequest.toUser(userRoleType, name.getName(), phoneNumber.getPhoneNumber(), emailAddress.getEmailAddress(), encodedPassword);
        userRepository.save(user);

        return new UserCreateResponse(user.getUserRoleType().getRoleType(), user.getEmailAddress(), user.getName(), user.getPhoneNumber());
    }

    private void checkDuplicationEmailAddress(EmailAddress emailAddress) {
        userRepository.findByEmailAddress(emailAddress.getEmailAddress()).orElseThrow(() -> new CustomException(CustomErrorCode.USER_EMAIL_ADDRESS_ALREADY_EXIST));
    }
}
