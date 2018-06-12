package assignment2;

import java.util.HashSet;
import java.util.Set;

public class BinaryTree<T> {

  private Node<T> root;
  private final Set<Node<T>> nodeSet;

  public BinaryTree() {
    nodeSet = new HashSet<>();
  }

  /**
   * The nodes of the binary tree are stored in a set in order to avoid duplicate values.
   * 
   * @param newNode is the node to be added to the set of nodes
   */
  public void add(Node<T> newNode) {
    if (root == null) {
      root = newNode;
    }

    if (nodeSet.contains(newNode)) {
      throw new IllegalArgumentException();
    } else {
      nodeSet.add(newNode);
    }
  }

  /**
   * Prints all the ancestors of a node
   * 
   * @param key is a node which ancestors are printed
   * 
   * @throws IllegalArgumentException if given key is not present in a tree
   */
  public void printAncestors(Node<T> key) {
    if (nodeSet.contains(key)) {
      printAncestorsIfNodeInSubtree(root, key);
    } else {
      throw new IllegalArgumentException("Key is out of tree");
    }
  }

  /**
   * Checks if a node is present in a subtree and prints its ancestors
   * 
   * @param currentNode is the root of a subtree
   * @param key is a node which ancestors will be printed
   * 
   * @return false if a node is not present in a subtree, otherwise return true
   */
  private boolean printAncestorsIfNodeInSubtree(Node<T> currentNode, Node<T> key) {
    if (currentNode == null) {
      return false;
    } else if (currentNode.getValue().equals(key.getValue())) {
      return true;
    } else if (printAncestorsIfNodeInSubtree(currentNode.getLeft(), key)
        || printAncestorsIfNodeInSubtree(currentNode.getRight(), key)) {
      System.out.print(currentNode.getValue() + " ");
      return true;
    }
    return false;
  }

  /**
   * Finds the lowest common ancestor of two nodes in a binary tree
   * 
   * @param node1 is the first node
   * @param node2 is the second node
   * 
   * @return node that is the lowest common ancestor of node1 and node2
   * 
   * @throws IllegalArgumentException if either one or both nodes are not in the binary tree
   */
  public Node<T> getLowestCA(Node<T> node1, Node<T> node2) {
    if (nodeSet.contains(node1) && nodeSet.contains(node2)) {
      return getLowestCA(root, node1, node2);
    } else {
      throw new IllegalArgumentException("Nodes are not in the tree");
    }
  }

  private Node<T> getLowestCA(Node<T> currentNode, Node<T> node1, Node<T> node2) {
    if (currentNode == null) {
      return null;
    }
    if (currentNode.equals(node1) || currentNode.equals(node2)) {
      return currentNode;
    }

    Node<T> leftCA = getLowestCA(currentNode.getLeft(), node1, node2);
    Node<T> rightCA = getLowestCA(currentNode.getRight(), node1, node2);

    if (leftCA != null && rightCA != null) {
      return currentNode;
    } else if (leftCA != null) {
      return leftCA;
    } else {
      return rightCA;
    }
  }


  static class Node<T> {

    private T value;
    private Node<T> left;
    private Node<T> right;

    public Node(T value) {
      this.value = value;
      left = null;
      right = null;
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
     * Adds node with given value as left child and returns newly created child Node object.
     */
    public Node<T> addLeft(T value) {
      Node<T> child = new Node<>(value);
      if (this.left == null) {
        this.left = child;
      } else {
        throw new IllegalArgumentException("Left child already exists");
      }
      return child;
    }

    /**
     * Adds node with given value as right child and returns newly created child Node object.
     */
    public Node<T> addRight(T value) {
      Node<T> child = new Node<>(value);
      if (this.right == null) {
        this.right = child;
      } else {
        throw new IllegalArgumentException("Right child already exists");
      }
      return child;
    }
  }

  public static void main(String[] args) {
    try {
      Node<Integer> root = new Node<>(0);
      Node<Integer> node1 = root.addLeft(1);
      Node<Integer> node2 = root.addRight(2);
      Node<Integer> node3 = node1.addLeft(3);
      Node<Integer> node4 = node1.addRight(4);
      Node<Integer> node5 = node2.addRight(5);
      Node<Integer> node6 = node4.addRight(6);
      Node<Integer> node7 = new Node<>(5);
      BinaryTree<Integer> bTree = new BinaryTree<>();
      bTree.add(root);
      bTree.add(node1);
      bTree.add(node2);
      bTree.add(node3);
      bTree.add(node4);
      bTree.add(node5);
      bTree.add(node6);

      System.out.print("The ancestors of 6 are: ");
      bTree.printAncestors(node6);
      System.out.println();
      System.out.println("The lowest common ancestor of 3 and 4 is: "
          + bTree.getLowestCA(node3, node4).getValue());
      bTree.printAncestors(node7);

    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

  }
}
