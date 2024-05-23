package telran.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private int size;
	private T[] array;
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	private class ArrayListIterator implements Iterator<T> {
        int current = 0;
	
        @Override
		public boolean hasNext() {
			
			return current < size;
		}

		@Override
		public T next() {
            while(!hasNext()) {
            	throw new NoSuchElementException();
            }
			return array[current++];
		}
		
	}
	@Override
	/**
	 * adds object at end of array, that is, at index == size
	 */
	public boolean add(T obj) {
		if (size == array.length) {
			allocate();
		}
		array[size++] = obj;
		return true;
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
		
	}
	@Override
	public boolean remove(T pattern) {
		int index = indexOf(pattern);
		boolean res = false;
		if (index > -1) {
			res = true;
			remove(index);
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		
		return indexOf(pattern) >= 0;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {

		return new ArrayListIterator();
	}

	@Override
	public T get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	public void add(int index, T obj) {
		try {
			if( index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			}
			if (size == array.length) {
				allocate();
			}
			System.arraycopy(array,index,array,index + 1, size - index);
		    array[index] = obj;
		    size++;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error! index of element to insert out of diapason");
			
		}

	}

	@Override
	public T remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		T removedElement = array[index];
		System.arraycopy(array, index +1, array, index, size - index -1);
		size--;
		return removedElement;
	}

	@Override
	public int indexOf(T pattern) {
		for(int index = 0; index < size;index++) {
			if(pattern.equals(array[index])) {
				return index;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		for(int index = size -1; index >= 0;index--) {
			if(pattern.equals(array[index])) {
				return index;
			}
		}
		return -1;
	}
}