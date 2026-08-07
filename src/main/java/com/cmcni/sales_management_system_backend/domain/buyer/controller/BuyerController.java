package com.cmcni.sales_management_system_backend.domain.buyer.controller;

import com.cmcni.sales_management_system_backend.common.response.ApiResponse;
import com.cmcni.sales_management_system_backend.domain.buyer.controller.request_form.BuyerCreateRequestForm;
import com.cmcni.sales_management_system_backend.domain.buyer.service.BuyerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/buyer")
@Tag(name = "[발주처]", description = "발주처 도메인")
public class BuyerController {

    private final BuyerService buyerService;

    @PostMapping("/create")
    @Operation(summary = "발주처를 등록합니다.")
    public Object create(@RequestBody BuyerCreateRequestForm buyerCreateRequestForm) {
        return ApiResponse.success(buyerService.create(buyerCreateRequestForm.toRequest()));
    }

    @GetMapping("/find-all")
    @Operation(summary = "발주처 전체를 반환합니다.")
    public Object findAll() {
        return ApiResponse.success(buyerService.findAll());
    }
}
