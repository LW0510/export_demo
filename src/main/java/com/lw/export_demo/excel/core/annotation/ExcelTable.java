package com.lw.export_demo.excel.core.annotation;



import com.lw.export_demo.excel.core.WorkbookType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by liuwei
 * date 2019/06/02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface ExcelTable {

    /**
     * 创建的excel包含所有字段
     *
     * @return true/false
     */
    boolean includeAllField() default true;

    /**
     * 是否忽略父类属性
     *
     * @return true/false
     */
    boolean excludeParent() default false;

    /**
     * 工作簿类型，.xls、.xlsx
     *
     * @return WorkbookType
     */
    com.lw.export_demo.excel.core.WorkbookType workbookType() default WorkbookType.XLSX;

    /**
     * sheeName
     *
     * @return sheeName
     */
    String sheetName() default "";

    /**
     * 内存行数保有量，只在WorkbookType.SXLSX有效
     *
     * @return 行数
     */
    int rowAccessWindowSize() default -1;

    /**
     * 是否使用字段名称作为标题
     *
     * @return true/false
     */
    boolean useFieldNameAsTitle() default false;

    /**
     * 为null时默认值
     *
     * @return 默认值
     */
    String defaultValue() default "";
}
