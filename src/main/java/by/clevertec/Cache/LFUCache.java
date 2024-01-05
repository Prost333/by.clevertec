package by.clevertec.Cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache<K,V> implements Cache<K,V>{
    private final Map<K, V> values;
    private final Map<K, Integer> counts;
    private final Map<Integer, LinkedHashSet<K>> lists;
    private int min = -1;
    private final int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        values = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    @Override
    public synchronized V get(K key) {
        if (!values.containsKey(key))
            return null;
        int count = counts.get(key);
        counts.put(key, count + 1);
        lists.get(count).remove(key);
        if (count == min && lists.get(count).isEmpty())
            min++;
        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet<>());
        lists.get(count + 1).add(key);
        return values.get(key);
    }

    @Override
    public synchronized void put(K key, V value) {
        if (capacity <= 0)
            return;
        if (values.containsKey(key)) {
            values.put(key, value);
            get(key);
            return;
        }
        if (values.size() >= capacity) {
            K evict = lists.get(min).iterator().next();
            lists.get(min).remove(evict);
            values.remove(evict);
        }
        values.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }

    @Override
    public synchronized void delete(K key) {
        if (!values.containsKey(key)) return;
        int count = counts.get(key);
        lists.get(count).remove(key);
        if (count == min && lists.get(count).isEmpty()) {
            lists.remove(count);
            min++;
        }
        values.remove(key);
        counts.remove(key);
    }

    @Override
    public Collection<V> findAll() {
        return values.values();
    }
}
