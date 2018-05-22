public class KToLast {

	public static Object kToLastObject(SinglyLinkedList list, int k) {

		SinglyLinkedList.Node node = list.first;
		int length = 0;

		// Find list length
		while (node != null) {
			length++;
			node = node.next;
		}

		int target = length - k - 1;
		int index = 0;
		node = list.first;

		// Get target node
		while (index != target) {
			node = node.next;
			index++;
		}

		return node.data;
	}

	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList(1);

		SinglyLinkedList.Node second = list.new Node(2);
		SinglyLinkedList.Node third = list.new Node(3);

		list.first.next = second;
		second.next = third;

		System.out.println(kToLastObject(list, 1));
	}
}

class SinglyLinkedList {

	Node first;

	public SinglyLinkedList(Object obj) {
		first = new Node(obj);
	}

	class Node {

		Object data;
		Node next;

		public Node(Object obj) {
			data = obj;
			next = null;
		}
	}
}