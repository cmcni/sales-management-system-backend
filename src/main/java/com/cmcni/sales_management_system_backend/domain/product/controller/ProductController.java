package com.cmcni.sales_management_system_backend.domain.product.controller;

import com.cmcni.sales_management_system_backend.common.response.ApiResponse;
import com.cmcni.sales_management_system_backend.domain.product.controller.request_form.ProductCreateRequestForm;
import com.cmcni.sales_management_system_backend.domain.product.controller.request_form.ProductSearchRequestForm;
import com.cmcni.sales_management_system_backend.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "[제품]", description = "제품 관련 도메인")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    @Operation(summary = "제품을 등록합니다.")
    public Object create(@RequestBody ProductCreateRequestForm productCreateRequestForm) {
        productService.create(productCreateRequestForm.toRequest());
        return ApiResponse.success();
    }
    
    @GetMapping("/search")
    @Operation(summary = "검색 조건에 따른 제품 목록을 검색합니다.")
    public Object search(@ModelAttribute ProductSearchRequestForm productSearchRequestForm,
                         @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ApiResponse.success(productService.search(productSearchRequestForm.toRequest(), pageable));
    }

    @DeleteMapping("/delete/{product-id}")
    @Operation(summary = "제품을 삭제합니다.")
    public Object delete(@PathVariable("product-id") Long productId) {
        productService.delete(productId);
        return ApiResponse.success();
    }
}
