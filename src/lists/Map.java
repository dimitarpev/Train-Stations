package lists;

import java.util.Collection;
import java.util.Set;

public interface Map<K,V> {
    /**
     *- adds the (key,value) combination to the map
     * (or updates the value if the key is already in the map)
     */
     V put(K key, V value);

    /**
     * returns the value coresponding to the key
     * (or null if the key is not in the map)
     */
     V get(K key);

    /**
     * removes the (key,value) combination from the map
     * and returns the value corresponding to the key
     * (or null if the key is not in the map)
     */
     V remove(K key);

    /**
    * - returns the Set of all keys that are in the map
    * */
    Set<K> keySet();

    /**
     * - returns the Collection of all values that are in the map
     */
    Collection<V> values();

    /**
     *- returns the number of (key,value) combinations in the map
     */
    int size();

    /**
     *- returns true if the map has no (key,value) combinations
     */
     boolean isEmpty();
}
