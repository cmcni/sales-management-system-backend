package com.cmcni.sales_management_system_backend.domain.sales_report.entity;

import com.cmcni.sales_management_system_backend.domain.sales_report.entity.sub.DeliveryType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SalesDeliveryInfo {

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(50)", comment = "배송방법")
    private DeliveryType deliveryType;

    @Column(comment = "물품수령지")
    private String deliveryAddress;

    @Column(comment = "물품수령인")
    private String deliveryReceiver;

    @Column(comment = "납품 / 시공일자")
    private LocalDate deliveryDate;

}
