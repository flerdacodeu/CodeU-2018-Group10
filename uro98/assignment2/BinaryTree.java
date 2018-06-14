/**
 * Represents a generic binary tree.
 *
 */
public class BinaryTree<T> {
  
  private Node<T> root;

  /**
   * Sets the root node of the tree to the given value and returns the root.
   * @param data
   * @return the root node
   * @throws IllegalStateException
   */
  public Node<T> setRoot(T data) throws IllegalStateException {
    if (root != null) {
      throw new IllegalStateException("Root is already set.");
    } else {
      root = new Node<T>(data);
    }
    return root;
  }
  
  public Node<T> getRoot() {
    return root;
  }
}
