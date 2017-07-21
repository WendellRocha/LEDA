/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

public class BST<T extends Comparable<T>> implements BST<T> {

    protected BSTNode<T> root;

    public BST() {
        root = new BSTNode<T>();
    }

    public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.height(root);
	}
	
	protected int height(BSTNode<T> node) {
		if (node.isEmpty())
			return -1;
		else
			return Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight())) + 1;
	}

	@Override
	public BSTNode<T> search(T element) {
		return this.search(root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (element == null || node.isEmpty())
			return node;

		if (node.getData().equals(element))
			return node;

		if (node.getLeft() != null && element.compareTo(node.getData()) < 0)
			return this.search((BSTNode<T>) node.getLeft(), element);
		else
			return this.search((BSTNode<T>) node.getRight(), element);
	}

	@Override
	public void insert(T element) {
		this.insert(root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		if (element != null) {
			if (node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BSTNode<T>());
				node.setRight(new BSTNode<T>());
				node.getLeft().setParent(node);
				node.getRight().setParent(node);
			} else {
				if (element.compareTo(node.getData()) < 0) {
					this.insert((BSTNode<T>) node.getLeft(), element);
				} else {
					this.insert((BSTNode<T>) node.getRight(), element);
				}
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return this.maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty())
			return null;

		if (node.getRight().isEmpty())
			return node;
		else
			return this.maximum((BSTNode<T>) node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		return this.minimum(root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty())
			return null;

		if (node.getLeft().isEmpty())
			return node;
		else
			return this.minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);

		if (node.isEmpty())
			return null;

		if (!node.getRight().isEmpty())
			return minimum((BSTNode<T>) node.getRight());

		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null && node.equals(parent.getRight())) {
			node = parent;
			parent = (BSTNode<T>) parent.getParent();
		}
		
		return parent;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);

		if (node.isEmpty())
			return null;

		if (!node.getLeft().isEmpty())
			return maximum((BSTNode<T>) node.getLeft());

		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null && node.equals(parent.getLeft())) {
			node = parent;
			parent = (BSTNode<T>) parent.getParent();
		}
		
		return parent;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = this.search(element);

		if (node.isEmpty())
			return;
		else
			this.remove(node);
	}
	
	protected void remove(BSTNode<T> node) {
		if (node.isEmpty())
			return;

		if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			BSTNode<T> novoNode = sucessor(node.getData());
			node.setData(novoNode.getData());
			remove(novoNode);
		} else {
			BSTNode<T> novoNode = (BSTNode<T>) node.getLeft();

			if (novoNode.isEmpty())
				novoNode = (BSTNode<T>) node.getRight();

			node.setData(novoNode.getData());
			node.setRight(novoNode.getRight());
			node.setLeft(novoNode.getLeft());

			if (!node.isEmpty() && node.getRight() != null)
				node.getRight().setParent(node);
			if (!node.isEmpty() && node.getLeft() != null)
				node.getLeft().setParent(node);
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = Util.makeArrayOfComparable(this.size());

		if (this.isEmpty())
			return array;
		else
			preOrder(array, root, 0);
		return array;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int preOrder(T[] array, BSTNode<T> node, int i) {
		array[i++] = node.getData();

		if (!node.getLeft().isEmpty())
			i = preOrder(array, (BSTNode) node.getLeft(), i);

		if (!node.getRight().isEmpty())
			i = preOrder(array, (BSTNode) node.getRight(), i);

		return i;
	}

	@Override
	public T[] order() {
		T[] array = Util.makeArrayOfComparable(this.size());

		if (this.isEmpty())
			return array;
		else
			order(array, root, 0);
		return array;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int order(T[] array, BSTNode<T> node, int i) {
		if (!node.getLeft().isEmpty())
			i = order(array, (BSTNode) node.getLeft(), i);

		array[i++] = node.getData();

		if (!node.getRight().isEmpty())
			i = order(array, (BSTNode) node.getRight(), i);
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] array = Util.makeArrayOfComparable(this.size());

		if (this.isEmpty())
			return array;
		else
			postOrder(array, root, 0);
		return array;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int postOrder(T[] array, BSTNode<T> node, int i) {
		if (!node.getLeft().isEmpty())
			i = postOrder(array, (BSTNode) node.getLeft(), i);

		if (!node.getRight().isEmpty())
			i = postOrder(array, (BSTNode) node.getRight(), i);

		array[i++] = node.getData();
		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}
    
    private static <T> T[] makeArrayOfComparable(int size) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Comparable[size];
        return array;
    }
}
