class Node
{
	int data;
	Node next;

	Node(int value) {
		data = value;
		next = null;
	}
}

public class AlterSwap
{
	private Node update(Node cur) {
		if (cur == null) {
			return null;
		}

		if (cur.next == null) {
			return cur;
		}

		Node temp = cur;
		Node next = cur.next.next;

		cur = cur.next;
		cur.next = temp;

		cur.next.next = update(next);

		return cur;
	}

	Node updateList(Node head) {
		return update(head);
	}

	void printList(Node head) {
		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		AlterSwap as = new AlterSwap();

		// Creating input
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		as.printList(head);
		head = as.updateList(head);
		as.printList(head);
	}
}
