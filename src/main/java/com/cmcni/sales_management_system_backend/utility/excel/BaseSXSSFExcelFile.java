package com.cmcni.sales_management_system_backend.utility.excel;


import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseSXSSFExcelFile implements ExcelFile {
    protected static final int ROW_ACCESS_WINDOW_SIZE = 1000;
    protected static final int ROW_START_INDEX = 0;
    protected static final int COLUMN_START_INDEX = 0;

    protected SXSSFWorkbook workbook;
    protected Sheet sheet;

    public BaseSXSSFExcelFile() {
        this.workbook = new SXSSFWorkbook(ROW_ACCESS_WINDOW_SIZE);
    }

    protected void renderHeaders(ExcelMetadata excelMetadata) {
        sheet = workbook.createSheet(excelMetadata.getSheetName());
        Row row = sheet.createRow(ROW_START_INDEX);
        int columnIndex = COLUMN_START_INDEX;
        CellStyle style = createCellStyle(workbook, true);

        for (String fieldName : excelMetadata.getDataFieldNames()) {
            createCell(row, columnIndex++, excelMetadata.getHeaderName(fieldName), style);
        }
    }

    protected void renderDataLines(ExcelSheetData data, ExcelMetadata metadata) {
        CellStyle style = createCellStyle(workbook, false);
        int rowIndex = ROW_START_INDEX + 1;
        // 헤더와 동일하게 @ExcelColumn이 붙은 필드만, 헤더와 같은 순서로 렌더링해야 컬럼이 어긋나지 않는다.
        List<Field> fields = resolveExcelColumnFields(data.getType(), metadata);

        for (Object record : data.getDataList()) {
            Row row = sheet.createRow(rowIndex++);
            int columnIndex = COLUMN_START_INDEX;
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    createCell(row, columnIndex++, field.get(record), style);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing data field rendering data lines.", e);
            }
        }
    }

    private List<Field> resolveExcelColumnFields(Class<?> type, ExcelMetadata metadata) {
        List<Field> fields = new ArrayList<>();
        for (String fieldName : metadata.getDataFieldNames()) {
            try {
                fields.add(SuperClassReflectionUtils.getField(type, fieldName));
            } catch (Exception e) {
                throw new RuntimeException("Excel export field lookup failed: " + fieldName, e);
            }
        }
        return fields;
    }

    @Override
    public void write(OutputStream stream) throws IOException {
        workbook.write(stream);
    }

    @Override
    public void writeWithEncryption(OutputStream stream, String password) throws IOException {
        if (password == null) {
            write(stream);
        } else {
            POIFSFileSystem fileSystem = new POIFSFileSystem();
            OutputStream encryptorStream = getEncryptorStream(fileSystem, password);
            workbook.write(encryptorStream);
            encryptorStream.close();
            fileSystem.writeFilesystem(stream);
            fileSystem.close();
        }

        workbook.close();
        workbook.dispose();
        stream.close();
    }

    private OutputStream getEncryptorStream(POIFSFileSystem fileSystem, String password) {
        try {
            Encryptor encryptor = new EncryptionInfo(EncryptionMode.agile).getEncryptor();
            encryptor.confirmPassword(password);
            return encryptor.getDataStream(fileSystem);
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException("Failed to obtain encrypted data stream from POIFSFileSystem.");
        }
    }

}
