package com.lw.export_demo.excel.utils;

import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * created by liuwei
 * date 2019/06/02
 */
@UtilityClass
public final class StringUtil {

    public static String toUpperCaseFirst(String content) {
        if (Objects.isNull(content) || content.isEmpty()) {
            return content;
        }
        if (content.length() == 1) {
            return content.toUpperCase();
        }
        String charAtFirst = content.substring(0, 1);
        return charAtFirst.toUpperCase() + content.substring(1);
    }

    public static boolean isBlank(String content) {
        return Objects.isNull(content) || content.trim().length() == 0;
    }

    public static boolean isNotBlank(String content) {
        return Objects.nonNull(content) && content.trim().length() > 0;
    }

}
