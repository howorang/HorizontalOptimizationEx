package dmcs.excercise.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class AbstractCache<K, V> implements Cache<K, V> {

    public AbstractCache(int capacity, boolean isLRU) {
        int capacity1 = capacity;
        this.cacheMap = new AbstractCache.CacheMap<K, V>(capacity, isLRU);
    }

    protected AbstractCache.CacheMap<K, V> cacheMap;

    public V put(K key, V value) {
        return cacheMap.put(key, value);
    }

    public V get(K key) {
        return cacheMap.get(key);
    }

    public void evictAll() {
        cacheMap.clear();
    }

    public void evictByKey(K key) {
        cacheMap.remove(key);
    }

    private static class CacheMap<K, V> extends LinkedHashMap<K, V> {

        private static final float DEFAULT_LOAD_FACTOR = 0.75f;

        private int capacity;

        CacheMap(int capacity, boolean isLRU) {
            super(capacity, DEFAULT_LOAD_FACTOR, isLRU);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }
}
