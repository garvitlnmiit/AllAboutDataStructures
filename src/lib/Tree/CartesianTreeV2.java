/**
 * Cartesian Tree version-2.0 :: O(N)
 * @author Garvit Sharma <garvits45@gmail.com>
 * @Date August 31, 2016
 **/

import java.util.Arrays;

class Node
{
	int data;
	Node left;
	Node right;

	Node(int val) {
		this.data = val;
		this.left = null;
		this.right = null;
	}
}

public class CartesianTreeV2
{
	// to build actual tree
	private Node buildCartesianTreeUtil(int root, int[] data, int[] leftChild, int[] rightChild) {

		if (root == -1) return null;

		Node rootNode = new Node(data[root]);

		rootNode.left = buildCartesianTreeUtil(leftChild[root], data, leftChild, rightChild);
		rootNode.right = buildCartesianTreeUtil(rightChild[root], data, leftChild, rightChild);

		return rootNode;
	}

	Node buildCartesianTree(int data[]) {

		int root = 0, last, n = data.length;
		int[] parent, leftChild, rightChild;

		parent = new int[n];
		leftChild = new int[n];
		rightChild = new int[n];

		Arrays.fill(parent, -1);
		Arrays.fill(leftChild, -1);
		Arrays.fill(rightChild, -1);

		for(int i=1; i<n; i++) {

			last = i-1;
			rightChild[i] = -1;

			while(data[last] <= data[i] && last != root)
				last = parent[last];

			// for the pattern 5 - 10 - 15
			if (data[last] <= data[i]) {
				parent[root] = i;
				leftChild[i] = root;
				root = i;

			// for the pattern 20 - 12 - 5
			} else if (rightChild[last] == -1) {
				rightChild[last] = i;
				parent[i] = last;
				leftChild[i] = -1;

			// for pattern 20 - 12 - 15 - 5;
			// reconfigure links
			} else {
				parent[rightChild[last]] = i;
				leftChild[i] = rightChild[last];
				rightChild[last] = i;
				parent[i] = last;
			}
		}

		parent[root] = -1;

		return buildCartesianTreeUtil(root, data, leftChild, rightChild);
	}

	void printInOrder(Node root) {
		if (root == null) return;

		printInOrder(root.left);
		System.out.print(root.data + " ");
		printInOrder(root.right);
	}

	public static void main(String[] args) {

		int[] data = {1, 5, 10, 40, 30, 15, 28, 20};

		CartesianTreeV2 ctv2 = new CartesianTreeV2();

		Node root = ctv2.buildCartesianTree(data);

		ctv2.printInOrder(root);
		System.out.println();
	}
}