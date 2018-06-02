package assignment2;

/**
 * This class represents a node of a binary tree
 * @param <T> is a type of value of the node
 */
public class Node <T> {
    private T value;
    private Node left;
    private Node right;

    public Node(T value) {
        this.value = value;
        left = right = null;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
