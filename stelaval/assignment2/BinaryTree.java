package assignment2;

import java.util.HashSet;
import java.util.Set;

public class BinaryTree<T> {

  private Node<T> root;
  private Set<Node<T>> nodeSet;

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
   * @throws Exception if given key is not present in a tree
   */
  public void printAncestors(Node<T> key) throws Exception {
    if (nodeSet.contains(key)) {
      containsKey(root, key);
    } else {
      throw new Exception("Key is out of tree");
    }
  }

  /**
   * Checks if a node is present in a subtree
   * 
   * @param currentNode is the root of a subtree
   * 
   * @param key is a node which ancestors will be printed
   */
  private boolean containsKey(Node<T> currentNode, Node<T> key) {
    if (currentNode == null) {
      return false;
    } else if (currentNode.getValue().equals(key.getValue())) {
      return true;
    } else if (containsKey(currentNode.getLeft(), key)
        || containsKey(currentNode.getRight(), key)) {
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
   * @throws Exception if either one or both nodes are not in the binary tree
   */
  public Node<T> getLowestCA(Node<T> node1, Node<T> node2) throws Exception {
    if (nodeSet.contains(node1) && nodeSet.contains(node2)) {
      return getCA(root, node1, node2);
    } else {
      throw new Exception("Nodes are not in the tree");
    }
  }

  private Node<T> getCA(Node<T> currentNode, Node<T> node1, Node<T> node2) {
    if (currentNode == null) {
      return null;
    }
    if (currentNode.equals(node1) || currentNode.equals(node2)) {
      return currentNode;
    }

    Node<T> leftCA = getCA(currentNode.getLeft(), node1, node2);
    Node<T> rightCA = getCA(currentNode.getRight(), node1, node2);

    if (leftCA != null && rightCA != null) {
      return currentNode;
    } else if (leftCA != null) {
      return leftCA;
    } else {
      return rightCA;
    }
  }


  private static class Node<T> {

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

    public void setLeft(Node left) {
      this.left = left;
    }

    public Node<T> getRight() {
      return right;
    }

    public void setRight(Node right) {
      this.right = right;
    }
  }

  public static void main(String[] args) {
    try {
      Node<Integer> root = new Node<>(0);
      Node<Integer> node1 = new Node<>(1);
      Node<Integer> node2 = new Node<>(2);
      Node<Integer> node3 = new Node<>(3);
      Node<Integer> node4 = new Node<>(4);
      Node<Integer> node5 = new Node<>(5);
      Node<Integer> node6 = new Node<>(6);
      Node<Integer> node7 = new Node<>(5);
      BinaryTree<Integer> bTree = new BinaryTree<>();
      root.setLeft(node1);
      root.setRight(node2);
      node1.setLeft(node3);
      node1.setRight(node4);
      node2.setRight(node5);
      node4.setRight(node6);
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

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
