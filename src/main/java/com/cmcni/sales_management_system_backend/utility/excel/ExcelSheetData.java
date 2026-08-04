package com.cmcni.sales_management_system_backend.utility.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ExcelSheetData {
    private final List<?> dataList;
    private final Class<?> type;

    public static ExcelSheetData of(List<?> dataList, Class<?> type) {
        return new ExcelSheetData(dataList, type);
    }
}
