package com.lw.export_demo.excel.core.converter.reader;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.function.Function;


/**
 * 数值读取转换
 *
 * created by liuwei
 * date 2019/06/02
 */
public class NumberReadConverter<R extends Number> extends AbstractReadConverter<R> {

    private Function<String, R> func;

    private NumberReadConverter(Function<String, R> func) {
        this.func = func;
    }

    @Override
    protected R doConvert(String v, Field field) {
        BigDecimal bigDecimal = new BigDecimal(v);
        String realValue = bigDecimal.toPlainString();
        return func.apply(realValue);

    }

    /**
     * 数字转换器
     *
     * @param func 转换函数
     * @param <R>  目标类型
     * @return 转换器
     */
    public static <R extends Number> NumberReadConverter<R> of(Function<String, R> func) {
        return new NumberReadConverter<>(func);
    }

}
