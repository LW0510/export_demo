package com.lw.export_demo.excel.core.converter.reader;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * LocalDateTime读取转换器
 *
 * created by liuwei
 * date 2019/06/02
 */
public class LocalDateTimeReadConverter extends AbstractReadConverter<LocalDateTime> {

    @Override
    public LocalDateTime doConvert(String v, Field field) {
        if (isNumber(v)) {
            final long time = Long.parseLong(v);
            return LocalDateTime.ofInstant(Instant.ofEpochSecond(time), TimeZone
                    .getDefault().toZoneId());
        }
        DateTimeFormatter dateTimeFormatter = getDateFormatFormatter(field);
        return LocalDateTime.parse(v, dateTimeFormatter);
    }
}
