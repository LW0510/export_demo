package com.lw.export_demo.excel.core.converter.reader;

import com.lw.export_demo.excel.core.annotation.ExcelColumn;
import com.lw.export_demo.excel.core.cache.WeakCache;
import com.lw.export_demo.excel.core.converter.Converter;
import com.lw.export_demo.excel.utils.StringUtil;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;


/**
 * 读取转换对象
 *
 * created by liuwei
 * date 2019/06/02
 */
public abstract class AbstractReadConverter<R> implements Converter<String, R> {

    protected static WeakCache<String, DateTimeFormatter> dateTimeFormatterWeakCache = new WeakCache<>();

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");

    protected static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public R convert(String obj, Field field) {
        if (StringUtil.isBlank(obj)) {
            return null;
        }
        String trimContent = obj.trim();
        return doConvert(trimContent, field);
    }


    /**
     * 把输入参数进行处理后，转换。
     *
     * @param v     待转换值
     * @param field 待转换值所属字段
     * @return 目标值
     */
    protected abstract R doConvert(String v, Field field);


    /**
     * 取得DateFormatPattern
     *
     * @param field 待转换值所属字段
     * @return 时间格式
     */
    protected String getDateFormatPattern(Field field) {
        ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
        if (Objects.nonNull(excelColumn) && StringUtil.isNotBlank(excelColumn.dateFormatPattern())) {
            return excelColumn.dateFormatPattern();
        }
        return DEFAULT_DATE_FORMAT;
    }

    /**
     * 是否为数值
     *
     * @param v 内容
     * @return true/false
     */
    protected boolean isNumber(String v) {
        return NUMBER_PATTERN.matcher(v).matches();
    }

    /**
     * 获取DateTimeFormatter
     *
     * @param field 字段
     * @return DateTimeFormatter
     */
    protected DateTimeFormatter getDateFormatFormatter(Field field) {
        String dateFormatPattern = getDateFormatPattern(field);
        DateTimeFormatter dateTimeFormatter = dateTimeFormatterWeakCache.get(dateFormatPattern);
        if (Objects.isNull(dateTimeFormatter)) {
            dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatPattern);
            dateTimeFormatterWeakCache.cache(dateFormatPattern, dateTimeFormatter);
        }
        return dateTimeFormatter;
    }
}
