/**
 * Red Black Tree Implementation
 * @Operations Insert(O(logN)), Delete(O(logN)), Search(O(logN))
 * @Author Garvit Sharma <garvits45@gmail.com>
 * @Date March 2, 2016
 **/

public class RedBlackTree
{
	public enum Color {
		RED,
		BLACK
	}

	/**
	 * Node schema
	 **/
	public static class Node
	{
		int data;
		Color color;
		Node left;
		Node right;
		Node parent;
		boolean isNullLeaf;
	}

	/**
	 * Main insert method of RedBlack Tree.
	 **/
	public Node insert(Node root, int data) {
		return insert(null, root, data);
	}

	public void printRedBlackTree(Node root) {
		printRedBlackTree(root, 0);
	}
	/**
	 * Creates and returns Black Node
	 **/
	private static Node createBlackNode(int data) {
		Node node = new Node();
		node.data = data;
		node.color = Color.BLACK;
		node.left = createNullLeafNode(node);
		node.right = createNullLeafNode(node);
		node.parent = null;
		return node;
	}

	/**
	 * Creates, associates and returns a Leaf Node
	 **/
	private static Node createNullLeafNode(Node parent) {
		Node leaf = new Node();
		leaf.color = Color.BLACK;
		leaf.isNullLeaf = true;
		leaf.parent = parent;
		return leaf;
	}

	/**
	 * Creates, associates and returns a Red Node
	 **/
	private static Node createRedNode(Node parent, int data) {
		Node node = new Node();
		node.data = data;
		node.color = Color.RED;
		node.parent = parent;
		node.left = createNullLeafNode(node);
		node.right = createNullLeafNode(node);
		return node;
	}

	private void rightRotate(Node node, boolean changeColor) {
		Node parent = node.parent;
		Node right = node.right;
		node.parent = parent.parent;

		if (parent.parent != null) {
			if (parent.parent.right == parent) {
				parent.parent.right = node;
			} else {
				node.parent.left = node;
			}
		}

		parent.parent = node;
		node.right = parent;
		parent.left = right;

		if (right != null) {
			right.parent = parent;
		}

		if (changeColor) {
			node.color = Color.BLACK;
			parent.color = Color.RED;
		}
	}

	private void leftRotate(Node node, boolean changeColor) {
		Node parent = node.parent;
		Node left = node.left;
		node.parent = parent.parent;

		if (parent.parent != null) {
			if (parent.parent.left == parent) {
				parent.parent.left = node;
			} else {
				parent.parent.right = node;
			}
		}

		parent.parent = node;
		node.left = parent;
		parent.right = left;

		if (left != null) {
			left.parent = parent;
		}

		if (changeColor) {
			node.color = Color.BLACK;
			parent.color = Color.RED;
		}
	}

	private boolean isLeftChildNode(Node node) {
		Node parent = node.parent;
		if (parent.left == node) {
			return true;
		} else {
			return false;
		}
	}

	private Node findSiblingNode(Node node) {
		Node parent = node.parent;
		if (isLeftChildNode(node)) {
			return parent.right.isNullLeaf ? null : parent.right;
		} else {
			return parent.left.isNullLeaf ? null : parent.left;
		}
	}

