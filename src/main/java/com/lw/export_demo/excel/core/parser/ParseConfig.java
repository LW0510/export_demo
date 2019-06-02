package com.lw.export_demo.excel.core.parser;

import com.lw.export_demo.excel.core.strategy.AutoWidthStrategy;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * 解析配置
 *
 * created by liuwei
 * date 2019/06/02
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParseConfig {

    AutoWidthStrategy autoWidthStrategy;

    boolean isCustomWidth;

    boolean isComputeAutoWidth;

    public void setAutoWidthStrategy(AutoWidthStrategy autoWidthStrategy) {
        this.autoWidthStrategy = autoWidthStrategy;
        this.isCustomWidth = AutoWidthStrategy.isCustomWidth(autoWidthStrategy);
        this.isComputeAutoWidth = AutoWidthStrategy.isComputeAutoWidth(autoWidthStrategy);
    }

    public AutoWidthStrategy getAutoWidthStrategy() {
        return autoWidthStrategy;
    }

    public boolean isCustomWidth() {
        return isCustomWidth;
    }

    public void setCustomWidth(boolean customWidth) {
        isCustomWidth = customWidth;
    }

    public boolean isComputeAutoWidth() {
        return isComputeAutoWidth;
    }

    public void setComputeAutoWidth(boolean computeAutoWidth) {
        isComputeAutoWidth = computeAutoWidth;
    }
}
