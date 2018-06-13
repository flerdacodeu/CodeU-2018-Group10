package CodeUAss1Package;

public class KthElementLinkedList<T> {

    private Node<T> head;

    private Node<T> getHead() { return head; }

    private boolean isEmpty()
    {
        return head==null;
    }

    /**
     * add new node to the end of the linkedList with the given data
     *
     * @param data is assign to a new node, and the latter is added to the
     *             end of the list
     */
    public void add(T data)
    {
        if (isEmpty()) {
            head = new Node<>(data);
        }
        else
        {
            Node lastNode = head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }
            lastNode.setNext(new Node<>(data));
        }
    }


    /**
     * find the kth to last element, and returns the data inside it
     *
     * @param k - determines how many nodes from the end will the data be
     * @return data of kth to the last node element
     *
     * Traversing with two pointers which point to nodes separated by k nodes,
     * and returns the data of the first node when the latter reaches
     * the end node of the list
     */
    public T getKthLastElement(int k)
    {

        Node currentNode = getHead();
        for(int i = 0 ; i < k ; i++)
        {
            if (currentNode == null) {
                return null;
            }
            currentNode = currentNode.getNext();
        }

        Node kthBackNode = getHead();
        while (currentNode.getNext() != null)
        {
            kthBackNode = kthBackNode.getNext();
            currentNode = currentNode.getNext();
        }

        return (T) kthBackNode.getData();
    }



    private static class Node<T>
    {
        private final T data;
        private Node next;

        private Node(T data)
        {
            this.data = data;
        }

        private Node getNext()
        {
            return next;
        }

        private T getData()
        {
            return data;
        }

        private void setNext(Node next)
        {
            this.next = next;
        }
    }
}
