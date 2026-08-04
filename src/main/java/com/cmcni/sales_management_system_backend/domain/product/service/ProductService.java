package com.cmcni.sales_management_system_backend.domain.product.service;

import com.cmcni.sales_management_system_backend.domain.product.service.request.ProductCreateRequest;
import com.cmcni.sales_management_system_backend.domain.product.service.request.ProductExcelExportRequest;
import com.cmcni.sales_management_system_backend.domain.product.service.request.ProductSearchRequest;
import com.cmcni.sales_management_system_backend.domain.product.service.response.ProductExcelExportResponse;
import com.cmcni.sales_management_system_backend.domain.product.service.response.ProductSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    void create(ProductCreateRequest request);

    void delete(Long productId);

    Page<ProductSearchResponse> search(ProductSearchRequest productSearchRequest, Pageable pageable);

    Page<ProductExcelExportResponse> excelExport(ProductExcelExportRequest request, Pageable pageable);
}
