/**
 * Cartesian Tree version-1.0 :: O(N^2)
 * @author Garvit Sharma <garvits45@gmail.com>
 * @Date August 31, 2016
 **/


// Tree node
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

public class CartesianTreeV1
{
	int getMaxValIdx(int[] inorderSequence, int start, int end) {

		int maxVal = inorderSequence[start], maxValIdx = start;

		for (int iter=start+1; iter<end; iter++) {
			if (maxVal < inorderSequence[iter]) {
				maxVal = inorderSequence[iter];
				maxValIdx = iter;
			}
		}

		return maxValIdx;
	}

	Node generateCTree(int[] inorderSeq, int start, int end) {

		if (start > end) return null;

		int getIdx = getMaxValIdx(inorderSeq, start, end);

		Node root = new Node(inorderSeq[getIdx]);

		root.left = generateCTree(inorderSeq, start, getIdx-1);
		root.right = generateCTree(inorderSeq, getIdx+1, end);

		return root;
	}

	void printInorder(Node root) {
		if (root == null) return;

		printInorder(root.left);
		System.out.print(root.data + " ");
		printInorder(root.right);
	}

	public static void main(String[] args) {

		CartesianTreeV1 ct = new CartesianTreeV1();

		int inorderSequence[] = {5, 10, 40, 30, 28, 10};

		Node root = ct.generateCTree(inorderSequence, 0, 5);

		ct.printInorder(root);

		System.out.println();
	}
}