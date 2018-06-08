package CodeUAss2Package;

public class TreeNode<T> {
    private final T value;
    private TreeNode<T> leftSon, rightSon, parent;
    private int level;

    public TreeNode(T value)
    {
        this.value = value;
        level = 0;
    }

    public T getValue()
    {
        return value;
    }

    public TreeNode<T> getLeftSon()
    {
        return leftSon;
    }


    public void setLeftSon(TreeNode<T> leftSon)
    {
        this.leftSon = leftSon;
        leftSon.setParent(this);
        leftSon.setLevel(this.level +1);
    }

    public TreeNode<T> getRightSon()
    {
        return rightSon;
    }

    public void setRightSon(TreeNode<T> rightSon)
    {
        this.rightSon = rightSon;
        rightSon.setParent(this);
        rightSon.setLevel(this.level +1);
    }

    public TreeNode<T> getParent()
    {
        return parent;
    }

    private void setParent(TreeNode<T> parent)
    {
        this.parent = parent;
    }

    public int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        this.level = level;

        if(rightSon != null)
        {
            rightSon.setLevel(level+1);
        }
        if(leftSon != null)
        {
            leftSon.setLevel(level+1);
        }
    }
}
