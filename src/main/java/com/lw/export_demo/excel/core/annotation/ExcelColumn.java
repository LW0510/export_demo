package com.lw.export_demo.excel.core.annotation;

import java.lang.annotation.*;

/**
 * created by liuwei
 * date 2019/06/02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ExcelColumn {
    /**
     * 列标题
     *
     * @return 标题
     */
    String title() default "";

    /**
     * 顺序，数值越大越靠后
     *
     * @return int
     */
    int order() default 0;

    /**
     * 列索引，从零开始，不允许重复
     *
     * @return int
     */
    int index() default -1;

    /**
     * 时间格式化，如yyyy-MM-dd HH:mm:ss
     *
     * @return 时间格式化
     */
    String dateFormatPattern() default "";

    /**
     * 分组
     *
     * @return 分组类类型集合
     */
    Class<?>[] groups() default {};

    /**
     * 为null时默认值
     *
     * @return 默认值
     */
    String defaultValue() default "";

    /**
     * 宽度
     *
     * @return 宽度
     */
    int width() default -1;
}
