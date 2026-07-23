package com.cmcni.sales_management_system_backend.domain.user.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
public class EmailAddress {
    private String emailAddress;

    public EmailAddress(String emailAddress) {
        checkEmailAddressValidation(emailAddress);
        this.emailAddress = emailAddress;
    }

    private void checkEmailAddressValidation(String emailAddress) {
        if (emailAddress == null) {
            throw new IllegalArgumentException("이메일은 Null이 될 수 없습니다.");
        }

        String trimmedEmail = emailAddress.trim();

        if (trimmedEmail.isEmpty()) {
            throw new IllegalArgumentException("이메일을 입력해야 합니다.");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher matcher = emailPattern.matcher(trimmedEmail);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }
    }
}
