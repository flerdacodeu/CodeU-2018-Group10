package CodeUAss2Package;

public class Main {

    private static final int NUM_OF_NODES_TO_TEST = 10;

    public static void main(String[] args) {

        TreeNode<Integer>[] testNodes = new TreeNode[10];
        for(int i = 0 ; i < NUM_OF_NODES_TO_TEST ; i++)
        {
            testNodes[i] = new TreeNode<>(i);
        }

        testNodes[0].setRightSon(testNodes[2]);
        testNodes[0].setLeftSon(testNodes[1]);
        testNodes[2].setRightSon(testNodes[3]);
        testNodes[2].setLeftSon(testNodes[4]);
        testNodes[4].setRightSon(testNodes[5]);
        testNodes[5].setLeftSon(testNodes[6]);
        testNodes[3].setRightSon(testNodes[7]);
        testNodes[3].setLeftSon(testNodes[8]);
        testNodes[6].setRightSon(testNodes[9]);


        // initiate binary tree with nodes
        BinaryTree<Integer> binaryTree = new BinaryTree<>(testNodes[0]);


        // print binary tree
        binaryTree.printBinaryTree();

        // test printAncestors
        final int VALUE_TO_PRINT_ANCESTOR_OF = 9;
        System.out.print("\nprintAncestors of " + VALUE_TO_PRINT_ANCESTOR_OF + " : ");
        binaryTree.printAncestors(VALUE_TO_PRINT_ANCESTOR_OF);

        // test getLowestCommonAncestor
        final int FIRST_VALUE_TO_FIND_COMMON_ANCESTOR = 0;
        final int SECOND_VALUE_TO_FIND_COMMON_ANCESTOR = 7;

        System.out.println("\n\ngetLowestCommonAncestor of " +
                FIRST_VALUE_TO_FIND_COMMON_ANCESTOR + " & " +
                SECOND_VALUE_TO_FIND_COMMON_ANCESTOR + ": " +
                binaryTree.getLowestCommonAncestor(FIRST_VALUE_TO_FIND_COMMON_ANCESTOR,
                                                    SECOND_VALUE_TO_FIND_COMMON_ANCESTOR));
    }
}
