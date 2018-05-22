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
			try {
				throw new Exception("Index k is more than the length of the linked list");
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	private class Node<T> {

		private T data;
		private Node next;

		private Node() {
			this.data = data;
		}

		private Node(T data) {
			this.data = data;
		}

		public T getData() {
			return data;
		}

		private void setData(T data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		private void setNext() {
			this.next = next;
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
