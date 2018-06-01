package CodeUAss1Package;

public class Main {

    public static void main(String[] args) throws KeyNotFoundException {

        // creating and fill binary tree
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        final int NUM_OF_NODES = 12;
        for(int i = 1 ; i <= NUM_OF_NODES ; i++)
        {
            binaryTree.add(1,i);
        }

        // print binary tree
        binaryTree.printBinaryTree();

        // test printAncestors
        final int KEY_TO_PRINT_ANCESTOR_OF = 12;
        System.out.print("\nprintAncestors of " + KEY_TO_PRINT_ANCESTOR_OF + " : ");
        binaryTree.printAncestors(KEY_TO_PRINT_ANCESTOR_OF);

        // test getLowestCommonAncestor
        final int FIRST_KEY_TO_FIND_COMMON_ANCESTOR = 7;
        final int SECOND_KEY_TO_FIND_COMMON_ANCESTOR = 11;
        System.out.println("\n\ngetLowestCommonAncestor of " +
                FIRST_KEY_TO_FIND_COMMON_ANCESTOR + " & " +
                SECOND_KEY_TO_FIND_COMMON_ANCESTOR + ": " +
                binaryTree.getLowestCommonAncestor(FIRST_KEY_TO_FIND_COMMON_ANCESTOR,
                                                    SECOND_KEY_TO_FIND_COMMON_ANCESTOR));
    }
}
