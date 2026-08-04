package com.cmcni.sales_management_system_backend.utility.excel;

import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface ExcelFile {
    DateTimeFormatter EXCEL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    void write(OutputStream stream) throws IOException;
    void writeWithEncryption(OutputStream stream, String password) throws IOException;

    default <T> void createCell(Row row, int column, T value, CellStyle style) {
        if(value == null) return; // avoid NPE

        Cell cell = row.createCell(column);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }
        else if(value instanceof Long) {
            cell.setCellValue((Long) value);
        }
        else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else if (value instanceof LocalDateTime) {
            cell.setCellValue(((LocalDateTime) value).format(EXCEL_DATE_TIME_FORMATTER));
        }
        else {
            // 그 외 타입(String, enum, 커스텀 DTO 등)은 (String) 캐스팅 시 ClassCastException이 나므로 toString()으로 안전하게 변환한다.
            cell.setCellValue(String.valueOf(value));
        }
        cell.setCellStyle(style);

    }

    default CellStyle createCellStyle(Workbook wb, boolean isBold) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(isBold);
        style.setFont(font);
        return style;
    }


}
