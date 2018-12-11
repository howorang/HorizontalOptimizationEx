package dmcs.excercise.cache;

public class LastInsertedCache<K, V> extends AbstractCache<K,V> {

    public LastInsertedCache(int capacity) {
        super(capacity, false);
    }
}
