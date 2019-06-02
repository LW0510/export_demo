package com.lw.export_demo.excel.core.container;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * 并行容器
 *
 * created by liuwei
 * date 2019/06/02
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParallelContainer<T> {
    /**
     * 序号
     */
    int index;

    /**
     * 数据
     */
    T data;

    public ParallelContainer() {

    }

    public ParallelContainer(int index, T data) {
        this.index = index;
        this.data = data;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
