package com.cmcni.sales_management_system_backend.domain.buyer.controller.request_form;

import com.cmcni.sales_management_system_backend.domain.buyer.service.request.BuyerCreateRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BuyerCreateRequestForm {
    private final String name;

    public BuyerCreateRequest toRequest() {
        return new BuyerCreateRequest(name);
    }
}
