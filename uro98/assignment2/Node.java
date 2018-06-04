/**
 * Represents a generic node in a generic binary tree.
 */
public class Node<T> {
  
  T data;
  Node<T> left;
  Node<T> right;
  int depth;
  
  public Node(T data, int depth) {
    this.data = data;
    left = null;
    right = null;
    this.depth = depth;
  }
}
