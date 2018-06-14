/**
 * Represents a generic node in a generic binary tree.
 */
public class Node<T> {
  
  enum Side {
    LEFT, RIGHT
  }
  
  private T data;
  private Node<T> left;
  private Node<T> right;
  private Node<T> parent;
  
  public Node(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }
  
  public Node<T> getLeft() {
    return left;
  }
  
  public Node<T> getRight() {
    return right;
  }
  
  public Node<T> getParent() {
    return parent;
  }

  /**
   * Adds node with given value as child of the current node on the specified side,
   * sets the current node as the parent and returns the newly created child.
   * @param data generic value of the child
   * @param side left or right side of the current node
   * @return the child node
   */
  public Node<T> addChild(T data, Side side) {
    Node<T> child = new Node<T>(data);
    
    if (side == Side.LEFT) {
      this.left = child;
    } else {
      this.right = child;
    }
    
    child.parent = this;
    
    return child;
  }
}
