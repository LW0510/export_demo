package com.lw.export_demo.excel.core.cache;

/**
 * 缓存接口
 *
 * created by liuwei
 * date 2019/06/02
 */
public interface Cache<E, T> {

    void cache(E key, T value);

    T get(E key);

    void clearAll();
}
