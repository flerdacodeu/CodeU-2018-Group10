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
            BinaryTree<Integer> binaryTree = new BinaryTree<>(4);
            binaryTree.add(2, 4, Side.LEFT);
            binaryTree.add(6, 4, Side.RIGHT);
            binaryTree.add(1, 2, Side.LEFT);
            binaryTree.add(3, 2, Side.RIGHT);
            binaryTree.add(9, 3, Side.LEFT);
            binaryTree.add(10, 9, Side.RIGHT);
            binaryTree.add(5,6, Side.LEFT);
            binaryTree.add(7, 6, Side.RIGHT);

            binaryTree.printAncestors(10);
            binaryTree.printAncestors(4);
            binaryTree.printAncestors(7);
            System.out.println(binaryTree.getLca(4, 10));
            System.out.println(binaryTree.getLca(4, 4));
            System.out.println(binaryTree.getLca(10, 7));
            System.out.println(binaryTree.getLca(7, 10));
            System.out.println(binaryTree.getLca(7, 7));
            System.out.println(binaryTree.getLca(10, 2));
            System.out.println(binaryTree.getLca(7, 5));

            BinaryTree<Integer> binaryTree1 = new BinaryTree<>(1);
            binaryTree1.add(2, 1, Side.LEFT);
            binaryTree1.printAncestors(2);
    }
}
