package telran.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
	Node<T> head;
	Node<T> tail;
	int size;

	private static class Node<T> {
		T data;
		Node<T> prev;
		Node<T> next;

		Node(T data) {
			this.data = data;
		}
	}
	
	private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;
        
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T data = current.data;
			current = current.next;
			return data;
		}
		
	}
	

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		addNode(size, node);
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> current = head;
		while (current != null) {
			if (current.data.equals(pattern)) {
				if (current == head) {
					head = current.next;
				} else {
					current.prev.next = current.next;
					current.next.prev = current.prev;
				}
				size--;
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		Node<T> current = head;
		while (current != null) {
			if (current.data.equals(pattern)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
        return new LinkedListIterator();
	}

	@Override
	public T get(int index) {
        if(index < 0 || index >= size) {
        	throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for( int i = 0; i < index; i++) {
        	current = current.next;
        }
		return current.data;
	}

	@Override
	public void add(int index, T obj) {
		List.checkIndex(index, size, false);
		Node<T> node = new Node<>(obj);
		addNode(index, node);

	}

	@Override
	public T remove(int index) {
	   if(index < 0 || index >= size) {
		   throw new IndexOutOfBoundsException();
	   }
	   Node<T> current = head;
	   for(int i = 0; i < index; i++) {
		   current = current.next;
	   }
	   T data = current.data;
	   if(current == head) {
		   head = current.next;
	   } else {
		   current.prev.next = current.next;
		   if(current.next != null) {
			   current.next.prev = current.prev;
		   }
	   }
	   size--;
		return data;
	}

	@Override
	public int indexOf(T pattern) {
		Node<T> current = head;
		for(int index = 0; index < size; index++ ) {
			if(current.data.equals(pattern)) {
				return index;
			}
			current = current.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		Node<T> current = tail;
		for(int index = size -1; index >= 0; index--) {
			if(current.data.equals(pattern)) {
				return index;
			}
			current = current.prev;
		}
		return -1;
	}

	private Node<T> getNode(int index) {
		return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
	}

	private Node<T> getNodeFromTail(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromHead(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private void addNode(int index, Node<T> node) {
		if (index == 0) {
			addHead(node);
		} else if (index == size) {
			addTail(node);
		} else {
			addMiddle(node, index);
		}
		size++;
	}

	private void addMiddle(Node<T> node, int index) {
		Node<T> nodeNext = getNode(index);
		Node<T> nodePrev = nodeNext.prev;
		nodeNext.prev = node;
		nodePrev.next = node;
		node.prev = nodePrev;
		node.next = nodeNext;

	}

	private void addTail(Node<T> node) {
		// head cannot be null
		tail.next = node;
		node.prev = tail;
		tail = node;

	}

	private void addHead(Node<T> node) {
		if (head == null) {
			head = tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}

	}
}