package dmcs.excercise.cache;

public class CacheTest {
    public static void main(String[] args) {
        LRUCache<Integer, String> lruCache = new LRUCache<Integer, String>(3);
        lruCache.put(1, "one");
        lruCache.put(2, "two");
        lruCache.put(3, "three");
        lruCache.get(1);
        lruCache.get(1);
        lruCache.get(2);
        lruCache.put(4, "four");
        System.out.println("Done");

        LastInsertedCache<Integer, String> lastInsertedCache = new LastInsertedCache<Integer, String>(3);
        lastInsertedCache.put(1, "one");
        lastInsertedCache.put(2, "two");
        lastInsertedCache.put(3, "three");
        lastInsertedCache.get(1);
        lastInsertedCache.get(1);
        lastInsertedCache.get(2);
        lastInsertedCache.put(4, "four");
        System.out.println("Done");
    }

}
