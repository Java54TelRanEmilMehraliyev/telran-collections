package telran.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
	List<T>[] hashTable;
	int size;
	float factor;

	private class HashSetIterator implements Iterator<T> {		
		private int index;
		private Iterator<T> listIterator;

		public HashSetIterator() {
			advanceIndex();
		}
		
		private void advanceIndex() {
			while (index < hashTable.length &&
				(hashTable[index] == null || hashTable[index].size()==0)) {
				index++;
			}
			if (index < hashTable.length) {
				listIterator = hashTable[index].iterator();
			}
		}
		@Override
		public boolean hasNext() {
			boolean hasNext = true;
			if(listIterator == null || !listIterator.hasNext()) {
				index++;
				advanceIndex();
				hasNext = listIterator != null && listIterator.hasNext();
			}
			return hasNext;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T nextElement = listIterator.next();
			if (!listIterator.hasNext()) {
				index++;
				advanceIndex();
			}
			return nextElement;
		}
		
	}

	public HashSet(int hashTableLength, float factor) {
		hashTable = new List[hashTableLength];
		this.factor = factor;
	}

	public HashSet() {
		this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if (!contains(obj)) {
			if ((float) size / hashTable.length >= factor) {
				hashTableReallocation();
			}
			addObjInHashTable(obj, hashTable);
			size++;
			res = true;
		}

		return res;
	}

	private void hashTableReallocation() {
		List<T>[] tmp = new List[hashTable.length * 2];
		for (List<T> list : hashTable) {
			if (list != null) {
				for (T obj : list) {
					addObjInHashTable(obj, tmp);
				}
			}
		}
		hashTable = tmp;

	}

	private void addObjInHashTable(T obj, List<T>[] table) {
	    int hashCode = obj.hashCode();
	    int index = Math.abs(hashCode % table.length); 
	    List<T> list = table[index];
	    if (list == null) {
	        list = new LinkedList<>();
	        table[index] = list;
	    }
	    list.add(obj);
	}

	private int getIndex(T obj,List<T> [] lists) {
		int hashCode = obj.hashCode();
		int index = Math.abs(hashCode % lists.length);
		return index;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = contains(pattern);
		if(res) {
			int index = getIndex(pattern, hashTable);
			hashTable[index].remove(pattern);
			size--;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getIndex(pattern,hashTable);
		List<T> list = hashTable[index];
		return list != null && list.contains(pattern);
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public Iterator<T> iterator() {

		return new HashSetIterator();
	}

	@Override
	public T get(T pattern) {
		int index = getIndex(pattern, hashTable);
		T res = null;
		List<T> list = hashTable[index];
		if (list != null) {
			int lIndex = list.indexOf(pattern);
			if(lIndex > -1) {
				res = list.get(lIndex);
			}
		}
		return res;
	}

}