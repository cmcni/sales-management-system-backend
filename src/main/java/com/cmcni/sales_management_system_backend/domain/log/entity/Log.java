package com.cmcni.sales_management_system_backend.domain.log.entity;

import com.cmcni.sales_management_system_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Log extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(comment = "api path")
    private String apiPath;

    @Column(comment = "request data")
    private String requestData;

    @Column(comment = "response data")
    private String responseData;
}
