package com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub;

import lombok.Getter;

@Getter
public enum SalesType {

    SALE("판매"),
    INSTALL("설치"),
    CONSTRUCTION("공사"),
    CONSUMABLE("소모품"),
    PAID_AS("유상AS"),
    MAINTENANCE("유지보수"),
    ETC_ACCIDENT("기타(사고)"),
    OPERATION("운영사업"),
    INSTALLMENT("할부판매"),
    INTEGRATED_CONTROL("통합관제"),
    SHARED_ENTRANCE("공동현관"),
    PAYMENT_MANAGEMENT("입금관리");

    private final String label;

    SalesType(String label) {
        this.label = label;
    }
}