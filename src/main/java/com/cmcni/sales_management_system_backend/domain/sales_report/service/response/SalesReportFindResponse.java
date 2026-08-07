package com.cmcni.sales_management_system_backend.domain.sales_report.service.response;

import com.cmcni.sales_management_system_backend.domain.buyer.service.response.BuyerFindResponse;
import com.cmcni.sales_management_system_backend.domain.company.service.response.CompanyFindResponse;
import com.cmcni.sales_management_system_backend.domain.sales_report.entity.*;
import com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class SalesReportFindResponse {

    private final Long id;
    private final BuyerFindResponse buyerFindResponse;
    private final CompanyFindResponse companyFindResponse;
    private final SalesReportType salesReportType;
    private final PaymentType paymentType;
    private final SalesContractInfo salesContractInfo;
    private final SalesProjectInfo salesProjectInfo;
    private final SalesDeliveryInfo salesDeliveryInfo;
    private final SalesPaymentInfo salesPaymentInfo;
    private final LocalDate orderDate;
    private final String salesManager;
    private final String fieldManager;
    private final Integer warranty;
    private final String etc;

    public static SalesReportFindResponse from(SalesReport salesReport) {
        return new SalesReportFindResponse(
                salesReport.getId(),
                BuyerFindResponse.from(salesReport.getBuyer()),
                CompanyFindResponse.from(salesReport.getCompany()),
                salesReport.getSalesReportType(),
                salesReport.getPaymentType(),
                salesReport.getSalesContractInfo(),
                salesReport.getSalesProjectInfo(),
                salesReport.getSalesDeliveryInfo(),
                salesReport.getSalesPaymentInfo(),
                salesReport.getOrderDate(),
                salesReport.getSalesManager(),
                salesReport.getFieldManager(),
                salesReport.getWarranty(),
                salesReport.getEtc()
        );
    }
}
