import java.util.Arrays;
import java.util.LinkedList;

public class PrintAncestors {
  
  /**
   * Print the ancestors of the node with the given key in a binary tree as a list.
   * Uses left-to-right depth first search.
   * An empty list is printed if the node is not found.
   * @param tree  a generic binary tree
   * @param key   the target value of a node
   */
  public static <T> void printAncestors(BinaryTree<T> tree, T key) {
    LinkedList<T> ancestors = new LinkedList<T>();
    LinkedList<Node<T>> frontier = new LinkedList<Node<T>>();
    
    frontier.addFirst(tree.root);
    
    while (!frontier.isEmpty()) {
      Node<T> node = frontier.pop();
      
      if (node.data.equals(key)) {
        printList(ancestors);
        return;
      } else {
        ancestors.addFirst(node.data);
        
        // Push the children of the node to the frontier stack, push the right child first to make DFS search left to right
        if (node.right != null) {
          frontier.addFirst(node.right);
        }
        if (node.left != null) {
          frontier.addFirst(node.left);
        }
        
        /* If the next node to be explored in the frontier is further up the tree, remove the nodes that aren't ancestors
         * (ie the ones on the same level and below)
         */
        if (!frontier.isEmpty() && node.depth >= frontier.peek().depth) {
          int depthDifference = node.depth - frontier.peek().depth;
          for (int i = 0; i < depthDifference + 1; i++) {
            ancestors.pop();
          }
        }
      }
    }
    
    // If the node is not found, print an empty list
    ancestors.clear();
    printList(ancestors);
  }
  
  /**
   * Print out a generic LinkedList.
   * @param list a LinkedList
   */
  public static <T> void printList(LinkedList<T> list) {
    System.out.println(Arrays.toString(list.toArray()));
  }

  public static void main(String[] args) {
    BinaryTree<Integer> tree = new BinaryTree<Integer>();
    
    tree.addRoot(7);
    
    Node<Integer> three = new Node<Integer>(3, 1);
    Node<Integer> four = new Node<Integer>(4, 1);
    Node<Integer> two = new Node<Integer>(2, 2);
    Node<Integer> five = new Node<Integer>(5, 2);
    Node<Integer> eight = new Node<Integer>(8, 2);
    Node<Integer> one = new Node<Integer>(1, 3);
    Node<Integer> six = new Node<Integer>(6, 3);
    
    tree.root.left = three;
    tree.root.right = four;
    three.left = two;
    three.right = five;
    four.right = eight;
    two.left = one;
    two.right = six;
    
    printAncestors(tree, 6);
  }
}
