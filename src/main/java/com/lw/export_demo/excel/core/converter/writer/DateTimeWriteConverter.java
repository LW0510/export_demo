package com.lw.export_demo.excel.core.converter.writer;

import com.lw.export_demo.excel.core.annotation.ExcelColumn;
import com.lw.export_demo.excel.core.cache.Cache;
import com.lw.export_demo.excel.core.cache.WeakCache;
import com.lw.export_demo.excel.core.container.Pair;
import com.lw.export_demo.excel.core.converter.WriteConverter;
import com.lw.export_demo.excel.utils.StringUtil;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * created by liuwei
 * date 2019/06/02
 */
public class DateTimeWriteConverter implements WriteConverter {

    private static final Cache<String, DateTimeFormatter> DATETIME_FORMATTER_CONTAINER = new WeakCache<>();

    @Override
    public Pair<Class, Object> convert(Field field, Object fieldVal) {
        Class<?> fieldType = field.getType();
        if (fieldType != LocalDateTime.class && fieldType != LocalDate.class && fieldType != Date.class) {
            return Pair.of(fieldType, fieldVal);
        }
        ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
        if (Objects.isNull(excelColumn) || Objects.isNull(fieldVal)) {
            return Pair.of(fieldType, fieldVal);
        }
        // 时间格式化
        String dateFormatPattern = excelColumn.dateFormatPattern();
        if (StringUtil.isBlank(dateFormatPattern)) {
            return Pair.of(fieldType, fieldVal);
        }
        if (fieldType == LocalDateTime.class) {
            LocalDateTime localDateTime = (LocalDateTime) fieldVal;
            DateTimeFormatter formatter = getDateTimeFormatter(dateFormatPattern);
            return Pair.of(String.class, formatter.format(localDateTime));
        } else if (fieldType == LocalDate.class) {
            LocalDate localDate = (LocalDate) fieldVal;
            DateTimeFormatter formatter = getDateTimeFormatter(dateFormatPattern);
            return Pair.of(String.class, formatter.format(localDate));
        }
        Date date = (Date) fieldVal;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatPattern);
        return Pair.of(String.class, simpleDateFormat.format(date));
    }

    /**
     * 获取时间格式化
     *
     * @param dateFormat 时间格式化
     * @return DateTimeFormatter
     */
    private DateTimeFormatter getDateTimeFormatter(String dateFormat) {
        DateTimeFormatter formatter = DATETIME_FORMATTER_CONTAINER.get(dateFormat);
        if (Objects.isNull(formatter)) {
            formatter = DateTimeFormatter.ofPattern(dateFormat);
            DATETIME_FORMATTER_CONTAINER.cache(dateFormat, formatter);
        }
        return formatter;
    }
}
