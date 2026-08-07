package com.cmcni.sales_management_system_backend.domain.sales_report.service.request;

import com.cmcni.sales_management_system_backend.domain.buyer.entity.Buyer;
import com.cmcni.sales_management_system_backend.domain.company.entity.Company;
import com.cmcni.sales_management_system_backend.domain.sales_report.entity.*;
import com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub.PaymentType;
import com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub.SalesReportType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class SalesReportCreateRequest {
    private final Buyer buyer; // 발주처
    private final Company company; // 회사

    private final SalesReportType salesReportType; // 레포트 구분
    private final PaymentType paymentType; // 대금결제

    private final LocalDate orderDate; // 발주일
    private final Integer warranty; // 워런티(보증기간)

    private final SalesContractInfo salesContractInfo; // 계약 정보
    private final SalesDeliveryInfo salesDeliveryInfo; // 배송 정보
    private final SalesPaymentInfo salesPaymentInfo; // 관리부 결제/매출 정보
    private final SalesProjectInfo salesProjectInfo; // 공사 정보

    private final String salesManager; // 영업담당자
    private final String fieldManager; // 현장담당자
    private final String etc; // 기타사항

    public SalesReport toSalesReport() {
        return new SalesReport(
                buyer,
                company,
                salesReportType,
                paymentType,
                salesContractInfo,
                salesProjectInfo,
                salesDeliveryInfo,
                salesPaymentInfo,
                orderDate,
                salesManager,
                fieldManager,
                warranty,
                etc
        );
    }
}
