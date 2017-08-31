import BST;

public class RB<T extends Comparable<T>> extends BST<T> implements RBTree<T> {

	public RB() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) this.root);
	}

	private int blackHeight(RBNode<T> node) {
		if (node.isEmpty())
			return 1;

		int height;

		if (verifyColour(node))
			height = 1;
		else
			height = 0;

		return height + Math.max(blackHeight((RBNode<T>) node.getRight()), blackHeight((RBNode<T>) node.getLeft()));
	}

	private boolean verifyColour(RBNode<T> node) {
		if (node != null)
			return node.getColour() == Colour.BLACK;
		else
			return true;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes((RBNode<T>) this.getRoot());
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		if (node.isEmpty())
			return true;

		if (!this.verifyColour(node))
			if (!this.verifyColour((RBNode<T>) node.getRight()) || !this.verifyColour((RBNode<T>) node.getLeft()))
				return false;

		return this.verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
				&& this.verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		int left = verifyBlackHeight((RBNode<T>) this.getRoot().getLeft(), 0);
		int right = verifyBlackHeight((RBNode<T>) this.getRoot().getRight(), 0);

		if (right == left)
			return true;
		else
			return false;
	}

	private int verifyBlackHeight(RBNode<T> node, int i) {
		if (node == null || node.isEmpty())
			return i++;

		if (this.verifyColour(node))
			i++;

		return Math.max(verifyBlackHeight((RBNode<T>) node.getRight(), i),
				verifyBlackHeight((RBNode<T>) node.getLeft(), i));
	}

	@Override
	public void insert(T value) {
		if (value != null)
			this.insert((RBNode<T>) this.getRoot(), value);
	}

	private void insert(RBNode<T> node, T value) {
		if (node.isEmpty()) {

			node.setData(value);
			node.setColour(Colour.RED);
			node.setLeft(new RBNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new RBNode<T>());
			node.getRight().setParent(node);

			fixUpCase1(node);

		} else {
			int comparation = node.getData().compareTo(value);

			if (comparation > 0)
				insert((RBNode<T>) node.getLeft(), value);
			else if (comparation < 0)
				insert((RBNode<T>) node.getRight(), value);
		}

	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		return this.rbPreOrder((RBNode<T>) this.getRoot());
	}

	@SuppressWarnings("unchecked")
	private RBNode<T>[] rbPreOrder(RBNode<T> node) {
		RBNode<T>[] array = new RBNode[0];

		if (node.isEmpty())
			return array;

		RBNode<T>[] left = this.rbPreOrder((RBNode<T>) node.getLeft());
		RBNode<T>[] right = this.rbPreOrder((RBNode<T>) node.getRight());

		array = (RBNode<T>[]) new RBNode[1 + left.length + right.length];
		int index = 0;
		array[index++] = node;

		for (RBNode<T> element : left)
			array[index++] = element;
		for (RBNode<T> element : right)
			array[index++] = element;

		return array;

	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(this.getRoot()))
			node.setColour(Colour.BLACK);
		else
			this.fixUpCase2(node);
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (!this.verifyColour((RBNode<T>) node.getParent()))
			this.fixUpCase3(node);
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> uncle;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();

		if (this.isLeftChild(parent))
			uncle = (RBNode<T>) grandParent.getRight();
		else
			uncle = (RBNode<T>) grandParent.getLeft();

		if (!this.verifyColour(uncle)) {
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			grandParent.setColour(Colour.RED);
			this.fixUpCase1(grandParent);
		} else {
			this.fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;

		if (!this.isLeftChild(node) && this.isLeftChild((RBNode<T>) node.getParent())) {
			Util.leftRotation((BSTNode<T>) node.getParent());
			next = (RBNode<T>) node.getLeft();
		} else if (this.isLeftChild(node) && !this.isLeftChild((RBNode<T>) node.getParent())) {
			Util.rightRotation((BSTNode<T>) node.getParent());
			next = (RBNode<T>) node.getRight();
		}
		this.fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();

		parent.setColour(Colour.BLACK);
		grandParent.setColour(Colour.RED);

		if (this.isLeftChild(node))
			Util.rightRotation(grandParent);
		else
			Util.leftRotation(grandParent);
	}

	private boolean isLeftChild(RBNode<T> node) {
		if (node.getParent().getLeft().equals(node))
			return true;
		return false;
    }
    
    /**
  	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
  	 * @param bstNode
  	 * @return
  	 */
  	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> bstNode) {
        BSTNode<T> rNode = (BSTNode<T>) bstNode.getRight();
        rNode.setParent(bstNode.getParent());

        BSTNode<T> rParent = (BSTNode<T>) rNode.getParent();

        if (rParent != null) {
            if (rParent.getLeft().equals(bstNode)) {
                rParent.setLeft(rNode);
            } else {
                rParent.setRight(rNode);
            }
        }

        bstNode.setRight(rNode.getLeft());
        rNode.getLeft().setParent(bstNode);
        rNode.setLeft(bstNode);
        bstNode.setParent(rNode);

        return rNode;
    }

    /**
     * A rotacao a direita em node deve subir e retornar seu filho a esquerda
     * @param node
     * @return
     */
    public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
        BSTNode<T> lNode = (BSTNode<T>) node.getLeft();
        lNode.setParent(node.getParent());

        BSTNode<T> lParent = (BSTNode<T>) lNode.getParent();

        if (lParent != null) {
            if (lParent.getLeft().equals(node)) {
                lParent.setLeft(lNode);
            } else {
                lParent.setRight(lNode);
            }
        }

        node.setLeft(lNode.getRight());
        lNode.getRight().setParent(node);
        lNode.setRight(node);
        node.setParent(lNode);

        return lNode;
    }

    public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Comparable[size];
        return array;
    }

}
