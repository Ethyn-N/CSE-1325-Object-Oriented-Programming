package emporium;

import java.util.HashMap;
import java.util.HashSet;

public class MultiMap<K, V> {
    public void put(K key, V value) {
        if (map.get(key) == null)
            map.put(key, new HashSet<V>());
        map.get(key).add(value);
    }

    public Object[] get(K key) {
        if (map.get(key) == null)
            return new Object[]{};
        else
            return map.get(key).toArray();
    }

    private HashMap<K, HashSet<V>> map = new HashMap<>();
}