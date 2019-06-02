package com.lw.export_demo.excel.core.converter;

import com.lw.export_demo.excel.core.container.Pair;

import java.lang.reflect.Field;


/**
 * created by liuwei
 * date 2019/06/02
 */
public interface WriteConverter {

    /**
     * 转换
     *
     * @param field    字段
     * @param fieldVal 字段对应的值
     * @return T
     */
    Pair<Class, Object> convert(Field field, Object fieldVal);

}
