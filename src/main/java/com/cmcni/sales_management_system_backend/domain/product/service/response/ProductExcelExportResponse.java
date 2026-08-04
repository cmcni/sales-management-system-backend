package com.cmcni.sales_management_system_backend.domain.product.service.response;

import com.cmcni.sales_management_system_backend.domain.product.entity.Product;
import com.cmcni.sales_management_system_backend.domain.product_category.entity.ProductCategory;
import com.cmcni.sales_management_system_backend.domain.product_category.service.response.ProductCategoryFindResponse;
import com.cmcni.sales_management_system_backend.domain.product_model.service.response.ProductModelFindResponse;
import com.cmcni.sales_management_system_backend.utility.excel.ExcelColumn;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Getter
@RequiredArgsConstructor
public class ProductExcelExportResponse {
    @ExcelColumn(headerName = "제품군")
    private final String productCategoryPath;

    @ExcelColumn(headerName = "제품명")
    private final String name;

    // 엑셀은 셀 하나에 텍스트만 담을 수 있어서, 중첩 객체인 productModel/productCategory 대신 이름만 뽑아 별도 컬럼으로 둔다.
    @ExcelColumn(headerName = "모델명")
    private final String productModelName;

    @ExcelColumn(headerName = "권장 판매 단가")
    private final Integer recommendedSellingPrice;

    private final ProductModelFindResponse productModel;

    private final ProductCategoryFindResponse productCategory;

    @ExcelColumn(headerName = "비고")
    private final String note;

    @ExcelColumn(headerName = "등록일")
    private final LocalDateTime createdAt;

    public static ProductExcelExportResponse from(Product product) {
        return new ProductExcelExportResponse(
                buildCategoryPath(product.getCategory()),
                product.getName(),
                product.getModel() == null ? null : product.getModel().getName(),
                product.getRecommendedSellingPrice(),
                ProductModelFindResponse.from(product.getModel()),
                ProductCategoryFindResponse.from(product.getCategory()),
                product.getNote(),
                product.getCreatedAt()
        );
    }

    private static String buildCategoryPath(ProductCategory category) {
        LinkedList<String> names = new LinkedList<>();
        for (ProductCategory current = category; current != null; current = current.getParent()) {
            names.addFirst(current.getName());
        }
        return String.join(" > ", names);
    }
}
