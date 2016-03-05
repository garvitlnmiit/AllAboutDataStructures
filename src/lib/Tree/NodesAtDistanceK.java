/**
 * @Title: Print all nodes at distance K from a given node in a Binary Tree.
 **/

class Node
{
	int data;
	Node left, right;

	Node(int d) {
		data = d;
		left = null;
		right = null;
	}
}

public class NodesAtDistanceK
{
	Node root;

	private void traverseDown(Node node, int dist) {

		// Base conditions
		if (dist < 0 || node == null) {
			return;
		}

		if (dist == 0) {
			System.out.print(node.data + " ");
			return;
		}

		traverseDown(node.left, dist-1);
		traverseDown(node.right, dist-1);
	}

	public int printNodesAtK(Node root, int target, int dist) {

		// Base cases
		if (root == null) {
			return -1;
		}

		// Target node found
		if (root.data == target) {
			traverseDown(root, dist);
			return 0;
		}

		// Otherwise
		// For left subtree
		int subLeft = printNodesAtK(root.left, target, dist);

		if (subLeft != -1) {
			if (dist == subLeft+1) {
				System.out.print(root.data + " ");
			} else {
				traverseDown(root.right, dist - subLeft - 2);
			}
			// Return now
			return (subLeft + 1);
		}

		// For right subtree
		int subRight = printNodesAtK(root.right, target, dist);

		if (subRight != -1) {
			if (dist == subRight+1) {
				System.out.print(root.data + " ");
			} else {
				traverseDown(root.left, dist - subLeft - 2);
			}
			return (subRight + 1);
		}

		return -1;
	}

	public static void main(String[] args) {

		NodesAtDistanceK nadk = new NodesAtDistanceK();

		// Creating Binary Tree
		nadk.root = new Node(20);
		nadk.root.left = new Node(22);
		nadk.root.right = new Node(25);
		nadk.root.left.left = new Node(24);
		nadk.root.left.right = new Node(12);
		nadk.root.left.right.left = new Node(10);
		nadk.root.left.right.right = new Node(14);

		int distance = 2;
		int target = 22;
		int k = nadk.printNodesAtK(nadk.root, target, distance);

		if (k == -1) {
			System.out.println("Node not found");
		} else {
			System.out.println();
		}
	}
}