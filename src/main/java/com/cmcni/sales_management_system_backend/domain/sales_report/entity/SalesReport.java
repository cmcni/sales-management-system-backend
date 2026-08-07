package com.cmcni.sales_management_system_backend.domain.sales_report.entity;

import com.cmcni.sales_management_system_backend.common.entity.BaseTimeEntity;
import com.cmcni.sales_management_system_backend.domain.buyer.entity.Buyer;
import com.cmcni.sales_management_system_backend.domain.company.entity.Company;
import com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class SalesReport extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", comment = "발주처")
    private Buyer buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", comment = "회사")
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)", comment = "계약 / 견적 구분용")
    private SalesReportType salesReportType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)", comment = "대금결제")
    private PaymentType paymentType;

    @Embedded // 계약 정보
    private SalesContractInfo salesContractInfo;

    @Embedded // 공사 정보
    private SalesProjectInfo salesProjectInfo;

    @Embedded // 배송 정보
    private SalesDeliveryInfo salesDeliveryInfo;

    @Embedded // 관리부 계약&결제 정보
    private SalesPaymentInfo salesPaymentInfo;

    @Column(comment = "발주일")
    private LocalDate orderDate;

    @Column(comment = "영업담당자")
    private String salesManager;

    @Column(comment = "현장담당자")
    private String fieldManager;

    @Column(comment = "워런티(보증기간)")
    private Integer warranty;

    @Column(comment = "기타사항", length = 1000)
    private String etc;

    public SalesReport(Buyer buyer, Company company, SalesReportType salesReportType, PaymentType paymentType, SalesContractInfo salesContractInfo, SalesProjectInfo salesProjectInfo, SalesDeliveryInfo salesDeliveryInfo, SalesPaymentInfo salesPaymentInfo, LocalDate orderDate, String salesManager, String fieldManager, Integer warranty, String etc) {
        this.buyer = buyer;
        this.company = company;
        this.salesReportType = salesReportType;
        this.paymentType = paymentType;
        this.salesContractInfo = salesContractInfo;
        this.salesProjectInfo = salesProjectInfo;
        this.salesDeliveryInfo = salesDeliveryInfo;
        this.salesPaymentInfo = salesPaymentInfo;
        this.orderDate = orderDate;
        this.salesManager = salesManager;
        this.fieldManager = fieldManager;
        this.warranty = warranty;
        this.etc = etc;
    }
}
