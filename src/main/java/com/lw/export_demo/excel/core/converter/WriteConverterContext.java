package com.lw.export_demo.excel.core.converter;

import com.lw.export_demo.excel.core.container.Pair;
import com.lw.export_demo.excel.core.converter.writer.DateTimeWriteConverter;
import com.lw.export_demo.excel.utils.ReflectUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * created by liuwei
 * date 2019/06/02
 */
public class WriteConverterContext {

    private static final List<WriteConverter> WRITE_CONVERTER_CONTAINER = new ArrayList<>();

    static {
        WRITE_CONVERTER_CONTAINER.add(new DateTimeWriteConverter());
    }

    public static synchronized void registering(WriteConverter... writeConverters) {
        Objects.requireNonNull(writeConverters);
        Collections.addAll(WRITE_CONVERTER_CONTAINER, writeConverters);
    }

    public static Pair<? extends Class, Object> convert(Field field, Object object) {
        Object result = ReflectUtil.getFieldValue(object, field);
        for (WriteConverter writeConverter : WRITE_CONVERTER_CONTAINER) {
            return writeConverter.convert(field, result);
        }
        return null;
    }
}
