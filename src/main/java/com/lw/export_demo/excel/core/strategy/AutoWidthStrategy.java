package com.lw.export_demo.excel.core.strategy;

import java.util.Objects;

/**
 * created by liuwei
 * date 2019/06/02
 */
public enum AutoWidthStrategy {

    /**
     * 无自动宽度
     */
    NO_AUTO,

    /**
     * 自适应宽度
     */
    AUTO_WIDTH,

    /**
     * 组件调整宽度
     */
    COMPUTE_AUTO_WIDTH,
    /**
     * 自定义宽度
     */
    CUSTOM_WIDTH;

    public static boolean isNoAuto(AutoWidthStrategy autoWidthStrategy) {
        return Objects.equals(autoWidthStrategy, NO_AUTO);
    }

    public static boolean isAutoWidth(AutoWidthStrategy autoWidthStrategy) {
        return Objects.equals(autoWidthStrategy, AUTO_WIDTH);
    }

    public static boolean isComputeAutoWidth(AutoWidthStrategy autoWidthStrategy) {
        return Objects.equals(autoWidthStrategy, COMPUTE_AUTO_WIDTH);
    }

    public static boolean isCustomWidth(AutoWidthStrategy autoWidthStrategy) {
        return Objects.equals(autoWidthStrategy, CUSTOM_WIDTH);
    }
}
