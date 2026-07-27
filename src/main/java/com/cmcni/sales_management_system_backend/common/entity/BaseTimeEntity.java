package com.cmcni.sales_management_system_backend.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    @Column(comment = "생성일", columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(comment = "수정일", columnDefinition = "datetime")
    private LocalDateTime modifiedAt;

}
