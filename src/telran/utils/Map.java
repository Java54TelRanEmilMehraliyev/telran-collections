package telran.utils;
import java.util.Objects;

public interface Map<K, V> {
	public static class Entry<K, V> implements Comparable<Entry<K,V>> {
		private final K key;
		private V value;
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Entry<K,V> o) {
			
			return ((Comparable<K>)key).compareTo(o.key);
		}
		@Override
		public int hashCode() {
			return Objects.hash(key);
		}
		@SuppressWarnings("rawtypes")
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entry other = (Entry) obj;
			return Objects.equals(key, other.key);
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public K getKey() {
			return key;
		}
		
		
		
	}
   V get(K key);
   default V getOrDefault(K key, V defaultValue) {
	   V res = get(key);
	   if (res == null) {
		   res = defaultValue;
	   }
	   return res;
	   
   }
   V put(K key, V value);
   default V putIfAbsent(K key, V value) {
	   V result = get(key);
	   if (result == null) {
		   put(key, value);
	   }
	   return result;
   }
   V remove(K key);
   Set<K> keySet();
   Set<Entry<K, V>> entrySet();
   Collection<V> values();
   }