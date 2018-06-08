package CodeUAss2Package;

/**
 * This class contains the root to a binary tree that can have only
 * one node for each possible value.
 * @param <T> is the type of data stored in the tree nodes value
 */
public class BinaryTree<T> {

    private TreeNode<T> root;


    public BinaryTree(TreeNode root)
    {
        this.root = root;
    }

    public void printBinaryTree()
    {
        printBinaryTree(this.root,0);
    }
    private void printBinaryTree(TreeNode root, int level){
        if(root==null)
        {
            return;
        }
        printBinaryTree(root.getRightSon(), level+1);
        if(level!=0)
        {
            for(int i=0;i<level-1;i++)
            {
                System.out.print("|\t\t");
            }
            System.out.println("|-------"+root.getValue());
        }
        else
        {
            System.out.println(root.getValue());
        }
        printBinaryTree(root.getLeftSon(), level+1);
    }


    /**
     * Prints all the ancestors value of the given value.
     * If there is'nt such value in the tree, nothing will be printed.
     */
    public void printAncestors(int value)
    {
        printAncestors(value,root);
    }
    private boolean printAncestors(int value, TreeNode root)
    {
        if(root == null)
        {
            return false;
        }
        else if(root.getValue().equals(value))
        {
            return true;
        }
        else if(printAncestors(value,root.getLeftSon()) ||
                printAncestors(value,root.getRightSon()))
        {
            System.out.print( root==this.root ? root.getValue() : root.getValue() + ", ");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This function find the lowest common ancestor by traversing up the tree
     * of each given node till reaching a common ancestor, which is the lowest
     *
     * @param value1 - first value to find common ancestor
     * @param value2 - second value to find common ancestor
     * @return lowest common ancestor of the two given values. If the values are equal than
     *          the function will return the value number.
     * @throws IllegalArgumentException if one or both of the values are not in the tree
     */
    public T getLowestCommonAncestor(T value1, T value2) throws IllegalArgumentException {

        TreeNode<T> nodeInPathOfVal1 = findNodeByVal(value1, root);
        TreeNode<T> nodeInPathOfVal2 = findNodeByVal(value2, root);

        if(nodeInPathOfVal1 == null || nodeInPathOfVal2 == null)
        {
            throw new IllegalArgumentException("Value not found");
        }
        else if(nodeInPathOfVal1.equals(nodeInPathOfVal2))
        {
            return nodeInPathOfVal1.getValue();
        }
        else if(nodeInPathOfVal1.equals(root) || nodeInPathOfVal2.equals(root))
        {
            return root.getValue();
        }

        while (nodeInPathOfVal1.getLevel() != 0 && nodeInPathOfVal2.getLevel()!=0)
        {
            if(nodeInPathOfVal1.equals(nodeInPathOfVal2))
            {
                return nodeInPathOfVal1.getValue();
            }
            else if(nodeInPathOfVal1.getLevel() < nodeInPathOfVal2.getLevel())
            {
                nodeInPathOfVal2 = nodeInPathOfVal2.getParent();
            }
            else if(nodeInPathOfVal1.getLevel() > nodeInPathOfVal2.getLevel())
            {
                nodeInPathOfVal1 = nodeInPathOfVal1.getParent();
            }
            else if (nodeInPathOfVal1.getLevel() == nodeInPathOfVal2.getLevel())
            {
                nodeInPathOfVal1 = nodeInPathOfVal1.getParent();
                nodeInPathOfVal2 = nodeInPathOfVal2.getParent();
            }
        }

        return null;
    }

    private TreeNode<T> findNodeByVal(T value1, TreeNode<T> root)
    {
        if(root == null)
        {
            return null;
        }
        else if(root.getValue() == value1)
        {
            return root;
        }

        TreeNode<T> resultFromLeft = findNodeByVal(value1,root.getLeftSon());

        if (resultFromLeft != null)
        {
            return  resultFromLeft;
        }

        TreeNode<T> resultFromRight = findNodeByVal(value1,root.getRightSon());
        if(resultFromRight != null)
        {
            return resultFromRight;
        }
        else
        {
            return null;
        }
    }
}
