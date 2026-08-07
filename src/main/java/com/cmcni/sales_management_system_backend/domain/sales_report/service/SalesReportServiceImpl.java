package com.cmcni.sales_management_system_backend.domain.sales_report.service;

import com.cmcni.sales_management_system_backend.domain.sales_report.repository.SalesReportRepository;
import com.cmcni.sales_management_system_backend.domain.sales_report.service.request.SalesReportCreateRequest;
import com.cmcni.sales_management_system_backend.domain.sales_report.service.response.SalesReportFindResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesReportServiceImpl implements SalesReportService {
    private final SalesReportRepository salesReportRepository;

    @Override
    public SalesReportFindResponse create(SalesReportCreateRequest salesReportCreateRequest) {
        salesReportRepository.save(salesReportCreateRequest.toSalesReport());
        return null;
    }
}
