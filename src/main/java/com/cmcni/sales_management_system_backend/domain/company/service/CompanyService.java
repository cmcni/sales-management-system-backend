package com.cmcni.sales_management_system_backend.domain.company.service;

import com.cmcni.sales_management_system_backend.domain.company.entity.Company;
import com.cmcni.sales_management_system_backend.domain.company.service.response.CompanyFindResponse;

import java.util.List;

public interface CompanyService {

    Company findById(Long companyId);

    List<CompanyFindResponse> findAll();

}
