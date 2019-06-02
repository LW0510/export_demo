package com.lw.export_demo.excel.core.converter.reader;

import com.lw.export_demo.excel.core.constant.Constants;

import java.lang.reflect.Field;
import java.util.Objects;


/**
 * 布尔转换器
 *
 * created by liuwei
 * date 2019/06/02
 */
public class BoolReadConverter extends AbstractReadConverter<Boolean> {

    @Override
    public Boolean doConvert(String v, Field field) {
        if (Objects.equals(Constants.ONE, v) || v.equalsIgnoreCase(Constants.TRUE)) {
            return Boolean.TRUE;
        }
        if (Objects.equals(Constants.ZERO, v) || v.equalsIgnoreCase(Constants.FALSE)) {
            return Boolean.FALSE;
        }
        throw new IllegalStateException("Cell content does not match the type of field to be injected,field is " + field.getName() + ",value is \"" + v + "\"");
    }
}
