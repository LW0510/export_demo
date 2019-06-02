package com.lw.export_demo.excel.core.converter.reader;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


/**
 * LocalDate读取转换器
 *
 * created by liuwei
 * date 2019/06/02
 */
public class LocalDateReadConverter extends AbstractReadConverter<LocalDate> {

    @Override
    public LocalDate doConvert(String v, Field field) {
        if (isNumber(v)) {
            final long time = Long.parseLong(v);
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time), TimeZone
                    .getDefault().toZoneId());
            return localDateTime.toLocalDate();
        }
        DateTimeFormatter dateTimeFormatter = getDateFormatFormatter(field);
        return LocalDate.parse(v, dateTimeFormatter);
    }
}
