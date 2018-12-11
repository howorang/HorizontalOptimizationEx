package dmcs.excercise.cache;

public class LRUCache<K, V> extends AbstractCache<K, V> {

    public LRUCache(int capacity) {
        super(capacity, true);
    }
}
