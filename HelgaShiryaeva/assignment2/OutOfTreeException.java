package assignment2;

/**
 * Created by Helga on 6/2/2018.
 */
public class OutOfTreeException extends Exception {
    public OutOfTreeException(Node node) {
        super("Node " + node + " with value " + node.getValue().toString() + " is not present in binary tree");
    }

    public OutOfTreeException(String message) {
        super(message);
    }
}
