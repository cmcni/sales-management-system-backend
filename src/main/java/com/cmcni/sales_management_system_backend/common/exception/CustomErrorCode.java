package com.cmcni.sales_management_system_backend.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {
    NOT_VALID_REQUEST("올바른 요청이 아닙니다.", FORBIDDEN),

    TEMP_TOKEN_EXPIRED_OR_INVALID("만료 혹은 잘못된 임시 토큰입니다.", BAD_REQUEST),
    ACCESS_TOKEN_IS_EXPIRED_OR_INVALID("만료 혹은 잘못된 토큰입니다.", BAD_REQUEST),

    STRING_CANNOT_BE_NULL("문자열은 NULL이 될 수 없습니다.", BAD_REQUEST),
    STRING_CANNOT_BE_EMPTY("문자열은 비어있을 수 없습니다.", BAD_REQUEST),
    STRING_LENGTH_EXCEEDED("문자열이 초과되었습니다.", BAD_REQUEST),
    STRING_CANNOT_CONTAINS_SPECIAL_CHARACTER("특수문자가 포함될 수 없습니다.", BAD_REQUEST),

    USER_NOT_FOUND("존재하는 유저가 아닙니다.", NOT_FOUND),
    USER_EMAIL_ADDRESS_ALREADY_EXIST("해당 이메일로 가입된 내역이 존재합니다. 다른 이메일을 사용해 주세요.", BAD_REQUEST),
    USER_LOGIN_FAILED("아이디 혹은 비밀번호가 일치하지 않습니다. 다시 입력해 주세요.", BAD_REQUEST),
    USER_PASSWORD_NOT_MATCHED("현재 비밀번호가 일치하지 않습니다.", BAD_REQUEST),

    USER_ROLE_NOT_FOUND("존재하는 권한이 아닙니다.", NOT_FOUND)

    ;

    final private String message;
    final private HttpStatus httpStatus;
}
