package com.cmcni.sales_management_system_backend.domain.user.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.regex.Pattern;

@Getter
@ToString
public class Name {
    private String name;

    public Name(String name) {
        checkNameValidation(name);
        this.name = name;
    }

    private void checkNameValidation(String name) {
        if (name == null) {
            throw new IllegalArgumentException("이름은 Null이 될 수 없습니다.");
        }

        String trimmedName = name.trim();

        if (trimmedName.isEmpty()) {
            throw new IllegalArgumentException("이름을 입력해야 합니다.");
        }

        if (trimmedName.length() < 2 || trimmedName.length() > 30) {
            throw new IllegalArgumentException("이름은 2자 이상 30자 이하로 작성해야 합니다.");
        }

        if (!Pattern.matches("^[가-힣a-zA-Z]{2,30}$", trimmedName)) {
            throw new IllegalArgumentException("이름은 한글과 영문만 사용할 수 있습니다.");
        }
    }

}
