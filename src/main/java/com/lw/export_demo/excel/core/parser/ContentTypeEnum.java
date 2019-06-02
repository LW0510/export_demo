package com.lw.export_demo.excel.core.parser;

/**
 * 内容类型枚举
 *
 * created by liuwei
 * date 2019/06/02
 */
public enum ContentTypeEnum {

    STRING,

    BOOLEAN,

    DOUBLE,

    DATE;

    public static boolean isString(ContentTypeEnum contentTypeEnum) {
        return STRING.equals(contentTypeEnum);
    }

    public static boolean isBool(ContentTypeEnum contentTypeEnum) {
        return BOOLEAN.equals(contentTypeEnum);
    }

    public static boolean isDouble(ContentTypeEnum contentTypeEnum) {
        return DOUBLE.equals(contentTypeEnum);
    }

}
