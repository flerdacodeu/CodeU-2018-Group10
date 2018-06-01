import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeAncestors<T> {

  public void printAncestors(T key, Node treeRoot) {
    List<Node> result = getAncestors(key, treeRoot);
    printNodeList(result);
  }

  public List<Node> getAncestors(T key, Node node) {
    if (node == null) {
      return Collections.emptyList();
    }
    List<Node> path = new ArrayList<>();
    boolean result = getAncestorsHelper(key, node, path);
    Collections.reverse(path);
    return path;
  }

  public boolean getAncestorsHelper(T key, Node node, List<Node> path) {
    if (node == null) {
      return false;
    }
    if (node.getValue() == key) {
      return true;
    }
    boolean left = false;
    boolean right = false;
    if (node.getLeft() != null) {
      path.add(node);
      left = getAncestorsHelper(key, node.getLeft(), path);
    }
    if (node.getRight() != null) {
      path.add(node);
      right = getAncestorsHelper(key, node.getRight(), path);
    }
    if (path.size() != 0 && !left && !right) {
      node = path.get(path.size() - 1); //Take most recently added node
      path.remove(path.size() - 1);
    }
    return left || right;
  }

  public String printNodeList(List<Node> list) {
    StringBuilder result = new StringBuilder();
    for (Node node : list) {
      result.append(node.getValue());
    }
    System.out.println(result.toString());
    return result.toString();
  }

  /**
   * Find the value of the lowest common ancestor node
   * of two nodes in a binary tree.
   *
   * <p> Tree is not necessarily a binary search tree.
   *
   * <p> Assumes both nodes are in the tree.
   *
   * @param n1 is the value of the first node
   * @param n2 is the value of the second node
   * @param tree in which to search for lowest common ancestor
   * @return lowest value of the common ancestor of n1 and n2
   */

  /**
   * Find the lowest common ancestor node of two nodes in a binary tree.
   *
   * <p> Tree is not necessarily a binary search tree.
   *
   * @param n1
   * @param n2
   * @param node
   * @param found
   * @return
   */
  public int nrNodesFound(Node<T> n1, Node<T> n2, Node<T> node, int found) {
   if (node.getValue().equals(n1.getValue()) || node.getValue().equals(n2.getValue())) {
     found++;
   }
   if (node.getLeft() != null) {
     found = nrNodesFound(n1, n2, node.getLeft(), found);
   }
    if (node.getRight() != null) {
      found = nrNodesFound(n1, n2, node.getRight(), found);
    }
    return found;
  }

  public Node<T> getLowestCommonAncestor(Node<T> n1, Node<T> n2, Node<T> tree) {
    if (tree == null) {
      return null;
    }
    Node<T> left = getLowestCommonAncestor(n1, n2, tree.getLeft());
    Node<T> right = getLowestCommonAncestor(n1, n2, tree.getRight());
    if (left == null && right == null) {
      int nrFound = nrNodesFound(n1, n2, tree, 0);
      if (nrFound == 2) {
        return tree;
      } else {
        return null;
      }
    } else if (left == null) {
      return right;
    } else {
      return left;
    }

  }


}
