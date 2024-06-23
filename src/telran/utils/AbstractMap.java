package telran.utils;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    protected Set<Entry<K, V>> set;
    abstract protected Set<K> getEmptyKeySet();
	@Override
	public V get(K key) {
		Entry<K, V> pattern = new Entry<>(key, null);
		Entry<K, V> entry = set.get(pattern);
		V res = null;
		if (entry != null) {
			res = entry.getValue();
		}
		return res;
	}

	@Override
	public V put(K key, V value) {
		Entry<K, V> pattern = new Entry<>(key, null);
		Entry<K, V> entry = set.get(pattern);
		V res = null;
		if (entry == null) {
			set.add(new Entry<K, V>(key, value));
		} else {
			res = entry.getValue();
			entry.setValue(value);
		}
		return res;
	}

	@Override
	public V remove(K key) {
		V res = null;
		Entry<K, V> pattern = new Entry<>(key, null);
		Entry<K, V> entry = set.get(pattern);
		if(entry != null) {
			res = entry.getValue();
			set.remove(entry);
			
		}
		return res;
	}

	@Override
	public Set<K> keySet() {
		Set<K> setKey = getEmptyKeySet();
		set.forEach(e -> setKey.add(e.getKey()));
		return setKey;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		
		return set;
	}

	@Override
	public Collection<V> values() {
		ArrayList<V> res = new ArrayList<>();
		set.forEach(e -> res.add(e.getValue()));
		return res;
	}

}