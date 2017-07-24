/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

public class AVL<T extends Comparable<T>> extends BST<T> implements	AVLTree<T> {

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if(node == null || node.isEmpty())
			return -1;
		
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		
		return this.height(right) - this.height(left);
	}
	
	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = this.calculateBalance(node);
		
		if(balance < -1) {
			if(this.calculateBalance((BSTNode<T>) node.getLeft()) > 0)
				leftRotation((BSTNode<T>) node.getLeft());
			rightRotation(node);
			} else if (balance > 1) {
				if(this.calculateBalance((BSTNode<T>) node.getRight()) < 0)
					rightRotation((BSTNode<T>) node.getRight());
				leftRotation(node);
		}
	}

	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> novoNode = Util.rightRotation(node);
		if(novoNode.getParent() == null)
			root = novoNode;
	}

	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> novoNode = Util.leftRotation(node);
		if(novoNode.getParent() == null)
			root = novoNode;
    }

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if(node != null) {
			this.rebalance(node);
			this.rebalanceUp((BSTNode<T>) node.getParent());
		}
	}
	
	@Override
	public void remove(T element) {
		if(element == null)
			return;
		
        BSTNode<T> node = search(element);
        // super.remove(element) == BST.remove(element)
		super.remove(node);
		this.rebalanceUp((BSTNode<T>) node.getParent());
	}
	
	@Override
	public void insert(T element) {
        // super.insert(element) == BST.insert(element)
		super.insert(element);
		BSTNode<T> node = search(element);
		this.rebalanceUp(node);
    }
    
     /**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		if(node.isEmpty())
			return null;
		
		BSTNode pivot = (BSTNode) node.getRight();
		pivot.parent = node.parent;
		node.parent = pivot;
		node.right = pivot.getLeft();
		pivot.left = node;
		
		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		if(node.isEmpty())
			return null;
		
		BSTNode pivot = (BSTNode) node.getLeft();
		pivot.parent = node.parent;
		node.parent = pivot;
		node.left = pivot.getRight();
		pivot.right = node;
		
		return pivot;
	}
}
