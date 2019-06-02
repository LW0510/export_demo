package com.lw.export_demo.excel.core;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * created by liuwei
 * date 2019/06/02
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FreezePane {

    /**
     * 从左到右需固定列数
     */
    int colSplit;

    /**
     * 从上到下需固定行数
     */
    int rowSplit;

    public FreezePane() {
    }

    public FreezePane(int rowSplit, int colSplit) {
        this.colSplit = colSplit;
        this.rowSplit = rowSplit;
    }

    public int getColSplit() {
        return colSplit;
    }

    public void setColSplit(int colSplit) {
        this.colSplit = colSplit;
    }

    public int getRowSplit() {
        return rowSplit;
    }

    public void setRowSplit(int rowSplit) {
        this.rowSplit = rowSplit;
    }
}
