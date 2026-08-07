package com.cmcni.sales_management_system_backend.domain.buyer.service.request;

import com.cmcni.sales_management_system_backend.domain.buyer.entity.Buyer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BuyerCreateRequest {
    private final String name;

    public Buyer toBuyer() {
        return new Buyer(name);
    }
}
