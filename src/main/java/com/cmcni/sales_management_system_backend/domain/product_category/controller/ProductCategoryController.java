package com.cmcni.sales_management_system_backend.domain.product_category.controller;

import com.cmcni.sales_management_system_backend.common.response.ApiResponse;
import com.cmcni.sales_management_system_backend.domain.product_category.controller.request_form.ProductCategoryCreateRequestForm;
import com.cmcni.sales_management_system_backend.domain.product_category.service.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product/category")
@Tag(name = "[제품 카테고리]", description = "제품 카테고리 관련 도메인")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @PostMapping("/create")
    @Operation(summary = "제품 카테고리를 생성합니다.")
    public Object create(@RequestBody ProductCategoryCreateRequestForm productCategoryCreateRequestForm) {
        return ApiResponse.success(productCategoryService.create(productCategoryCreateRequestForm.toRequest()));
    }

    @GetMapping("/find-all")
    @Operation(summary = "제품 카테고리 목록을 조회합니다.")
    public Object findAll() {
        return ApiResponse.success(productCategoryService.findAll());
    }
}
