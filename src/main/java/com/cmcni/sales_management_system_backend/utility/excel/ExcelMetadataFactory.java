package com.cmcni.sales_management_system_backend.utility.excel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.reflect.FieldUtils.getAllFields;
import static org.springframework.core.annotation.AnnotationUtils.getAnnotation;

public class ExcelMetadataFactory {
    private ExcelMetadataFactory() {}

    private static class SingletonHolder {
        private static final ExcelMetadataFactory INSTANCE = new ExcelMetadataFactory();
    }

    public static ExcelMetadataFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ExcelMetadata createMetadata(Class<?> clazz) {
        Map<String, String> headerNamesMap = new LinkedHashMap<>();
        List<String> dataFieldNamesList = new ArrayList<>();

        for (Field field : getAllFields(clazz)) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                ExcelColumn columnAnnotation = field.getAnnotation(ExcelColumn.class);
                headerNamesMap.put(field.getName(), columnAnnotation.headerName());
                dataFieldNamesList.add(field.getName());
            }
        }

        if (headerNamesMap.isEmpty()) {
            throw new RuntimeException(String.format("Class %s has not @ExcelColumn at all", clazz));
        }

        return new ExcelMetadata(headerNamesMap, dataFieldNamesList, getSheetName(clazz));
    }

    private String getSheetName(Class<?> clazz) {
        ExcelSheet annotation = (ExcelSheet) getAnnotation(clazz, ExcelSheet.class);
        if(annotation != null) {
            return annotation.name();
        }
        return "Users";}

}
