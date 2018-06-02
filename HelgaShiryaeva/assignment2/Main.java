package assignment2;

/**
 * test tree for the assignment
 * the way it looks :
 *               4
 *           2       6
 *         1   3   5   7
 *           9
 *             10
 */
public class Main {
    public static void main(String... args) {
        try {
            Node<Integer> root = new Node<>(4);
            Node<Integer> node1 = new Node<>(1);
            Node<Integer> node2 = new Node<>(2);
            Node<Integer> node3 = new Node<>(3);
            Node<Integer> node5 = new Node<>(5);
            Node<Integer> node6 = new Node<>(6);
            Node<Integer> node7 = new Node<>(7);
            Node<Integer> node9 = new Node<>(9);
            Node<Integer> node10 = new Node<>(10);
            BinaryTree<Integer> binaryTree = new BinaryTree<>(root);
            root.setLeft(node2);
            root.setRight(node6);
            node2.setLeft(node1);
            node2.setRight(node3);
            node3.setLeft(node9);
            node9.setRight(node10);
            node6.setLeft(node5);
            node6.setRight(node7);
            binaryTree.addNode(node2);
            binaryTree.addNode(node6);
            binaryTree.addNode(node1);
            binaryTree.addNode(node3);
            binaryTree.addNode(node9);
            binaryTree.addNode(node10);
            binaryTree.addNode(node5);
            binaryTree.addNode(node7);
            binaryTree.printAncestors(node10);
            binaryTree.printAncestors(root);
            binaryTree.printAncestors(node7);
            System.out.println(binaryTree.getLca(root, node10).getValue());
            System.out.println(binaryTree.getLca(root, root).getValue());
            System.out.println(binaryTree.getLca(node10, node7).getValue());
            System.out.println(binaryTree.getLca(node7, node10).getValue());
            System.out.println(binaryTree.getLca(node7, node7).getValue());
            System.out.println(binaryTree.getLca(node10, node2).getValue());
            System.out.println(binaryTree.getLca(node7, node5).getValue());

            Node<Integer> integerNode1 = new Node<>(1);
            Node<Integer> integerNode2 = new Node<>(2);
            integerNode1.setLeft(integerNode2);
            BinaryTree<Integer> binaryTree1 = new BinaryTree<>(integerNode1);
            binaryTree1.addNode(integerNode2);
            binaryTree1.printAncestors(integerNode2);
        } catch (OutOfTreeException e) {
            System.out.println(e.getMessage());
        }
    }
}
