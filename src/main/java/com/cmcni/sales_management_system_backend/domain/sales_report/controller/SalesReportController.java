package com.cmcni.sales_management_system_backend.domain.sales_report.controller;

import com.cmcni.sales_management_system_backend.common.response.ApiResponse;
import com.cmcni.sales_management_system_backend.domain.buyer.entity.Buyer;
import com.cmcni.sales_management_system_backend.domain.buyer.service.BuyerService;
import com.cmcni.sales_management_system_backend.domain.company.entity.Company;
import com.cmcni.sales_management_system_backend.domain.company.service.CompanyService;
import com.cmcni.sales_management_system_backend.domain.sales_report.controller.request_form.SalesReportCreateRequestForm;
import com.cmcni.sales_management_system_backend.domain.sales_report.service.SalesReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sales-report")
@Tag(name = "[영업]", description = "영업 도메인")
public class SalesReportController {

    private final BuyerService buyerService;
    private final CompanyService companyService;
    private final SalesReportService salesReportService;

    @PostMapping("/create")
    @Operation(summary = "영업 리포트를 생성합니다.")
    public Object create(@RequestBody SalesReportCreateRequestForm salesReportCreateRequestForm) {
        Buyer buyer = buyerService.findById(salesReportCreateRequestForm.getBuyerId());
        Company company = companyService.findById(salesReportCreateRequestForm.getCompanyId());
        return ApiResponse.success(salesReportService.create(salesReportCreateRequestForm.toRequest(buyer, company)));
    }

    @GetMapping("/list")
    @Operation(summary = "영업 리포트 목록을 리포트 종류에 따라 반환합니다.")
    public Object listBySalesReportType(@RequestParam("report-type") String salesReportType) {
        return ApiResponse.success();
    }
}