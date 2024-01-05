import by.clevertec.Cache.LRUCache;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTest {
    @Test
    public void testPutAndGet() {
        LRUCache<String, Integer> cache = new LRUCache<>(2);
        cache.put("one", 1);
        assertEquals(1, cache.get("one"));
        cache.put("two", 2);
        assertEquals(2, cache.get("two"));
        cache.put("three", 3);
        assertNull(cache.get("one"));
        assertEquals(3, cache.get("three"));
        assertEquals(2, cache.get("two"));
    }

    @Test
    public void testDelete() {
        LRUCache<String, Integer> cache = new LRUCache<>(2);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.delete("one");
        assertNull(cache.get("one"));
        assertEquals(2, cache.get("two"));
    }
}