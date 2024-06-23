package telran.utils;

public interface SortedMap<K, V> extends Map<K, V> {
	K firstKey();
	K lastKey();
	K floorKey(K key);
	K ceilingKey(K key);
}
