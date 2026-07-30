package com.cmcni.sales_management_system_backend.domain.product_model.controller;

import com.cmcni.sales_management_system_backend.common.response.ApiResponse;
import com.cmcni.sales_management_system_backend.domain.product_model.controller.request_form.ProductModelCreateRequestForm;
import com.cmcni.sales_management_system_backend.domain.product_model.service.ProductModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product/model")
@Tag(name = "[제품 모델]", description = "제품 모델 관련 도메인")
public class ProductModelController {

    private final ProductModelService productModelService;

    @PostMapping("/create")
    @Operation(summary = "제품 모델을 생성합니다.")
    public Object create(@RequestBody ProductModelCreateRequestForm productModelCreateRequestForm) {
        return ApiResponse.success(productModelService.create(productModelCreateRequestForm.toRequest()));
    }

    @GetMapping("/find-all")
    @Operation(summary = "전체 제품 모델 목록을 조회합니다.")
    public Object findAll() {
        return ApiResponse.success(productModelService.findAll());
    }
}
