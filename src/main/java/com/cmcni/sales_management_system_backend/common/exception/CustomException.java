package com.cmcni.sales_management_system_backend.common.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    final private CustomErrorCode customErrorCode;
    private String message;

    public CustomException(CustomErrorCode customErrorCode) {
        this.customErrorCode = customErrorCode;
    }

    public CustomException(CustomErrorCode customErrorCode, String message) {
        this.customErrorCode = customErrorCode;
        this.message = message;
    }

    public String getMessage() {
        if (this.message == null) {
            return this.customErrorCode.getMessage();
        } else {
            return String.format(this.customErrorCode.getMessage(), this.message);
        }
    }

    public HttpStatus getHttpStatus() {
        return this.customErrorCode.getHttpStatus();
    }
}
