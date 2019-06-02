package com.lw.export_demo.excel.utils;

import com.lw.export_demo.excel.core.cache.Cache;
import com.lw.export_demo.excel.core.cache.WeakCache;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.function.IntSupplier;
import java.util.regex.Pattern;


/**
 * created by liuwei
 * date 2019/06/02
 */
@UtilityClass
public final class TdUtil {

    private static Pattern chineseOrCapitalPattern = Pattern.compile("[\u4e00-\u9fa5|A-Z]");

    private static Pattern digitalPattern = Pattern.compile("^\\d+$");

    private static final Cache<String, Integer> SPAN_CACHE = new WeakCache<>();

    public static int get(IntSupplier firstSupplier, IntSupplier secondSupplier) {
        int firstValue = firstSupplier.getAsInt();
        int secondValue = secondSupplier.getAsInt();
        return firstValue > 0 ? secondValue + firstValue - 1 : secondValue;
    }

    public static int getSpan(String span) {
        Integer cacheResult = SPAN_CACHE.get(span);
        if (Objects.nonNull(cacheResult)) {
            return cacheResult;
        }
        if (!isSpanValid(span)) {
            SPAN_CACHE.cache(span, 0);
            return 0;
        }
        int spanVal = Integer.parseInt(span);
        int result = spanVal > 1 ? spanVal : 0;
        SPAN_CACHE.cache(span, result);
        return result;
    }

    public static boolean isSpanValid(String span) {
        return digitalPattern.matcher(span).find();
    }

    public static int getStringWidth(String s) {
        return getStringWidth(s, 0);
    }

    public static int getStringWidth(String s, double shift) {
        if (Objects.isNull(s)) {
            return 1;
        }
        // 最小为1
        double valueLength = 1;
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为1，否则为0.5
        double chineseOrCapitalShift = 1 + shift;
        double otherShift = 0.5 + shift;
        for (int i = 0, size = s.length(); i < size; i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            if (chineseOrCapitalPattern.matcher(temp).find()) {
                valueLength += chineseOrCapitalShift;
            } else {
                valueLength += otherShift;
            }
        }
        // 进位取整
        return (int) Math.ceil(valueLength);
    }
}
