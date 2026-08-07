package com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub;

import lombok.Getter;

@Getter
public enum InvoiceType {
    ISSUED, // 계산서 발행
    NOT_ISSUED, // 미발행
    NON_SALES, // 비매출
    SPLIT_ISSUED // 분할 발행
}
