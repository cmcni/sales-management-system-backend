package com.cmcni.sales_management_system_backend.domain.buyer.service;

import com.cmcni.sales_management_system_backend.domain.buyer.entity.Buyer;
import com.cmcni.sales_management_system_backend.domain.buyer.service.request.BuyerCreateRequest;
import com.cmcni.sales_management_system_backend.domain.buyer.service.response.BuyerFindResponse;

import java.util.List;

public interface BuyerService {

    BuyerFindResponse create(BuyerCreateRequest buyerCreateRequest);

    Buyer findById(Long buyerId);

    List<BuyerFindResponse> findAll();
}
