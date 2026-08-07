package com.cmcni.sales_management_system_backend.domain.company.repository;

import com.cmcni.sales_management_system_backend.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
