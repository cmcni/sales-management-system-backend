package com.cmcni.sales_management_system_backend.domain.company.service;

import com.cmcni.sales_management_system_backend.common.exception.CustomErrorCode;
import com.cmcni.sales_management_system_backend.common.exception.CustomException;
import com.cmcni.sales_management_system_backend.domain.company.entity.Company;
import com.cmcni.sales_management_system_backend.domain.company.repository.CompanyRepository;
import com.cmcni.sales_management_system_backend.domain.company.service.response.CompanyFindResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company findById(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(() -> new CustomException(CustomErrorCode.COMPANY_IS_NOT_EXIST));
    }

    @Override
    public List<CompanyFindResponse> findAll() {
        return companyRepository.findAll().stream().map(CompanyFindResponse::from).toList();
    }
}
