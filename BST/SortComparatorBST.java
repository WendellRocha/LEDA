import java.util.Comparator;

import util.Util;

public class SortComparatorBST<T extends Comparable<T>> extends BST<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBST(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		
		while(!this.root.isEmpty()) {
			this.remove(this.root.getData());
		}
		for(int i = 0; i < array.length; i++) {
			this.insert(array[i]);
		}
		
		return this.order();
	}

	@Override
	public T[] reverseOrder() {
		T[] array = Util.makeArrayOfComparable(this.size());
		if (this.isEmpty())
			return array;
		reverseOrder(array, root, 0);
		return array;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int reverseOrder(T[] array, BSTNode<T> node, int i) {
		if (!node.getRight().isEmpty())
			i = reverseOrder(array, (BSTNode) node.getRight(), i);
		
		array[i++] = node.getData();
		
		if (!node.getLeft().isEmpty())
			i = reverseOrder(array, (BSTNode) node.getLeft(), i);

		return i;
		
	}

	@Override
	public BSTNode<T> search(T element) {
		return this.search(root, element);
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		if(element == null || node.isEmpty())
			return node;
		
		if(node.getData().equals(element))
			return node;
		
		if(node.getLeft() != null && element.compareTo(node.getData()) < 0)
			return this.search((BSTNode<T>) node.getLeft(), element);
		else
			return this.search((BSTNode<T>) node.getRight(), element);
	}
	
	@Override
	public void insert(T element) {
		this.insert(root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		if(element != null) {
			if(node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BSTNode<T>());
				node.setRight(new BSTNode<T>());
			} else {
				if(element.compareTo(node.getData()) < 0) {
					this.insert((BSTNode<T>) node.getLeft(), element);
				} else {
					this.insert((BSTNode<T>) node.getRight(), element);
				}
			}
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

    private static <T> T[] makeArrayOfComparable(int size) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Comparable[size];
        return array;
    }
	
}
