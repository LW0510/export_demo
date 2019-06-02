package com.lw.export_demo.excel.core;


import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

/**
 * 简单Excel构造器
 *
 * created by liuwei
 * date 2019/06/02
 */
interface SimpleExcelBuilder extends ExcelBuilder {

    /**
     * 设置sheet名称
     *
     * @param sheetName sheet名称
     * @return DefaultExcelBuilder
     */
    SimpleExcelBuilder sheetName(String sheetName);

    /**
     * 标题设置
     *
     * @param titles 标题集合
     * @return DefaultExcelBuilder
     */
    SimpleExcelBuilder titles(List<String> titles);

    /**
     * 设置字段的展示顺序
     *
     * @param fieldDisplayOrder 展示的字段集合
     * @return DefaultExcelBuilder
     */
    SimpleExcelBuilder fieldDisplayOrder(List<String> fieldDisplayOrder);

    /**
     * 无样式
     *
     * @return SimpleExcelBuilder
     */
    SimpleExcelBuilder noStyle();

    /**
     * 根据指定的数据集合构建，需指明数据集合数据的类类型，使用该方法，如设定了标题但无数据，则标题行也不展示
     *
     * @param data   数据列表
     * @param groups 分组
     * @return Workbook
     */
    Workbook build(List<?> data, Class<?>... groups);

    @Override
    default ExcelBuilder useDefaultStyle() {
        throw new UnsupportedOperationException();
    }

    @Override
    default ExcelBuilder freezePanes(FreezePane... freezePanes) {
        throw new UnsupportedOperationException();
    }

    @Override
    default ExcelBuilder template(String path) {
        throw new UnsupportedOperationException();
    }

    @Override
    default <T> Workbook build(Map<String, T> renderData) {
        throw new UnsupportedOperationException();
    }
}
