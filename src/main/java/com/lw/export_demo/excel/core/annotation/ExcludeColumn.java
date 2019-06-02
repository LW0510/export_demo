package com.lw.export_demo.excel.core.annotation;

import java.lang.annotation.*;

/**
 * created by liuwei
 * date 2019/06/02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ExcludeColumn {
}
