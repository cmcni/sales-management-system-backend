package com.cmcni.sales_management_system_backend.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.Objects;

import static org.springframework.util.StringUtils.hasText;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private T data;
    private Map<String, Object> pageInfo;
    private boolean success;

    public ApiResponse() {
        this.success = true;
    }

    public ApiResponse(T data) {
        this.data = data;
        this.success = true;
    }

    public ApiResponse(T data, Map<String, Object> pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
        this.success = true;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> success(T data) {
        if (data instanceof Page) {
            return (ApiResponse<T>) new ApiResponse<>(((Page<?>) data).getContent(), setPageInfo((Page) data));
        }
        return new ApiResponse<>(data);
    }

    public static Map<String, Object> setPageInfo(Page data) {
        String property = "";
        boolean desc = true;

        for (Sort.Order order : data.getSort()) {
            property = order.getProperty();
        }

        if (hasText(property)) {
            desc = Objects.requireNonNull(data.getSort().getOrderFor(property)).getDirection().isDescending();
        }

        return Map.of(
                "totalPage",    Math.max(data.getTotalPages() - 1, 0),
                "desc",         desc,
                "sort",         property,
                "page",         data.getNumber(),
                "size",         data.getSize(),
                "totalElements",data.getTotalElements()
        );
    }
}
