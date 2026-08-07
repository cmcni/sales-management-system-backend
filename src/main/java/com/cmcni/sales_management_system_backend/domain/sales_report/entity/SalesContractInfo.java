package com.cmcni.sales_management_system_backend.domain.sales_report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SalesContractInfo {
    @Column(comment = "계약명")
    private String contractName;
    @Column(comment = "계약일")
    private LocalDate contractDate;
    @Column(comment = "계약금액")
    private int contractAmount;
}
