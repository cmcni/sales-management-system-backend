package com.cmcni.sales_management_system_backend.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    final private int httpStatus;
    final private String message;
    final private Map<String, String> validation;
    final private boolean success;

    @Builder
    public ErrorResponse(int httpStatus, String message, Map<String, String> validation) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.validation = validation;
        this.success = false;
    }
    public void addValidation(String fieldName, String message) {
        this.validation.put(fieldName, message);
    }

    public static ErrorResponse create(int httpStatus, String message) {
        return ErrorResponse.builder()
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }
}
