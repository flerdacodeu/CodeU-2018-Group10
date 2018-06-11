
public class LowestCommonAncestor {
  
  /**
   * Find the lowest common ancestor of two nodes. This method assumes both nodes are in the tree.
   * @param tree  a generic binary tree
   * @param node1 a generic node in the tree
   * @param node2 a generic node in the tree
   * @return      the value of the lowest common ancestor
   */
  public static <T> T lowestCommonAncestor(BinaryTree<T> tree, Node<T> node1, Node<T> node2) {
    
    Node<T> temp = node2;
    
    // For each ancestor of node1, look for an identical ancestor of node2
    while (!node1.equals(tree.getRoot())) {
      node1 = node1.getParent();
      temp = node2;
      
      while (!temp.equals(tree.getRoot())) {
        temp = temp.getParent();
        
        if (node1.getData().equals(temp.getData())) {
          return node1.getData();
        }
      }
    }
    
    return tree.getRoot().getData();
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

    System.out.println(lowestCommonAncestor(tree, six, five));
  }
}
