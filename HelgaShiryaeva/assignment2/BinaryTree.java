package assignment2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * This class represents a Binary tree data structure
 * @param <T> is a type of values stored in nodes of a tree
 */
public class BinaryTree <T> {
    private Set<Node<T>> nodes;
    private Node<T> root;

    public BinaryTree(Node<T> root) {
        this.root = root;
        nodes = new HashSet<>();
        nodes.add(root);
    }

    /**
     * All the nodes of a tree should be inserted into a nodes set
     * in order to avoid duplicates. A node which is not in the
     * set of nodes is not considered to be a part of a binary tree.
     * @param newNode is a node to be inserted in the nodes set
     * @throws IllegalArgumentException if node is already in a binary tree or if
     * the node with such value is already exists in the tree
     */
    public void addNode(Node<T> newNode) {
        Optional<Node<T>> nodeOptional = nodes.stream()
                .filter(e -> e.getValue().equals(newNode.getValue()))
                .findFirst();
        if (nodes.contains(newNode) || nodeOptional.isPresent()) {
            throw new IllegalArgumentException("Node with value " + newNode.getValue() + " already exists in the tree");
        }
        nodes.add(newNode);
    }

    /**
     * Prints all the ancestors ordered from parent of the node to the root of the tree
     * @param node is a node which ancestors will be printed
     * @throws OutOfTreeException if the reference for the node which
     * is not in the tree found during tree traversal
     */
    public void printAncestors(Node<T> node) throws OutOfTreeException {
        if (nodes.contains(node)) {
            printAncestorsRecursive(node, root);
            System.out.println();
        } else {
            throw new OutOfTreeException(node);
        }
    }

    private Node<T> printAncestorsRecursive(Node<T> node, Node<T> currentNode) throws OutOfTreeException {
        if (currentNode != null) {
            if (!nodes.contains(currentNode)) {
                throw new OutOfTreeException(currentNode);
            }
            if (currentNode.getValue().equals(node.getValue())) {
                return currentNode;
            }
            Node<T> left = printAncestorsRecursive(node, currentNode.getLeft());
            Node<T> right = printAncestorsRecursive(node, currentNode.getRight());
            if (left == null && right == null) {
                return null;
            } else {
                System.out.print(currentNode.getValue() + " ");
                return currentNode;
            }
        }
         return null;
    }

    /**
     * Finds the lowest common ansestor of two nodes of a binary tree
     * @return node which is the lowest common ancestor of two nodes
     * @throws OutOfTreeException if either fisrt or second or both node(s) are not a part
     * of a binary tree
     */
    public Node<T> getLca(Node<T> first, Node<T> second) throws OutOfTreeException {
        if (!nodes.contains(first) || !nodes.contains(second)) {
            throw new OutOfTreeException("Node(s} is(are) not present in a binary tree");
        }
        return getLcaRecursive(root, first, second);
    }

    private Node<T> getLcaRecursive(Node<T> current, Node<T> first, Node<T> second) throws OutOfTreeException {
        if (current == null) {
            return null;
        }
        if(!nodes.contains(current)) {
            throw new OutOfTreeException(current);
        }
        if (current == first || current == second) {
            return current;
        }
        Node<T> left = getLcaRecursive(current.getLeft(), first, second);
        Node<T> right = getLcaRecursive(current.getRight(), first, second);
        if (left != null && right != null) {
            return current;
        }
        return left != null ? left : right;
    }
}
