/**
 * A Binary Tree consists of "nodes" each node is itself a Binary Tree.
 * Each node has a parent unless it is a root and may have a left child and may have a right child.
 *
 * @Author Garvit Sharma
 * @Version Feb 20, 2016
 * @Params <V> The type of values contained in this Binary Tree
 **/

public class BinaryTree<V>
{
	/**
	 * The value (data) in this node of the BinaryTree; may be of
	 * any object type.
	 **/
	public V value;
	private BinaryTree<V> leftChild;
	private BinaryTree<V> rightChild;

	/**
	 * Contructor for BinaryTree.
	 *
	 * @param value The value to be placed in the node.
	 * @param leftChild The left child of the node, may be null.
	 * @param rightChild The right child of the node, may be null.
	 **/
	public BinaryTree(V value, BinaryTree<V> leftChild, BinaryTree<V> rightChild) {
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	/**
	 * Contructor for a Binary Tree leaf node i.e., with no children.
	 * @param value The value to be placed in the node;
	 **/
	public BinaryTree(V value) {
		this(value, null, null);
	}


	public V getValue() {
		return value;
	}

	public BinaryTree<V> getLeftChild() {
		return leftChild;
	}

	public BinaryTree<V> getRightChild() {
		return rightChild;
	}

	public void setLeftChild(BinaryTree<V> subtree) {
		leftChild = subtree;
	}

	public void setRightChild(BinaryTree<V> subtree) {
		rightChild = subtree;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof BinaryTree)) {
			return false;
		}

		BinaryTree<?> otherTree = (BinaryTree<?>) o;

		return equals(value, otherTree.value)
						&& equals(leftChild, otherTree.getLeftChild())
						&& equals(rightChild, otherTree.getRightChild());
	}

	private boolean equals(Object x, Object y) {
		if (x == null) return y == null;
		return x.equals(y);
	}

	@Override
	public String toString() {
		if (isLeaf()) {
			return value.toString();
		} else {
			String root, left="null", right="null";
			root = value.toString();
			if (getLeftChild() != null) {
				left = getLeftChild().toString();
			}
			if (getRightChild() != null) {
				right = getRightChild().toString();
			}
			return root + " (" + left + " , " + right + ") ";
		}
	}
}