package com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub;

import lombok.Getter;

@Getter
public enum DeliveryType {
    ROZEN_PREPAID, // 로젠(선불)
    KYUNGDONG_PREPAID, // 경동(선불)
    COURIER, // 용달
    DIRECT, // 직접배송
    NONE // 배송없음
}
