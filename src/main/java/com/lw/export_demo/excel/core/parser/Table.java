package com.lw.export_demo.excel.core.parser;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * created by liuwei
 * date 2019/06/02
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Table {

    String caption;

    List<Tr> trList;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<Tr> getTrList() {
        return trList;
    }

    public void setTrList(List<Tr> trList) {
        this.trList = trList;
    }
}
