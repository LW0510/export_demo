package com.lw.export_demo.excel.core.converter.reader;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * BigDecimal读取转换器
 *
 * created by liuwei
 * date 2019/06/02
 */
public class BigDecimalReadConverter extends AbstractReadConverter<BigDecimal> {

    @Override
    public BigDecimal doConvert(String v, Field field) {
        return new BigDecimal(v);
    }
}
