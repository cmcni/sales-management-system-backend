package com.cmcni.sales_management_system_backend.domain.product.controller;

import com.cmcni.sales_management_system_backend.common.response.ApiResponse;
import com.cmcni.sales_management_system_backend.domain.product.controller.request_form.ProductCreateRequestForm;
import com.cmcni.sales_management_system_backend.domain.product.controller.request_form.ProductExcelExportRequestForm;
import com.cmcni.sales_management_system_backend.domain.product.controller.request_form.ProductSearchRequestForm;
import com.cmcni.sales_management_system_backend.domain.product.service.ProductService;
import com.cmcni.sales_management_system_backend.domain.product.service.response.ProductExcelExportResponse;
import com.cmcni.sales_management_system_backend.utility.excel.ExcelSheetData;
import com.cmcni.sales_management_system_backend.utility.excel.SXSSFExcelFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

//    @PostMapping("/create/excel")
//    @Operation(summary = "제품을 엑셀로 등록합니다.")
//    public Object createExcel(@RequestParam("file") MultipartFile file) {
//        // 1. 엑셀 파일 읽는거 분리
//        // 2. 엑셀 파일 읽은 값 하나하나 entity로 만들어서 list 형태로 반환
//        // 3. list 형태로 담긴 entity 한꺼번에 insert하기
//        return ApiResponse.success();
//    }

    @GetMapping("/search")
    @Operation(summary = "검색 조건에 따른 제품 목록을 검색합니다.")
    public Object search(@ModelAttribute ProductSearchRequestForm productSearchRequestForm,
                         @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ApiResponse.success(productService.search(productSearchRequestForm.toRequest(), pageable));
    }

    @GetMapping("/excel/export")
    @Operation(summary = "검색 조건에 따른 제품 목록을 엑셀로 추출합니다.")
    public void excelExport(@ModelAttribute ProductExcelExportRequestForm productExcelExportRequestForm, Pageable pageable,
                            HttpServletResponse response) throws IOException {
        List<ProductExcelExportResponse> content = productService.excelExport(productExcelExportRequestForm.toRequest(), pageable).getContent();

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("제품_목록.xlsx", StandardCharsets.UTF_8).replace("+", "%20"));

        new SXSSFExcelFile(ExcelSheetData.of(content, ProductExcelExportResponse.class), response);
    }

    @DeleteMapping("/delete/{product-id}")
    @Operation(summary = "제품을 삭제합니다.")
    public Object delete(@PathVariable("product-id") Long productId) {
        productService.delete(productId);
        return ApiResponse.success();
    }
}
