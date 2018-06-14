import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PrintAncestors {

  /**
   * Print the ancestors of the node with the given key in a binary tree as a
   * list. Uses left-to-right depth first search. An empty list is printed if
   * the node is not found.
   * 
   * @param tree  a generic binary tree
   * @param key   the target value of a node
   */
  public static <T> void printAncestors(BinaryTree<T> tree, T key) {
    ArrayList<T> ancestors = new ArrayList<T>();
    
    Node<T> result = depthFirstSearch(tree.getRoot(), key);
    if (result != null) {
      while (!result.equals(tree.getRoot())) {
        result = result.getParent();
        ancestors.add(result.getData());
      }
    }
    
    printList(ancestors);
  }
  
  /**
   * Recursively searches the binary tree from the given node for the node with the given key.
   * @param root  the node to search from
   * @param key   the key to search for
   * @return      the node with the key or null
   */
  public static <T> Node<T> depthFirstSearch(Node<T> root, T key) {
    
    if (root.getData().equals(key)) {
      return root;
    } else {
      if (root.getLeft() != null) {
        Node<T> result = depthFirstSearch(root.getLeft(), key);
        if (result != null) {
          return result;
        }
      }
      if (root.getRight() != null) {
        Node<T> result = depthFirstSearch(root.getRight(), key);
        if (result != null) {
          return result;
        }
      }
    }
    
    return null;
  }

  /**
   * Print out a generic LinkedList.
   * 
   * @param list a LinkedList
   */
  public static <T> void printList(List<T> list) {
    System.out.println(Arrays.toString(list.toArray()));
  }

  public static void main(String[] args) {
    BinaryTree<Integer> tree = new BinaryTree<Integer>();

    Node<Integer> root = tree.setRoot(7);
    Node<Integer> three = root.addChild(3, Node.Side.LEFT);
    Node<Integer> four = root.addChild(4, Node.Side.RIGHT);
    Node<Integer> two = three.addChild(2, Node.Side.LEFT);
    Node<Integer> five = three.addChild(5, Node.Side.RIGHT);
    Node<Integer> eight = four.addChild(8, Node.Side.RIGHT);
    Node<Integer> one = two.addChild(1, Node.Side.LEFT);
    Node<Integer> six = two.addChild(6, Node.Side.RIGHT);

    printAncestors(tree, 6);
  }
}
