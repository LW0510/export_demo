package com.lw.export_demo.excel.core.container;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


/**
 * 键值对容器对象. immutable。
 *
 * created by liuwei
 * date 2019/06/02
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pair<K, V> {

    final K key;

    final V value;

    private Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }


    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
