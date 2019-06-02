package com.lw.export_demo.excel.core;

import com.lw.export_demo.excel.core.strategy.AutoWidthStrategy;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

/**
 * excel构造器
 *
 * created by liuwei
 * date 2019/06/02
 */
public interface ExcelBuilder {

    /**
     * excel类型
     *
     * @param workbookType workbookType
     * @return ExcelBuilder
     */
    ExcelBuilder workbookType(WorkbookType workbookType);

    /**
     * 设置workbookType为SXSSFWorkbook的内存数据保有量
     *
     * @param rowAccessWindowSize 内存数据保有量
     * @return ExcelBuilder
     */
    ExcelBuilder rowAccessWindowSize(int rowAccessWindowSize);

    /**
     * 使用默认样式
     *
     * @return ExcelBuilder
     */
    ExcelBuilder useDefaultStyle();

    /**
     * 自动宽度策略
     *
     * @param autoWidthStrategy 策略
     * @return ExcelBuilder
     */
    ExcelBuilder autoWidthStrategy(AutoWidthStrategy autoWidthStrategy);

    /**
     * 选择固定区域
     *
     * @param freezePanes 固定区域
     * @return ExcelBuilder
     */
    ExcelBuilder freezePanes(FreezePane... freezePanes);

    /**
     * 设置模板
     *
     * @param path 模板路径
     * @return ExcelBuilder
     */
    ExcelBuilder template(String path);

    /**
     * 构建
     *
     * @param renderData 渲染数据
     * @param <T>        值类型
     * @return Workbook
     */
    <T> Workbook build(Map<String, T> renderData);
}
