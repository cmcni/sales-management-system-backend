package com.cmcni.sales_management_system_backend.domain.sales_report.entity;

import com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub.InvoiceType;
import com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub.SalesType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SalesPaymentInfo {

    // 관리부 항목
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)", comment = "계산서 발행 여부")
    private InvoiceType invoiceType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)", comment = "매출구분")
    private SalesType salesType;

    @Column(comment = "중도금")
    private int progressPayment;

    @Column(comment = "수금일(수금을 실행한 일자)")
    private LocalDate paymentCollectionDate;

    @Column(comment = "수금예정일")
    private LocalDate paymentCollectionDueDate;

    @Column(comment = "관리부 계약기간 시작일")
    private LocalDate paymentContractStartDate;

    @Column(comment = "관리부 계약기간 종료일")
    private LocalDate paymentContractEndDate;

    @Column(comment = "세금계산서 발행일자")
    private LocalDate invoiceIssueDate;

}
