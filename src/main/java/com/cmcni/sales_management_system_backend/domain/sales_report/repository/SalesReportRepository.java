package com.cmcni.sales_management_system_backend.domain.sales_report.repository;

import com.cmcni.sales_management_system_backend.domain.sales_report.entity.SalesReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesReportRepository extends JpaRepository<SalesReport, Long> {
}
