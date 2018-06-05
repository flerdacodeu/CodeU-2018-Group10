
public class LowestCommonAncestor {
  
  /**
   * Find the lowest common ancestor of two nodes. This method assumes both nodes are in the tree.
   * @param tree  a generic binary tree
   * @param node1 a generic node in the tree
   * @param node2 a generic node in the tree
   * @return      the value of the lowest common ancestor
   */
  public static <T> T lowestCommonAncestor(BinaryTree<T> tree, Node<T> node1, Node<T> node2) {
    
    int depth1 = node1.depth;
    int depth2 = node2.depth;
    Node<T> temp = node2;
    
    // For each ancestor of node1, look for an identical ancestor of node2
    for (int i = depth1; i > 0; i--) {
      node1 = node1.parent;
      node2 = temp;
      
      for (int j = depth2; j > 0; j--) {
        node2 = node2.parent;
        
        if (node1.data.equals(node2.data)) {
          return node1.data;
        }
      }
    }
    
    return tree.root.data;
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
    
    three.parent = tree.root;
    four.parent = tree.root;
    two.parent = three;
    five.parent = three;
    eight.parent = four;
    one.parent = two;
    six.parent = two;
    
    System.out.println(lowestCommonAncestor(tree, six, five));
  }
}
