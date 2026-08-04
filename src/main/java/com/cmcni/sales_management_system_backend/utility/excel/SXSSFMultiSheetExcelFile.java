package com.cmcni.sales_management_system_backend.utility.excel;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;

import java.io.IOException;

public class SXSSFMultiSheetExcelFile extends BaseSXSSFExcelFile{
    public SXSSFMultiSheetExcelFile(ExcelSheetDataGroup dataGroup, HttpServletResponse response) throws IOException {
        this(dataGroup, response, null);
    }

    public SXSSFMultiSheetExcelFile(ExcelSheetDataGroup dataGroup, HttpServletResponse response, @Nullable String password) throws IOException {
        exportExcelFile(dataGroup, response.getOutputStream(), password);
    }

    private void exportExcelFile(ExcelSheetDataGroup dataGroup, ServletOutputStream stream, String password) throws IOException {
        for (ExcelSheetData data : dataGroup.getExcelSheetData()) {
            ExcelMetadata metadata = ExcelMetadataFactory.getInstance().createMetadata(data.getType());
            renderHeaders(metadata);
            renderDataLines(data, metadata);
        }
        writeWithEncryption(stream, password); // if password is null, encryption will not be applied
    }
}
