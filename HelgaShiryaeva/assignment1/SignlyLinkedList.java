package assignment1;


public class SignlyLinkedList<T> {
    private Node tail;
    private int size;

    public SignlyLinkedList() {
        tail = null;
        size = 0;
    }

    public void add(T data) {
        Node newNode = new Node(data);
        Node current = tail;
        if (tail == null) {
            tail = newNode;
        } else {
            newNode.setPrevious(current);
            tail = newNode;
          }
        size++;
    }

    private Node getNode(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Index " + index + " is out of bound. Current size : " + size + ".");
        }
        Node current = tail;
        for (int i = 1; i <= index; i++) {
            current = current.getPrevious();
        }
        return current;
    }

    public int size() {
        return this.size;
    }

    /**
     * Allows to access SinglyLinkedList element at 'index'
     * position counting from tail
     * @param index is the number of the kth last element in a list
     * @return value of the kth last element in a list
     */
    public  T getByIndexFromTail(int index) {
        Node node = getNode(index);
        if (node != null) {
            return node.value;
        } else {
            throw new IllegalArgumentException("Index " + index + " is out of bound");
        }
    }

    public int getSize() {
        return size;
    }

    private class Node {
        Node previous;
        T value;

        Node(T value) {
            this(value, null);
        }

        Node(T value, Node previous) {
            this.previous = previous;
            this.value = value;
        }

        Node getPrevious() {
            return this.previous;
        }

        void setPrevious(Node previous) {
            this.previous = previous;
        }
    }
}
