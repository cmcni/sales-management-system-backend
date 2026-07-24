package com.cmcni.sales_management_system_backend.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommEnumResponse {
    private final String label;
    private final String value;
}
