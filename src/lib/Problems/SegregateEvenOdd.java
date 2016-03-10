/**
 * @Title: Segregate even-odd data in linkedlist
 * @GFG: http://www.geeksforgeeks.org/segregate-even-and-odd-elements-in-a-linked-list/
 * @Author: Garvit Sharma <garvits45@gmail.com>
 **/

class Node
{
	int data;
	Node next;

	Node(int val) {
		data = val;
		next = null;
	}
}

public class SegregateEvenOdd
{

	void segregate(Node root) {

		Node lastNode = root,
			head = root, temp, lastTemp, prev;
	  boolean rootSet=false, containsEven = false;
	  int listSize = 1;

		if (root == null || root.next == null) {
			return;
		}

		while (lastNode.next != null) {
			lastNode = lastNode.next;
			listSize++;
		}

		prev = head;
		temp = head;
		lastTemp = lastNode;

		for (int iter=0; iter<listSize; iter++) {
			printList(root);

			// Hack for all Odds
			if ((iter == listSize-1) && !containsEven && (head.data%2 != 0)) {
				// Iterate one extra time...
				listSize++;
				containsEven = true;
			}

			if(head.data%2 != 0) {
				temp = head.next;
				lastTemp.next = head;
				head = temp;
				lastTemp.next.next = null;
				lastTemp = lastTemp.next;

				// If no even found yet... keep updating the root
				if (!rootSet) {
					root = head;
				} else {
					// If root is set to even and if odd found later then
					// join previous node to next node.
					prev.next = head;
				}
			} else {
				containsEven = true;
				// Update the root as soon as even number found.
				if (!rootSet) {
					root=head;
					rootSet=true;
				}
				prev = head;
				head = head.next;
			}
		}
	}

	void printList(Node root) {
		if (root == null) return;

		while(root != null) {
			System.out.print(root.data + " ");
			root = root.next;
		}

		System.out.println();
	}

	public static void main(String[] args) {

		// test case
		Node llNode = new Node(17);
		llNode.next = new Node(15);
		llNode.next.next = new Node(8);
		llNode.next.next.next = new Node(12);
		llNode.next.next.next.next = new Node(10);
		llNode.next.next.next.next.next = new Node(5);
		llNode.next.next.next.next.next.next = new Node(4);
		llNode.next.next.next.next.next.next.next = new Node(1);
		llNode.next.next.next.next.next.next.next.next = new Node(7);
		llNode.next.next.next.next.next.next.next.next.next = new Node(6);

		SegregateEvenOdd seo = new SegregateEvenOdd();
		seo.segregate(llNode);
		//seo.printList(llNode);
	}
}