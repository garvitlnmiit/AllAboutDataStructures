/**
 * AVL Tree Implementation.
 * @author Garvit Sharma <garvits45@gmail.com>
 **/

class Node
{
	int key, height;
	Node left, right;

	Node(int d) {
		key = d;
		height = 1;
	}
}

public class AVLTree
{

	// A utility to get the height of the node
	int getHeight(Node N) {
		if (N==null) {
			return 0;
		}
		return N.height;
	}

	// A utility function to right rotate subtree rooted with N
	Node rightRotate(Node N) {
		Node root = N.left;

		// Perform rotation
		N.left = root.right;
		root.right = N;

		// Update heights
		N.height = Math.max(getHeight(N.left), getHeight(N.right)) + 1;
		root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;

		return root;
	}

	// A utility function to right rotate subtree rooted with N
	Node leftRotate(Node N) {
		Node root = N.right;

		// Perform rotation
		N.right = root.left;
		root.left = N;

		// Update heights
		N.height = Math.max(getHeight(N.left), getHeight(N.right)) + 1;
		root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;

		return root;
	}

	// Get balance factor of node N
	int getBalance(Node N) {
		if (N==null) return 0;

		return getHeight(N.left) - getHeight(N.right);
	}

	// Insertion in Tree
	Node insert(Node N, int key) {
		if (N==null) {
			return (new Node(key));
		}

		if (key < N.key) {
			N.left = insert(N.left, key);
		} else {
			N.right = insert(N.right, key);
		}

		// Update height of this ancestor node
		N.height = Math.max(getHeight(N.left), getHeight(N.right)) + 1;

		// Get the balance factor of this ancestor node to check whether
		// this node became unbalanced
		int balance = getBalance(N);

		// If this node becomes unbalanced then there are 4 cases

		// 1. Left left case
		if (balance > 1 && key < N.left.key) {
			return rightRotate(N);
		}

		// 2. Right right case
		if (balance < -1 && key > N.right.key) {
			return leftRotate(N);
		}

		// 3. Left right case
		if (balance > 1 && key > N.left.key) {
			N.left = leftRotate(N.left);
			return rightRotate(N);
		}

		// 4. Right left case
		if (balance < -1 && key < N.right.key) {
			N.right = rightRotate(N.right);
			return leftRotate(N);
		}

		// If everything remains unchanged
		return N;
	}

	// PreOrder tree traversal
	void preOrder(Node N) {
		if (N!=null) {
			System.out.print(N.key + " ");
			preOrder(N.left);
			preOrder(N.right);
		}
	}

	// InOrder tree traversal
	void inOrder(Node N) {
		if (N!=null) {
			inOrder(N.left);
			System.out.print(N.key + " ");
			inOrder(N.right);
		}
	}

	// PostOrder tree traversal
	void postOrder(Node N) {
		if (N!=null) {
			postOrder(N.left);
			postOrder(N.right);
			System.out.print(N.key + " ");
		}
	}

	public static void main(String[] args) {
		// Initializations
		Node root = null;
		AVLTree avl = new AVLTree();

		// Tree Construction
		root = avl.insert(root, 10);
		root = avl.insert(root, 20);
		root = avl.insert(root, 30);
		root = avl.insert(root, 40);
		root = avl.insert(root, 50);
		root = avl.insert(root, 25);

		System.out.println("PreOrder tree traversal ::");
		avl.preOrder(root);
		System.out.println();
		System.out.println("InOrder tree traversal ::");
		avl.inOrder(root);
		System.out.println();
		System.out.println("PostOrder tree traversal ::");
		avl.postOrder(root);
		System.out.println();
	}
}