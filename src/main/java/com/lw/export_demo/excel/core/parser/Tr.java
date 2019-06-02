package com.lw.export_demo.excel.core.parser;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

/**
 * created by liuwei
 * date 2019/06/02
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tr {

    /**
     * 索引
     */
    int index;
    /**
     * 行单元格
     */
    List<Td> tdList;
    /**
     * 最大宽度
     */
    Map<Integer, Integer> colWidthMap;
    /**
     * 是否可见
     */
    boolean visibility = true;

    public Tr(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Td> getTdList() {
        return tdList;
    }

    public void setTdList(List<Td> tdList) {
        this.tdList = tdList;
    }

    public Map<Integer, Integer> getColWidthMap() {
        return colWidthMap;
    }

    public void setColWidthMap(Map<Integer, Integer> colWidthMap) {
        this.colWidthMap = colWidthMap;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
