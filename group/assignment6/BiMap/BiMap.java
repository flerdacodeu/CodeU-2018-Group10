package assignment6Package.BiMap;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class BiMap <K,V> {

    private HashMap<K, V> keyToValue;
    private HashMap<V, K> valueToKey;

    public BiMap(HashMap<K,V> keyToValue)
    {
        this.keyToValue = new HashMap<>(keyToValue);
        valueToKey = new HashMap<>();

        for (K key : keyToValue.keySet()) {
            valueToKey.put(keyToValue.get(key),key);
        }
    }


    public K getKey(V value)
    {
        return valueToKey.get(value);
    }

    public V getValue(K key)
    {
        return keyToValue.get(key);
    }

    public void put(K key, V value)
    {
        keyToValue.put(key,value);
        valueToKey.put(value,key);
    }

    public Set<K> getKeysSet() {
        return keyToValue.keySet();
    }
    public Set<V> getValuesSet() {
        return valueToKey.keySet();
    }

    @Override
    public String  toString()
    {
        return keyToValue.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiMap<?, ?> biMap = (BiMap<?, ?>) o;
        return keyToValue.equals(biMap.keyToValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyToValue);
    }

    public HashMap<K,V> getKeyToValueMap()
    {
        return keyToValue;
    }
}
