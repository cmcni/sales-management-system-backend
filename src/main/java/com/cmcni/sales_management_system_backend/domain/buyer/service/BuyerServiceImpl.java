package com.cmcni.sales_management_system_backend.domain.buyer.service;

import com.cmcni.sales_management_system_backend.common.exception.CustomErrorCode;
import com.cmcni.sales_management_system_backend.common.exception.CustomException;
import com.cmcni.sales_management_system_backend.domain.buyer.entity.Buyer;
import com.cmcni.sales_management_system_backend.domain.buyer.repository.BuyerRepository;
import com.cmcni.sales_management_system_backend.domain.buyer.service.request.BuyerCreateRequest;
import com.cmcni.sales_management_system_backend.domain.buyer.service.response.BuyerFindResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;

    @Override
    public BuyerFindResponse create(BuyerCreateRequest buyerCreateRequest) {
        return BuyerFindResponse.from(buyerRepository.save(buyerCreateRequest.toBuyer()));
    }

    @Override
    public Buyer findById(Long buyerId) {
        return buyerRepository.findById(buyerId).orElseThrow(() -> new CustomException(CustomErrorCode.BUYER_IS_NOT_EXIST));
    }

    @Override
    public List<BuyerFindResponse> findAll() {
        return buyerRepository.findAll().stream().map(BuyerFindResponse::from).toList();
    }
}
