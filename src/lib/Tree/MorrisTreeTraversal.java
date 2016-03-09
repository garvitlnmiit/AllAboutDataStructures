/**
 * @Title: Morris Tree Traversals
 * @Author: Garvit Sharma <garvits45@gmail.com>
 **/

class Node
{
	int data;
	Node left, right;

	Node(int val) {
		data = val;
		left = null;
		right = null;
	}
}

public class MorrisTreeTraversal
{

	private Node findPredecessor(Node current) {

		Node node = current;

		// Base case
		if (node == null || node.left == null) {
			return null;
		}

		node = node.left;

		while (node.right != null && node.right != current) {
			node = node.right;
		}

		return node;
	}

	private void visitNode(Node node) {

		//Base case
		if (node != null) {
			System.out.print(node.data + " ");
		} else {
			System.out.println();
		}

	}

	public void morrisInOrder(Node node) {

		Node predecessor = null, current = node;

		// Main iteration...
		while (current != null) {

			if (current.left == null) {
				visitNode(current);
				current = current.right;

			} else {
				predecessor = findPredecessor(current);

				if (predecessor.right == null) {
					predecessor.right = current;
					current = current.left;

				} else {
					predecessor.right = null;
					visitNode(current);
					current = current.right;
				}
			}
		}

		visitNode(null);
	}

	public void inOrder(Node node) {
		if (node == null) {
			return;
		}

		inOrder(node.left);
		visitNode(node);
		inOrder(node.right);
	}

	public static void main(String[] args) {

		Node bt = new Node(10);
		bt.left = new Node(5);
		bt.right = new Node(30);
		bt.right.right = new Node(40);
		bt.left.left = new Node(-2);
		bt.left.left.right = new Node(2);
		bt.left.left.right.left = new Node(-1);
		bt.left.right = new Node(6);
		bt.left.right.right = new Node(8);

		MorrisTreeTraversal mtt = new MorrisTreeTraversal();
		mtt.morrisInOrder(bt);
		//mtt.inOrder(bt);
	}
}