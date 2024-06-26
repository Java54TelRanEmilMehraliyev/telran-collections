package telran.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements SortedSet<T> {
	private static class Node<T> {
		T data;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T data) {
			this.data = data;
		}

	}

	private class TreeSetIterator implements Iterator<T> {
		Node<T> current = getLeastFrom(root);
		Node<T> prev;

		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prev = current;
			current = getCurrent(current);
			return prev.data;
		}

		@Override
		public void remove() {
			if (prev == null) {
				throw new IllegalStateException();
			}
			removeNode(prev);
			prev = null;
		}

	}

	private static final int DEFAULT_SPACES_PER_LEVEL = 2;
	Node<T> root;
	private Comparator<T> comp;
	private int spacesPerLevel = DEFAULT_SPACES_PER_LEVEL;

	public int getSpacesPerLevel() {
		return spacesPerLevel;
	}

	public void setSpacesPerLevel(int spacesPerLevel) {
		this.spacesPerLevel = spacesPerLevel;
	}

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public T get(T pattern) {
		Node<T> node = getNode(pattern);

		return node == null ? null : node.data;
	}

	private Node<T> getNode(T pattern) {
		Node<T> res = null;
		Node<T> node = getParentOrNode(pattern);
		if (node != null && comp.compare(node.data, pattern) == 0) {
			res = node;
		}
		return res;
	}

	private Node<T> getParentOrNode(T pattern) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes = 0;
		while (current != null && (compRes = comp.compare(pattern, current.data)) != 0) {
			parent = current;
			current = compRes > 0 ? current.right : current.left;
		}
		return current == null ? parent : current;
	}

	public Node<T> getCurrent(Node<T> current) {

		return current.right != null ? getLeastFrom(current.right) : getFirstGreaterParent(current);
	}

	private Node<T> getFirstGreaterParent(Node<T> current) {
		Node<T> parent = current.parent;
		while (parent != null && parent.right == current) {
			current = current.parent;
			parent = current.parent;
		}
		return parent;
	}

	private Node<T> getLeastFrom(Node<T> node) {
		if (node != null) {

			while (node.left != null) {
				node = node.left;
			}
		}
		return node;
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if (obj == null) {
			throw new NullPointerException();
		}
		if (!contains(obj)) {
			res = true;
			Node<T> node = new Node<>(obj);
			if (root == null) {
				addRoot(node);
			} else {
				addWithParent(node);
			}
			size++;
		}
		return res;
	}

	private void addWithParent(Node<T> node) {
		Node<T> parent = getParent(node);
		if (comp.compare(node.data, parent.data) > 0) {
			parent.right = node;
		} else {
			parent.left = node;
		}
		node.parent = parent;

	}

	private Node<T> getParent(Node<T> node) {
		Node<T> parent = getParentOrNode(node.data);
		return parent;
	}

	private void addRoot(Node<T> node) {
		root = node;

	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = getNode(pattern);
		if (node != null) {
			removeNode(node);
			res = true;
		}
		return res;
	}

	private void removeNode(Node<T> node) {
		if (node.left != null && node.right != null) {
			removeJunction(node);
		} else {
			removeNonJunction(node);
		}

		size--;
	}

	private void setNulls(Node<T> node) {
		node.data = null;
		node.parent = node.left = node.right = null;

	}

	private void removeJunction(Node<T> node) {
		Node<T> substitute = getGreatestFrom(node.left);
		node.data = substitute.data;
		removeNonJunction(substitute);

	}

	private void removeNonJunction(Node<T> node) {
		Node<T> parent = node.parent;
		Node<T> child = node.left != null ? node.left : node.right;
		if (parent == null) {
			root = child; // actual root removing
		} else if (node == parent.left) {
			parent.left = child;
		} else {
			parent.right = child;
		}
		if (child != null) {
			child.parent = parent;
		}
		setNulls(node);

	}

	@Override
	public boolean contains(T pattern) {

		return getNode(pattern) != null;
	}

	@Override
	public Iterator<T> iterator() {

		return new TreeSetIterator();
	}

	@Override
	public T first() {

		return root == null ? null : getLeastFrom(root).data;
	}

	@Override
	public T last() {

		return root == null ? null : getGreatestFrom(root).data;
	}

	private Node<T> getGreatestFrom(Node<T> node) {
		if (node != null) {
			while (node.right != null) {
				node = node.right;
			}
		}
		return node;
	}


	@Override
	public T floor(T key) {
		return findClosest(key, true);
	}

	@Override
	public T ceiling(T key) {
		return findClosest(key, false);
	}

	private T findClosest(T key, boolean isFloor) {
		if (key == null) {
			throw new NullPointerException(isFloor ? "Floor" : "Ceilling");
		}

		Node<T> closest = null;
		Node<T> current = root;

		while (current != null) {
			int cmp = comp.compare(key, current.data);
			if (cmp < 0) {
				if (!isFloor)
					closest = current;
				current = current.left;
			} else if (cmp > 0) {
				if (isFloor)
					closest = current;
				current = current.right;
			} else {
				return current.data;
			}
		}
		return closest == null ? null : closest.data;
	}

	/**
	 * display tree in the following form: -20 10 1 -5 100
	 */
	public void displayRootChildren() {
      System.out.println("*****displayRootChildren start*****");
      if(root != null) {
    	  displayRootChildren(root,1);
      }
      System.out.println("*****displayRootChildren end*****");
	}

	private void displayRootChildren(Node<T> node, int level) {
		if(node != null) {
			displayRoot(node, level);
			displayRootChildren(node.left, level + 1);
			displayRootChildren(node.right, level + 1);
		}
		
	}

	/*****************************************/
	/**
	 * conversion of tree so that iterating has been in the inversive order
	 */
	public void treeInversion() {
		inversion(root);
		if (root != null && ((root.left != null || root.right != null))) {
			comp = comp.reversed();
		}
	}

	private void inversion(Node<T> node) {
		if (node != null) {
			swapNode(node);
			inversion(node.left);
			inversion(node.right);
		}
	}

	private void swapNode(Node<T> node) {
		Node<T> temp = node.left;
		node.left = node.right;
		node.right = temp;

	}

	/**
	 * displays tree in the following form 100 10 1 -5 -20
	 */
	public void displayTreeRotated() {
		displayTreeRotated(root, 1);
	}

	private void displayTreeRotated(Node<T> tmpRoot, int level) {
		if (tmpRoot != null) {
			displayTreeRotated(tmpRoot.right, level + 1);
			displayRoot(tmpRoot, level);
			displayTreeRotated(tmpRoot.left, level + 1);
		}

	}

	private void displayRoot(Node<T> tmpRoot, int level) {
		System.out.printf("%s", " ".repeat(level * spacesPerLevel));
		System.out.println(tmpRoot.data);

	}

	/**
	 * 
	 * @return number of leaves (leaf - node with both left and right nulls)
	 */
	public int width() {

		return width(root);
	}

	private int width(Node<T> tmpRoot) {
		int res = 0;
		if (tmpRoot != null) {
			if (tmpRoot.left == null && tmpRoot.right == null) {
				res = 1;
			} else {
				res = width(tmpRoot.left) + width(tmpRoot.right);
			}
		}
		return res;
	}

	/****************************************/
	/**
	 * 
	 * @return number of the nodes of the longest line
	 */
	public int height() {

		return height(root);
	}

	private int height(Node<T> tmpRoot) {
		int res = 0;
		if (tmpRoot != null) {
			int heightLeft = height(tmpRoot.left);
			int heightRight = height(tmpRoot.right);
			res = Math.max(heightLeft, heightRight) + 1;
		}
		return res;
	}

	public void balance() {
		// sorted array of tree nodes
		if (root != null) {
			Node<T>[] arrayNodes = getNodesArray();
			root = balanceArray(arrayNodes, 0, size - 1, null);
		}

	}

	private Node<T> balanceArray(Node<T>[] arrayNodes, int left, int right, Node<T> parent) {
		Node<T> root = null;
		if (left <= right) {
			int indexRoot = (left + right) / 2;
			root = arrayNodes[indexRoot];
			root.parent = parent;
			root.left = balanceArray(arrayNodes, left, indexRoot - 1, root);
			root.right = balanceArray(arrayNodes, indexRoot + 1, right, root);
		}
		return root;
	}

	@SuppressWarnings("unchecked")
	private Node<T>[] getNodesArray() {
		Node<T>[] res = new Node[size];
		Node<T> current = getLeastFrom(root);
		for (int i = 0; i < size; i++) {
			res[i] = current;
			current = getCurrent(current);
		}
		return res;
	}

}