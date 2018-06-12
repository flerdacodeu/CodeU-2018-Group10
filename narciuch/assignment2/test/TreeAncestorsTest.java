import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class TreeAncestorsTest {
  TreeAncestors ancestorFinder = new TreeAncestors();
  Node<Integer> seven = new Node(7);

  private Node<Integer> getTestTree() {
    Node<Integer> three = seven.addLeftChild(3);
    Node<Integer> four = seven.addRightChild(4);
    Node<Integer> two = three.addLeftChild(2);
    Node<Integer> five = three.addRightChild(5);
    Node<Integer> eight = four.addRightChild(8);
    Node<Integer> one = two.addLeftChild(1);
    Node<Integer> six = two.addRightChild(6);
    return seven;
  }

  @Test
  public void noAncestorsInEmptyTree() {
    assertTrue(ancestorFinder.getAncestors(7, null).size() == 0);
  }

  @Test
  public void noAncestorsInOneElemTree() {
    assertTrue(ancestorFinder.getAncestors(7, new Node(65)).size() == 0);
    assertTrue(ancestorFinder.getAncestors(7, new Node(7)).size() == 0);
  }

  @Test
  public void noAncestorsIfKeyNotInTree() {
    assertTrue(ancestorFinder.getAncestors(44, getTestTree()).size() == 0);
  }

  @Test
  public void correctlyIdentifiesAncestors() {
    Node<Integer> three = seven.addLeftChild(3);
    Node<Integer> four = seven.addRightChild(4);
    Node<Integer> two = three.addLeftChild(2);
    Node<Integer> five = three.addRightChild(5);
    Node<Integer> eight = four.addRightChild(8);
    Node<Integer> one = two.addLeftChild(1);
    Node<Integer> six = two.addRightChild(6);

    List<Node<Integer>> sixAncestors = new ArrayList<>();
    sixAncestors.add(two);
    sixAncestors.add(three);
    sixAncestors.add(seven);
    assertTrue(ancestorFinder.getAncestors(6, seven).equals(sixAncestors));

    List<Node<Integer>> threeAncestors = new ArrayList<>();
    threeAncestors.add(seven);
    assertTrue(ancestorFinder.getAncestors(3, seven).equals(threeAncestors));
  }

  @Test
  public void returnsAncestorsOfAllInstancesIfNonUniqueKeys() {

    //Two instances of the key '6'
    Node<Integer> three = seven.addLeftChild(3);
    Node<Integer> four = seven.addRightChild(4);
    Node<Integer> two = three.addLeftChild(2);
    Node<Integer> five = three.addRightChild(5);
    Node<Integer> six2 = four.addRightChild(6);
    Node<Integer> one = two.addLeftChild(1);
    Node<Integer> six = two.addRightChild(6);

    List<Node<Integer>> sixAncestors = new ArrayList<>();
    sixAncestors.add(four);
    sixAncestors.add(seven);
    sixAncestors.add(two);
    sixAncestors.add(three);
    sixAncestors.add(seven);
    assertTrue(ancestorFinder.getAncestors(6, seven).equals(sixAncestors));

    //Three instances of the key '6'
    Node<Integer> nine = four.addLeftChild(9);
    Node<Integer> six3 = nine.addLeftChild(6);
    assertTrue(ancestorFinder.getAncestors(6, seven).contains(nine));
    assertTrue(ancestorFinder.getAncestors(6, seven).contains(four));
    assertTrue(ancestorFinder.getAncestors(6, seven).contains(two));
    assertTrue(ancestorFinder.getAncestors(6, seven).contains(three));
    assertTrue(ancestorFinder.getAncestors(6, seven).contains(seven));

  }


  @Test
  public void noCommonAncestorsInEmptyTree() {
    assertTrue(ancestorFinder.getLowestCommonAncestor(new Node(7), new Node(9), null) == null);
  }

  @Test
  public void noCommonAncestorsInOneElemTree() {
    assertTrue(ancestorFinder.getLowestCommonAncestor(new Node(7), new Node(9), new Node(9)) == null);
  }

  @Test
  public void noCommonAncestorsIfKeyNotInTree() {
    assertTrue(ancestorFinder.getLowestCommonAncestor(new Node(44), new Node(9), getTestTree()) == null);
  }

  @Test
  public void correctlyIdentifiesLowestCommonAncestor() {
    assertTrue(ancestorFinder.getLowestCommonAncestor(new Node(1), new Node(6), getTestTree()).getValue().equals(2));
    assertTrue(ancestorFinder.getLowestCommonAncestor(new Node(1), new Node(5), getTestTree()).getValue().equals(3));
    assertTrue(ancestorFinder.getLowestCommonAncestor(new Node(2), new Node(8), getTestTree()).getValue().equals(7));
    assertTrue(ancestorFinder.getLowestCommonAncestor(new Node(1), new Node(3), getTestTree()).getValue().equals(3));
  }
}
