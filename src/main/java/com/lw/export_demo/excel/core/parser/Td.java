package com.lw.export_demo.excel.core.parser;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Map;

/**
 * created by liuwei
 * date 2019/06/02
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Td {
    /**
     * 所在行
     */
    int row;
    /**
     * 所在列
     */
    int col;
    /**
     * 跨行数
     */
    int rowSpan;
    /**
     * 跨列数
     */
    int colSpan;
    /**
     * 内容
     */
    String content;
    /**
     * 内容类型
     */
    ContentTypeEnum tdContentType = ContentTypeEnum.STRING;
    /**
     * 是否为th
     */
    boolean th;
    /**
     * 行边界
     */
    int rowBound;
    /**
     * 列边界
     */
    int colBound;
    /**
     * 单元格样式
     */
    Map<String, String> style;
    /**
     * 公式
     */
    boolean formula;


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentTypeEnum getTdContentType() {
        return tdContentType;
    }

    public void setTdContentType(ContentTypeEnum tdContentType) {
        this.tdContentType = tdContentType;
    }

    public boolean isTh() {
        return th;
    }

    public void setTh(boolean th) {
        this.th = th;
    }

    public int getRowBound() {
        return rowBound;
    }

    public void setRowBound(int rowBound) {
        this.rowBound = rowBound;
    }

    public int getColBound() {
        return colBound;
    }

    public void setColBound(int colBound) {
        this.colBound = colBound;
    }

    public Map<String, String> getStyle() {
        return style;
    }

    public void setStyle(Map<String, String> style) {
        this.style = style;
    }

    public boolean isFormula() {
        return formula;
    }

    public void setFormula(boolean formula) {
        this.formula = formula;
    }
}
