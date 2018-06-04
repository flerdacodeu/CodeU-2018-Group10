/**
 * Represents a generic binary tree.
 *
 */
public class BinaryTree<T> {
  
  Node<T> root;
  
  /**
   * Set the root node to the value specified.
   * @param data a generic value
   */
  public void addRoot(T data) {
    Node<T> newNode = new Node<T>(data, 0);
    
    if (root == null) {
      root = newNode;
    }
  }
}
