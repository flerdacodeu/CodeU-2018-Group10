/**
 * Represents a singly linked list.
 *
 */
public class SinglyLinkedList<E> {
  
  Node<E> first;
  Node<E> last;
  int length;

  private static class Node<E> {

    E data;
    Node<E> next;

    public Node(E obj) {
      data = obj;
      next = null;
    }
  }

  /**
   * Adds a new Generic element to the singly linked list.
   * @param element
   */
  public void add(E element) {
    Node<E> newNode = new Node<E>(element);
    
    if (length == 0) {
      first = newNode;
      last = newNode;
      length = 1;
    } else {
      last.next = newNode;
      last = newNode;
      length++;
    }
  }
  
  /**
   * Returns the k to last element.
   * @param k integer
   * @return Generic element
   */
  public E kToLastObject(int k) {

    int target = length - k - 1;
    int index = 0;
    Node<E> node = first;

    // Get target node
    while (index != target) {
      node = node.next;
      index++;
    }

    return node.data;
  }
}
