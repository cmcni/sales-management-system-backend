package com.cmcni.sales_management_system_backend.domain.company.service.response;

import com.cmcni.sales_management_system_backend.domain.company.entity.Company;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CompanyFindResponse {
    private final Long id;
    private final String name;

    public static CompanyFindResponse from(Company company) {
        return new CompanyFindResponse(
                company.getId(),
                company.getName()
        );
    }
}
