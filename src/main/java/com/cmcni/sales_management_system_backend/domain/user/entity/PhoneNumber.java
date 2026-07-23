package com.cmcni.sales_management_system_backend.domain.user.entity;

import com.cmcni.sales_management_system_backend.common.exception.CustomErrorCode;
import com.cmcni.sales_management_system_backend.common.exception.CustomException;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
public class PhoneNumber {
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        checkPhoneNumberValidation(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void checkPhoneNumberValidation(String phoneNumber) {
        if (phoneNumber == null) throw new CustomException(CustomErrorCode.STRING_CANNOT_BE_NULL);
        String normalizedPhoneNumber = phoneNumber.replace("-", "").trim();
        if (normalizedPhoneNumber.isEmpty()) throw new CustomException(CustomErrorCode.STRING_CANNOT_BE_EMPTY);

        String phoneRegex = "^01[016789]-?\\d{3,4}-?\\d{4}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher matcher = phonePattern.matcher(normalizedPhoneNumber);

        if (!matcher.matches()) throw new IllegalArgumentException("올바른 휴대폰 번호 형식이 아닙니다.");
    }
}
