package com.lw.export_demo.excel.core.converter.reader;

import com.lw.export_demo.excel.core.converter.Converter;

import java.lang.reflect.Field;


/**
 * String读取转换
 *
 * created by liuwei
 * date 2019/06/02
 */
public class StringReadConverter implements Converter<String, String> {

    @Override
    public String convert(String obj, Field field) {
        return obj;
    }
}
