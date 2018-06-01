package CodeUAss1Package;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

/**
 * This class contains the root to a binary tree that can have only
 * one node for each possible key.
 * @param <T> is the type of data stored in the tree nodes value
 */
public class BinaryTree<T> {

    private Node root;
    private Set<Integer> keySet;

    public BinaryTree() {
        keySet = new HashSet<>();
    }

    public void printBinaryTree()
    {
        printBinaryTree(this.root,0);
    }
    private void printBinaryTree(Node root, int level){
        if(root==null)
            return;
        printBinaryTree(root.getRightSon(), level+1);
        if(level!=0){
            for(int i=0;i<level-1;i++)
                System.out.print("|\t\t");
            System.out.println("|-------"+root.getKey());
        }
        else
            System.out.println(root.getKey());
        printBinaryTree(root.getLeftSon(), level+1);
    }

    /**
     * This method add a node to a random valid place in the binary tree
     * @param value - value of the added node
     * @param key - key of the added node
     * @throws KeyAlreadyExistsException if there is already a node with key as given in the parameter
     */
    public void add(T value, int key) throws KeyAlreadyExistsException
    {
        if(keySet.contains(key))
        {
            throw new KeyAlreadyExistsException();
        }
        else {
            keySet.add(key);
        }

        Node nodeToAdd = new Node(key, value);
        if(root == null)
        {
            root = nodeToAdd;
            return;
        }

        /*
        *   Moving down the tree, while choosing the path down (through right or left son)
        *  randomly, till the current node is null, and then assign to the added node
        *  to that null current node
        */
        Node currentNode = root;
        Random random = new Random();
        while(currentNode != null)
        {
            if (random.nextBoolean())
            {
                if (currentNode.getLeftSon() == null)
                {
                    currentNode.setLeftSon(nodeToAdd);
                    return;
                }
                else
                {
                    currentNode = currentNode.getLeftSon();
                }
            }
            else
            {
                if (currentNode.getRightSon() == null)
                {
                    currentNode.setRightSon(nodeToAdd);
                    return;
                }
                else
                {
                    currentNode = currentNode.getRightSon();
                }
            }
        }
    }

    /**
     * Prints all the ancestors key of the given key.
     * If there is'nt such key in the tree, nothing will be printed.
     */
    public void printAncestors(int key)
    {
        printAncestors(key,root);
    }
    private boolean printAncestors(int key, Node root)
    {
        if(root == null)
        {
            return false;
        }
        else if(root.getKey() == key)
        {
            return true;
        }
        else if(printAncestors(key,root.getLeftSon()) ||
                printAncestors(key,root.getRightSon()))
        {
            System.out.print( root==this.root ? root.getKey() : root.getKey() + ", ");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This function find the lowest common ancestor by finding the path to
     * each given key, than return the last common number in both paths.
     *
     * @param key1 - first key to find common ancestor
     * @param key2 - second key to find common ancestor
     * @return lowest common ancestor of the two given keys. If the keys are equal than
     *          the function will return the key number.
     * @throws KeyNotFoundException if one or both of the keys are not in the tree
     */
    public int getLowestCommonAncestor(int key1, int key2) throws KeyNotFoundException {

        // create paths to the two keys given
        List<Integer> firstKeyPath = getKeyPath(key1, root, new LinkedList<>());
        List<Integer> secKeyPath = getKeyPath(key2, root, new LinkedList<>());

        if(firstKeyPath == null || secKeyPath == null)
        {
            throw new KeyNotFoundException();
        }

        Collections.reverse(firstKeyPath);
        Collections.reverse(secKeyPath);

        // iterate throw the paths till they are stop intersecting - this will
        // be where the lowest common ancestor at
        int currentLowestCommonAncestor = root.key;
        Iterator IteratorFirst = firstKeyPath.iterator();
        Iterator IteratorSecond = secKeyPath.iterator();
        while (IteratorFirst.hasNext() && IteratorSecond.hasNext()) {
            int nextInFirstPath = (int) IteratorFirst.next();
            int nextInSecondPath = (int) IteratorSecond.next();
            if(nextInFirstPath == nextInSecondPath)
            {
                currentLowestCommonAncestor = nextInFirstPath;
            }
            else
            {
                break;
            }
        }
        return currentLowestCommonAncestor;
    }
    private LinkedList<Integer> getKeyPath(int key, Node root, LinkedList<Integer> linkedList)
    {
        if(root == null)
        {
            return null;
        }
        if(root.getKey() == key)
        {
            linkedList.add(root.getKey());
            return linkedList;
        }

        LinkedList leftSonPath = getKeyPath(key,root.getLeftSon(), linkedList);
        if (leftSonPath!= null) {
            linkedList.add(root.getKey());
            return leftSonPath;
        }

        LinkedList rightSonPath = getKeyPath(key,root.getRightSon(), linkedList);
        if (rightSonPath!= null) {
            linkedList.add(root.getKey());
            return rightSonPath;
        }

        return null;
    }

    private class Node
    {
        private final int key;
        private final T value;
        private Node leftSon, rightSon;

        public Node(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public Node getLeftSon() {
            return leftSon;
        }

        public void setLeftSon(Node leftSon) {
            this.leftSon = leftSon;
        }

        public Node getRightSon() {
            return rightSon;
        }

        public void setRightSon(Node rightSon) {
            this.rightSon = rightSon;
        }
    }
}
