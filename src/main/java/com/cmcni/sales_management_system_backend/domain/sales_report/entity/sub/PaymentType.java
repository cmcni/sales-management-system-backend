package com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub;

import lombok.Getter;

@Getter
public enum PaymentType {
    INVOICE, // 계산서 발행
    CARD, // 카드
    BANK_DEPOSIT, // 통장입금
    NONE // 결제없음
}
