package com.cmcni.sales_management_system_backend.domain.sales_report.service;

import com.cmcni.sales_management_system_backend.domain.sales_report.service.request.SalesReportCreateRequest;
import com.cmcni.sales_management_system_backend.domain.sales_report.service.response.SalesReportFindResponse;

public interface SalesReportService {
    SalesReportFindResponse create(SalesReportCreateRequest salesReportCreateRequest);
}
