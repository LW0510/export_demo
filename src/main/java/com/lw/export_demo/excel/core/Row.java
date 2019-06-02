package com.lw.export_demo.excel.core;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * 行
 *
 * created by liuwei
 * date 2019/06/02
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Row {

    int rowNum;

    public Row(int rowNum) {
        this.rowNum = rowNum;
    }
}
