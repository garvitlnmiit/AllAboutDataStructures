class BTNode
{
	int data;
	BTNode left;
	BTNode right;

	BTNode(int value) {
		data = value;
		left = null;
		right = null;
	}
}

public class PrintZigZagTree
{
	private void _printZigZag(BTNode node, boolean flag) {
		if (node == null)
			return;

		BTNode lnode = node.left;
		BTNode rnode = node.right;

		if (flag) {
			if (lnode != null) System.out.print(lnode.data + " ");
			if (rnode != null) System.out.print(rnode.data + " ");

			_printZigZag(rnode, !flag);
			_printZigZag(lnode, !flag);
		} else {
			if (rnode != null) System.out.print(rnode.data + " ");
			if (lnode != null) System.out.print(lnode.data + " ");

			_printZigZag(lnode, !flag);
			_printZigZag(rnode, !flag);
		}
	}

	void printZigZag(BTNode root) {
		if (root == null) return;

		System.out.print(root.data + " ");
		_printZigZag(root, true);
		System.out.println();
	}

	public static void main(String[] args) {
		PrintZigZagTree zz = new PrintZigZagTree();

		// Creating input
		BTNode root = new BTNode(1);
		root.left = new BTNode(2);
		root.right = new BTNode(3);
		root.left.left = new BTNode(7);
		root.left.right = new BTNode(6);
		root.right.left = new BTNode(5);
		root.right.right = new BTNode(4);
		root.left.right.left = new BTNode(8);
		root.right.left.right = new BTNode(9);

		zz.printZigZag(root);
	}
}