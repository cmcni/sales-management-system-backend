package com.cmcni.sales_management_system_backend.utility.excel;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;

import java.io.IOException;

public class SXSSFExcelFile extends BaseSXSSFExcelFile {
    public SXSSFExcelFile(ExcelSheetData data, HttpServletResponse response) throws IOException {
        this(data, response, null);
    }

    public SXSSFExcelFile(ExcelSheetData data, HttpServletResponse response, @Nullable String password) throws IOException {
        ExcelMetadata metadata = ExcelMetadataFactory.getInstance().createMetadata(data.getType());
        exportExcelFile(data, metadata, response.getOutputStream(), password);
    }

    private void exportExcelFile(ExcelSheetData data, ExcelMetadata metadata, ServletOutputStream stream, String password) throws IOException {
        renderHeaders(metadata);
        renderDataLines(data, metadata);
        writeWithEncryption(stream, password); // if password is null, encryption will not be applied.
    }

}
