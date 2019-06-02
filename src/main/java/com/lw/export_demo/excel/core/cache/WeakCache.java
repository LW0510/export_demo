package com.lw.export_demo.excel.core.cache;


/**
 * created by liuwei
 * date 2019/06/02
 */
import java.util.WeakHashMap;

public class WeakCache<K, V> implements Cache<K, V> {

    private WeakHashMap<K, V> cacheMap;

    public WeakCache() {
        cacheMap = new WeakHashMap<>();
    }

    public WeakCache(int mapSize) {
        cacheMap = new WeakHashMap<>(mapSize);
    }

    @Override
    public synchronized void cache(K key, V value) {
        cacheMap.put(key, value);
    }

    @Override
    public V get(K key) {
        return cacheMap.get(key);
    }

    @Override
    public synchronized void clearAll() {
        cacheMap.clear();
    }
}