	private Node insert(Node parent, Node node, int data) {
		if (node == null || node.isNullLeaf) {
			/**
			 * If parent is not null means tree is not empty
			 **/
			if (parent != null) {
				return createRedNode(parent, data);
			} else { // Create a black node in case tree is empty
				return createBlackNode(data);
			}
		}

		/**
		 * Value already exists exception handled
		 **/
		if(node.data == data) {
			throw new IllegalArgumentException("Duplicate data " + data);
		}

		boolean isLeft;
		if (node.data > data) {
			Node left = insert(node, node.left, data);
			// If left becomes root parent means rotation
			// happened at lower level. So just return left
			// so that upper level nodes can set their child
			// correctly.
			if (left == node.parent) {
				return left;
			}

			//Set the left child returned to be left of nodes' left
			node.left = left;
			isLeft = true;
		} else {
			Node right = insert(node, node.right, data);
			// If right becomes nodes' parent means rotation
			// happened at lower level. So just return right
			// so that upper level nodes can set their child
			// correctly.
			if (right == node.parent) {
				return right;
			}

			// Set the right child returned to be right of nodes' right
			node.right = right;
			isLeft = false;
		}

		if (isLeft) {
			// Check Red-red conflict between node and its left child.
			if (node.color == Color.RED && node.left.color == Color.RED) {
				// Get the sibling of node.
				Node sibling = findSiblingNode(node);
				// If sibling is empty or of Black color
				if (sibling == null || sibling.color == Color.BLACK) {
					// Check if node is left child of its parent
					if (isLeftChildNode(node)) {
						// We have got left-left situation
						// So one right rotate will work
						rightRotate(node, true);
					} else {
						// We have got right-left situation
						// So right rotate followed by left rotate.

						// Do right rotate without changing color.

						// When right rotation is done, node becomes the right child of its left child.
						rightRotate(node.left, false);
						// After roatation node should be nodes' parent
						node = node.parent;
						// Now left rotate with change of color.
						leftRotate(node, true);
					}

				} else {
					// We have sibling colored as Red. So change color of node and its sibling to Black.
					// Then change the color of their parent to Red if its not a root.
					node.color = Color.BLACK;
					sibling.color = Color.BLACK;
					// if parents' parent is not null means node parent is not a root.
					// So change its color to Red.
					if (node.parent.parent != null) {
						node.parent.color = Color.RED;
					}
				}
			}

		} else {
			// This is mirror case of above.
			if (node.color == Color.RED && node.right.color == Color.RED) {
				Node sibling = findSiblingNode(node);
				if (sibling == null || sibling.color == Color.BLACK) {
					if (!isLeftChildNode(node)) {
						leftRotate(node, true);
					} else {
						leftRotate(node.right, false);
						node = node.parent;
						rightRotate(node, true);
					}
				} else {
					node.color = Color.BLACK;
					sibling.color = Color.BLACK;

					if (node.parent.parent != null) {
						node.parent.color = Color.RED;
					}
				}
			}
		}

		return node;
	}

	private void printRedBlackTree(Node node, int space) {
		if (node == null || node.isNullLeaf) {
			return;
		}

		printRedBlackTree(node.right, space + 5);
		for(int iter=0; iter<space; iter++) {
			System.out.print(" ");
		}
		System.out.println(node.data + " " + (node.color == Color.BLACK ? "B" : "R"));
		printRedBlackTree(node.left, space + 5);
	}

	public void printInOrder(Node node) {
		if (node == null || node.isNullLeaf) {
			System.out.print("0 ");
			return;
		}

		System.out.print("(");
		printInOrder(node.left);
		System.out.print(node.data + "-" + node.color + " ");
		printInOrder(node.right);
		System.out.print(")");
	}

	public static void main(String[] args) {
		Node root = null;
		RedBlackTree redBlackTree = new RedBlackTree();

		root = redBlackTree.insert(root, 10);
    root = redBlackTree.insert(root, 15);
    root = redBlackTree.insert(root, -10);
    root = redBlackTree.insert(root, 20);
    root = redBlackTree.insert(root, 30);
    root = redBlackTree.insert(root, 40);
    root = redBlackTree.insert(root, 50);
    root = redBlackTree.insert(root, -15);
    root = redBlackTree.insert(root, 25);
    root = redBlackTree.insert(root, 17);
    root = redBlackTree.insert(root, 21);
    root = redBlackTree.insert(root, 24);
    root = redBlackTree.insert(root, 28);
    root = redBlackTree.insert(root, 34);
    root = redBlackTree.insert(root, 32);
    root = redBlackTree.insert(root, 26);
    root = redBlackTree.insert(root, 35);
    root = redBlackTree.insert(root, 19);
    //redBlackTree.printRedBlackTree(root);
    redBlackTree.printInOrder(root);
    System.out.println();
	}
}