public class SinglyLinkedList<T> {
  private int size = 0;
  private Node head;

  public int getSize() {
    return size;
  }

  /**
   * Get element at specified index from the singly linked list.
   * Indexing from 0 (i.e. head is element zero).
   *
   * @param elemIndex : index of desired element
   * @return value of the the kth element in the list
   */
  public T getElemAtIndex(int elemIndex) {
    if (elemIndex > size) {
        return null;
    }
    Node currentNode = head;
    for (int i = 0; i < elemIndex && currentNode.getNext() != null; i++) {
      currentNode = currentNode.getNext();
    }
    return currentNode.getValue();
  }

  /**
   * Finds the kth to last element of the list
   *
   * @param nrFromEnd: desired index from end of list.
   *                   nrFromEnd of 0 corresponds to the last element in the list,
   *                   nrFromEnd of 1 corresponds to the second to last element etc.
   * @return value of the nrFromEnd element
   */
  public T getKthElemFromTail(int nrFromEnd) {
    return getElemAtIndex(size - nrFromEnd - 1);
  }

  /**
   * Insert new value into the list.
   * Adds new element to the end of the list.
   *
   * @param value: to insert
   */
  public void insert(T value) {
    final Node newNode = new Node(value);
    if (head != null) {
      Node currentNode = head;
      while (currentNode.getNext() != null) {
        currentNode = currentNode.getNext();
      }
      currentNode.setNext(newNode);
    } else {
      head = newNode;
    }
    size++;
  }


  /**
   * Singly linked list consists of a chain of connected Nodes.
   */
  private class Node {
    private T value;
    private Node next;

    private Node(T value) {
      this.value = value;
    }

    public T getValue() {
      return value;
    }


    public Node getNext() {
      return next;
    }

    private void setNext(Node next) {
      this.next = next;
    }
  }
}
