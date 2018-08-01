import java.util.HashMap;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class BiMap<K, V> {

  private HashMap<K, V> keyToValue;
  private HashMap<V, K> valueToKey;

  public BiMap(HashMap<K, V> keyToValue) {
    this.keyToValue = new HashMap<>(keyToValue);
    this.valueToKey = new HashMap<>();

    for (K key : keyToValue.keySet()) {
      valueToKey.put(keyToValue.get(key), key);
    }
  }

  public K getKey(V value) {
    System.out.println("V: " + value);
    System.out.println("K: " + valueToKey.get(value));
    return valueToKey.get(value);
  }

  public V getValue(K key) {
    return keyToValue.get(key);
  }

  public void put(K key, V value) {
    keyToValue.put(key, value);
    valueToKey.put(value, key);
  }

  public Set<K> getKeysSet() {
    return keyToValue.keySet();
  }

  public Set<V> getValuesSet() {
    return valueToKey.keySet();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (K key : getKeysSet().stream().sorted().collect(toSet())) {
      sb.append(key + " : " + getValue(key) + "; ");
    }
    return sb.toString();
  }
}