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
public class SalesProjectInfo {
    @Column(comment = "공사 시작일")
    private LocalDate projectStartDate;
    @Column(comment = "공사 종료일")
    private LocalDate projectEndDate;
    @Column(comment = "공사 담당자")
    private String projectManager;
}
