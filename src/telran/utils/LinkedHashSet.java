package telran.utils;

import java.util.Iterator;

import telran.utils.LinkedList.Node;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	HashMap<T, Node<T>> map = new HashMap<>();
	LinkedList<T> list = new LinkedList<>();
	private class LinkedHashSetIterator implements Iterator<T> {
		Iterator<T> it = list.iterator();
		T iterated;
		@Override
		public boolean hasNext() {
			
			return it.hasNext();
		}

		@Override
		public T next() {
			iterated = it.next();
			return iterated;
		}
		@Override
		public void remove() {
			it.remove();
			map.remove(iterated);
			size--;
		}
		
	}
	@Override
	public T get(T pattern) {
		Node<T> node = map.get(pattern);
		
		return node == null ? null : node.data;
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if(!contains(obj)) {
			res = true;
			Node<T> node = new Node<T>(obj);
			map.put(obj, node);
			list.addNode(size++, node);
		}
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> node = map.get(pattern);
		boolean res = false;
		if (node != null) {
			res = true;
			list.removeNode(node);
			map.remove(pattern);
			size--;
		}
		
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		
		return map.get(pattern) != null;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new LinkedHashSetIterator();
	}

}