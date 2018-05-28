package assignment1;

public class SinglyLinkedList<T> {

	private Node<T> head;

	public Node<T> getHead() {
		return head;
	}

	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Finds the kth to last element of the singly linked list
	 */
	public T getKthToLastElement(int k) {
		int length = 0;
		Node<T> currentNode = getHead();

		while (currentNode != null) {
			currentNode = currentNode.getNext();
			length++;
		}

		if (length < k) {
			throw new IllegalArgumentException();
		}

		currentNode = getHead();

		for (int i = 1; i < length - k + 1; i++)
			currentNode = currentNode.getNext();

		return currentNode.getData();
	}

	/**
	 * Inserts a new element at front of the linked list.
	 */
	public void push(int data) {

		Node<T> newNode = new Node(data);

		newNode.next = head;

		head = newNode;
	}

	private static class Node<T> {

		private final T data;
		private Node next;

		public Node(T data) {
			this.data = data;
		}

		public T getData() {
			return data;
		}

		public Node getNext() {
			return next;
		}
	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		list.push(5);
		list.push(10);
		list.push(15);
		list.push(20);
		list.push(25);

		System.out.println(list.getKthToLastElement(2));
	}
}
