package com.lw.export_demo.excel.core.converter.reader;

import com.lw.export_demo.excel.core.cache.WeakCache;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Date读取转换器
 *
 * created by liuwei
 * date 2019/06/02
 */
public class DateReadConverter extends AbstractReadConverter<Date> {

    private WeakCache<String, SimpleDateFormat> simpleDateFormatWeakCache = new WeakCache<>();

    @Override
    public Date doConvert(String v, Field field) {
        if (isNumber(v)) {
            final long time = Long.parseLong(v);
            return new Date(time);
        }
        String dateFormatPattern = getDateFormatPattern(field);
        SimpleDateFormat sdf = simpleDateFormatWeakCache.get(dateFormatPattern);
        if (Objects.isNull(sdf)) {
            sdf = new SimpleDateFormat(dateFormatPattern);
            simpleDateFormatWeakCache.cache(dateFormatPattern, sdf);
        }
        try {
            return sdf.parse(v);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
