package assignment2;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This class represents a Binary tree data structure
 * All values stored in a binary tree are unique
 * @param <T> is a type of values stored in nodes of a tree
 */
public class BinaryTree<T> {
    private Node root;
    private Set<T> values;

    public BinaryTree(T rootValue) {
        this.root = new Node(rootValue);
        values = new HashSet<>();
        values.add(rootValue);
    }

    /**
     * This method adds a new node in a binary tree
     * If the parent of a new node already has a child on
     * the requested side an old child would be replaced by a new one
     * All values stored in a binary tree are unique
     * @param value is a value of a new node
     * @param parent is a parent of the node
     * @param side on which side of parent a new node should be added
     * */
    public void add(T value, T parent, Side side) {
        if (value == null) {
            throw new NullPointerException("'null' value can't be inserted in a tree");
        }
        if (!values.contains(value)) {
            throw new IllegalArgumentException("Node with value " + value + " already exist in a binary tree");
        }
        Node newNode = new Node(value);
        Node parentNode = findNodeByValue(parent);
        if (side.equals(Side.LEFT)) {
            if (parentNode.getLeft() != null) {
                values.remove(parentNode.getLeft().getValue());
            }
            parentNode.setLeft(newNode);
        } else {
            if (parentNode.getRight() != null) {
                values.remove(parentNode.getRight().getValue());
            }
            parentNode.setRight(newNode);
        }
        values.add(value);
    }

    private Node findNodeByValue(T value) {
        if (!values.contains(value)) {
            throw new NoSuchElementException("Node with value " + value + " doesn't exist in a binary tree");
        }
        return findNodeByValueRecursive(root, value);
    }

    private Node findNodeByValueRecursive(Node current, T value) {
        if (current == null) {
            return null;
        }
        if (current.getValue().equals(value)) {
            return current;
        }
        Node left = findNodeByValueRecursive(current.getLeft(), value);
        if (left != null) {
            return left;
        } else {
            return findNodeByValueRecursive(current.getRight(), value);
        }
    }

    /**
     * Prints all the ancestors ordered from parent of the node to the root of the tree
     * @param value is a value of the tree node which ancestors will be printed
     * @throws NullPointerException if the node with such value doesn't exist in a tree
     */
    public void printAncestors(T value)  {
        if (!values.contains(value)) {
            throw new NoSuchElementException("Node with value " + value + " doesn't exist in a binary tree");
        }
            printAncestorsRecursive(root, value);
            System.out.println();
    }

    private Node printAncestorsRecursive(Node currentNode, T value) {
        if (currentNode != null) {
            if (currentNode.getValue().equals(value)) {
                return currentNode;
            }
            Node left = printAncestorsRecursive(currentNode.getLeft(), value);
            if (left != null) {
                System.out.print(currentNode.getValue() + " ");
                return currentNode;
            }
            Node right = printAncestorsRecursive(currentNode.getRight(), value);
            if (right == null) {
                return null;
            } else {
                System.out.print(currentNode.getValue() + " ");
                return currentNode;
            }
        }
        return null;
    }

    /**
     * Finds the lowest common ancestor of two nodes of a binary tree
     * @return value of the node which is the lowest common ancestor of two nodes
     * @throws NoSuchElementException if either first or second or both node(s) are not a part
     * of a binary tree
     */
    public T getLca(T first, T second) {
        if (!values.contains(first) || !values.contains(second)) {
            throw new NoSuchElementException("Node(s} is(are) not present in a binary tree");
        }
        return getLcaRecursive(root, first, second).getValue();
    }

    private Node getLcaRecursive(Node current, T first, T second) {
        if (current == null) {
            return null;
        }
        if (current.getValue().equals(first) || current.getValue().equals(second)) {
            return current;
        }
        Node left = getLcaRecursive(current.getLeft(), first, second);
        Node right = getLcaRecursive(current.getRight(), first, second);
        if (left != null && right != null) {
            return current;
        }
        return left != null ? left : right;
    }

    /**
     * This class represents a node of a binary tree
     */
        class Node {
        private final T value;
        private Node left;
        private Node right;

        Node(T value) {
            this.value = value;
        }

        T getValue() {
            return value;
        }

        Node getLeft() {
            return left;
        }

        void setLeft(Node left) {
            this.left = left;
        }

        Node getRight() {
            return right;
        }

        void setRight(Node right) {
            this.right = right;
        }
    }
}
