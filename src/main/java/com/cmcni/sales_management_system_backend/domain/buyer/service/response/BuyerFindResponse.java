package com.cmcni.sales_management_system_backend.domain.buyer.service.response;

import com.cmcni.sales_management_system_backend.domain.buyer.entity.Buyer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BuyerFindResponse {
    private final Long id;
    private final String name;

    public static BuyerFindResponse from(Buyer buyer) {
        return new BuyerFindResponse(
                buyer.getId(),
                buyer.getName()
        );
    }
}
