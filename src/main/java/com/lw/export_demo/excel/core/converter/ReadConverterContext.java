package com.lw.export_demo.excel.core.converter;

import com.lw.export_demo.excel.core.converter.reader.*;
import com.lw.export_demo.excel.exception.SaxReadException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;



/**
 * 读取转换器上下文
 *
 * created by liuwei
 * date 2019/06/02
 */
public class ReadConverterContext {

    private static final Map<Class<?>, Converter<String, ?>> READ_CONVERTERS = new HashMap<>();

    static {
        BoolReadConverter boolReadConverter = new BoolReadConverter();
        READ_CONVERTERS.put(Boolean.class, boolReadConverter);
        READ_CONVERTERS.put(boolean.class, boolReadConverter);

        READ_CONVERTERS.put(Date.class, new DateReadConverter());
        READ_CONVERTERS.put(LocalDate.class, new LocalDateReadConverter());
        READ_CONVERTERS.put(LocalDateTime.class, new LocalDateTimeReadConverter());

        NumberReadConverter<Double> doubleReadConverter = NumberReadConverter.of(Double::valueOf);
        READ_CONVERTERS.put(Double.class, doubleReadConverter);
        READ_CONVERTERS.put(double.class, doubleReadConverter);

        NumberReadConverter<Float> floatReadConverter = NumberReadConverter.of(Float::valueOf);
        READ_CONVERTERS.put(Float.class, floatReadConverter);
        READ_CONVERTERS.put(float.class, floatReadConverter);

        NumberReadConverter<Long> longReadConverter = NumberReadConverter.of(Long::valueOf);;
        READ_CONVERTERS.put(Long.class, longReadConverter);
        READ_CONVERTERS.put(long.class, longReadConverter);

        NumberReadConverter<Integer> integerReadConverter = NumberReadConverter.of(Integer::valueOf);
        READ_CONVERTERS.put(Integer.class, integerReadConverter);
        READ_CONVERTERS.put(int.class, integerReadConverter);

        NumberReadConverter<Short> shortReadConverter = NumberReadConverter.of(Short::valueOf);
        READ_CONVERTERS.put(Short.class, shortReadConverter);
        READ_CONVERTERS.put(short.class, shortReadConverter);

        NumberReadConverter<Byte> byteReadConverter = NumberReadConverter.of(Byte::valueOf);
        READ_CONVERTERS.put(Byte.class, byteReadConverter);
        READ_CONVERTERS.put(byte.class, byteReadConverter);

        READ_CONVERTERS.put(BigDecimal.class, new BigDecimalReadConverter());
        READ_CONVERTERS.put(String.class, new StringReadConverter());
    }

    public synchronized ReadConverterContext registering(Class<?> clazz, Converter<String, ?> converter) {
        READ_CONVERTERS.putIfAbsent(clazz, converter);
        return this;
    }

    public static void convert(String content,
                               Field field, Object obj) {
        Converter<String, ?> converter = READ_CONVERTERS.get(field.getType());
        if (Objects.isNull(converter)) {
            throw new IllegalStateException("No suitable type converter was found.");
        }
        Object value = converter.convert(content, field);
        if (Objects.isNull(value)) {
            return;
        }
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new SaxReadException("Failed to set the " + field.getName() + " field value to " + content, e);
        }
    }
}
