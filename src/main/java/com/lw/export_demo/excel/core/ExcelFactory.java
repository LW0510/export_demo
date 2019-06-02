package com.lw.export_demo.excel.core;

import com.lw.export_demo.excel.core.strategy.AutoWidthStrategy;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * created by liuwei
 * date 2019/06/02
 */
public interface ExcelFactory {

    /**
     * 是否使用默认样式
     *
     * @return ExcelFactory
     */
    ExcelFactory useDefaultStyle();

    /**
     * 窗口冻结
     *
     * @param freezePanes 窗口冻结区域
     * @return ExcelFactory
     */
    ExcelFactory freezePanes(FreezePane... freezePanes);

    /**
     * 设置workbookType为SXSSFWorkbook的内存数据保有量
     *
     * @param rowAccessWindowSize 内存数据保有量
     * @return ExcelFactory
     */
    ExcelFactory rowAccessWindowSize(int rowAccessWindowSize);

    /**
     * 设置workbook类型
     *
     * @param workbookType 工作簿类型
     * @return ExcelFactory
     */
    ExcelFactory workbookType(WorkbookType workbookType);

    /**
     * 自动宽度策略
     *
     * @param autoWidthStrategy 策略
     * @return ExcelFactory
     */
    ExcelFactory autoWidthStrategy(AutoWidthStrategy autoWidthStrategy);

    /**
     * 构建
     *
     * @return workbook
     */
    Workbook build();
}
