package com.cmcni.sales_management_system_backend.utility.excel;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ExcelMetadata {
    private final Map<String, String> excelHeaderNames;
    private final List<String> dataFieldNames;
    private final String sheetName;

    public ExcelMetadata(Map<String, String> excelHeaderNames,
                         List<String> dataFieldNames,
                         String sheetName) {
        this.excelHeaderNames = excelHeaderNames;
        this.dataFieldNames = dataFieldNames;
        this.sheetName = sheetName;
    }

    public String getHeaderName(String fieldName) {
        return excelHeaderNames.getOrDefault(fieldName,"");
    }

}
