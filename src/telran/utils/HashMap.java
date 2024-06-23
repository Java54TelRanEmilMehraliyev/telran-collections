package telran.utils;

public class HashMap<K, V> extends AbstractMap<K, V> {

	@Override
	protected Set<K> getEmptyKeySet() {
		
		return new HashSet<K>();
	}
	public HashMap() {
		set = new HashSet<Entry<K, V>>();
	}

}