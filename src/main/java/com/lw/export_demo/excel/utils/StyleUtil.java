package com.lw.export_demo.excel.utils;

import com.lw.export_demo.excel.core.cache.Cache;
import com.lw.export_demo.excel.core.cache.WeakCache;
import lombok.experimental.UtilityClass;
import org.jsoup.nodes.Element;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 样式工具
 *
 * created by liuwei
 * date 2019/06/02
 */
@UtilityClass
public final class StyleUtil {

    private static final Cache<String, Map<String, String>> STYLE_CACHE = new WeakCache<>();

    public static Map<String, String> parseStyle(Element element) {
        String style = element.attr("style");
        if (style.length() == 0) {
            return Collections.emptyMap();
        }
        Map<String, String> cacheResult = STYLE_CACHE.get(style);
        if (Objects.nonNull(cacheResult)) {
            return cacheResult;
        }
        String[] styleArr = style.split(";");

        Map<String, String> result = new HashMap<>(styleArr.length);
        for (int i = 0, length = styleArr.length; i < length; i++) {
            String[] styleDetail = styleArr[i].split(":");
            if (styleDetail.length < 2) {
                continue;
            }
            String styleName = styleDetail[0].trim();
            if (styleName.length() == 0) {
                continue;
            }
            String styleValue = styleDetail[1].trim();
            if (styleValue.length() == 0) {
                continue;
            }
            result.put(styleName, styleValue);
        }
        STYLE_CACHE.cache(style, result);
        return result;
    }

    /**
     * 样式融合
     *
     * @param originStyle 源样式
     * @param targetStyle 目标样式
     * @return 结果
     */
    public static Map<String, String> mixStyle(Map<String, String> originStyle, Map<String, String> targetStyle) {
        if (Objects.isNull(targetStyle) && Objects.isNull(originStyle)) {
            return Collections.emptyMap();
        }
        if (Objects.isNull(targetStyle)) {
            return new HashMap<>(originStyle);
        } else if (Objects.isNull(originStyle)) {
            return new HashMap<>(targetStyle);
        }
        // 相加的两倍，防止扩容。
        Map<String, String> result = new HashMap<>((targetStyle.size() + originStyle.size()) * 2);
        targetStyle.forEach(result::put);
        originStyle.forEach(result::putIfAbsent);
        return result;
    }
}
