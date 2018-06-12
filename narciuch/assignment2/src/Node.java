public class Node<T> {
  private final T value;
  private Node<T> left;
  private Node<T> right;

  public Node(T value) {
    this.value = value;
  }

  public void setLeft(Node<T> left) {
    this.left = left;
  }

  public void setRight(Node<T> right) {
    this.right = right;
  }

  public T getValue() {
    return value;
  }

  public Node<T> getLeft() {
    return left;
  }

  public Node<T> getRight() {
    return right;
  }

  /**
   * Adds node with given value as left child of current node and
   * returns newly created child
   *
   * @param value: value of the new left child
   * @return: reference to the newly created left child
   */
  public Node<T> addLeftChild(T value) {
    Node<T> newLeft = new Node<>(value);
    this.setLeft(newLeft);
    return newLeft;
  }

  /**
   * Adds node with given value as right child of current node and
   * returns newly created child
   *
   * @param value: value of the new right child
   * @return: reference to the newly created right child
   */
  public Node<T> addRightChild(T value) {
    Node<T> newRight = new Node<>(value);
    this.setRight(newRight);
    return newRight;
  }
}