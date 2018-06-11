import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeAncestors<T> {

  public void printAncestors(T key, Node treeRoot) {
    List<Node> result = getAncestors(key, treeRoot);
    printNodeList(result);
  }

  /**
   * Returns a list of nodes that are parents, grandparents etc, of the node
   * with the value of 'key'.
   *
   * <p>Assumes uniqueness of keys to get the parent, grandparent, ..., root order.
   * If the keys are not unique then returns all node ancestors of all instances.
   *
   * @param key  : The value of the node whos ancestors you want to find
   * @param node : The root of the tree in which you want to search for the ancestors
   * @return : List of nodes which are the ancestors of the node with value 'key'.
   * These are in the order of parent, grandparent, ..., root
   */
  public List<Node> getAncestors(T key, Node node) {
    if (node == null) {
      return Collections.emptyList();
    }
    List<Node> path = new ArrayList<>();
    boolean result = getAncestorsHelper(key, node, path);
    Collections.reverse(path); //Puts the ancestors into the desired order
    return path;
  }

  /**
   * Used to find the path from the root of a tree to a particular node
   * (i.e. the nodes ancestors)
   *
   * <p>Assumes uniqueness of keys.
   * If the keys are not unique then returns all node ancestors of all instances.
   *
   * @param key  : The value of the desired node
   * @param node : Tree to search through
   * @param path : Indicates the visited nodes in the path from the original root directly to
   *             the current 'node'
   * @return true if the node with value 'key' was found in the tree 'node'
   */
  private boolean getAncestorsHelper(T key, Node node, List<Node> path) {
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
      result.append(node.getValue() + " ");
    }
    System.out.println(result.toString());
    return result.toString();
  }

  /**
   * Determines how many of n1 and n2 are in the tree rooted at 'node'.
   *
   * @param n1    : First node to look for
   * @param n2    : Second node to look for
   * @param node  : Root of the tree to search for n1 and n2 in
   * @param found : How many of n1 and n2 have already been found
   * @return 0 if neither n1 nor n2 are in the 'node' tree
   * 1 if either n1 or n2 are in the 'node' tree
   * 2 if both n1 and n2 are in the 'node' tree
   */
  private int nrNodesFound(Node<T> n1, Node<T> n2, Node<T> node, int found) {
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

  /**
   * Find the lowest common ancestor(LCA) node of two nodes in a binary tree.
   * <p>
   * <p>Tree is not necessarily a binary search tree.
   *
   * @param n1
   * @param n2
   * @param tree : root of the tree in which to search for lowest common ancestor
   * @return node which is the lowest common ancestor of n1 and n2
   */
  public Node<T> getLowestCommonAncestor(Node<T> n1, Node<T> n2, Node<T> tree) {
    if (tree == null) {
      return null;
    }
    Node<T> left = getLowestCommonAncestor(n1, n2, tree.getLeft());
    Node<T> right = getLowestCommonAncestor(n1, n2, tree.getRight());

    //Lowest common ancestor is not in the left or right subtree.
    //It could be the current tree node, so we need to check this node
    if (left == null && right == null) {
      int nrFound = nrNodesFound(n1, n2, tree, 0);
      //We've found nodes n1 and n2 in the tree so the root tree node is the LCA
      if (nrFound == 2) {
        return tree;
      } else {
        //Not found n1 and n2 anywhere in this tree
        return null;
      }
    } else if (left == null) {
      //LCA is in the right tree
      return right;
    } else {
      return left;
    }

  }

}
