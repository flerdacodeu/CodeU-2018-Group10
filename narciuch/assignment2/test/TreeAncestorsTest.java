import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class TreeAncestorsTest {
  TreeAncestors ancestorFinder = new TreeAncestors();
  Node seven = new Node(7);

  private Node getTestTree() {
    Node three = new Node(3);
    Node four = new Node(4);
    Node two = new Node(2);
    Node five = new Node(5);
    Node eight = new Node(8);
    Node one = new Node(1);
    Node six = new Node(6);
    seven.setLeft(three);
    seven.setRight(four);
    four.setRight(eight);
    three.setLeft(two);
    three.setRight(five);
    two.setLeft(one);
    two.setRight(six);
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
    Node three = new Node(3);
    Node four = new Node(4);
    Node two = new Node(2);
    Node five = new Node(5);
    Node eight = new Node(8);
    Node one = new Node(1);
    Node six = new Node(6);
    seven.setLeft(three);
    seven.setRight(four);
    four.setRight(eight);
    three.setLeft(two);
    three.setRight(five);
    two.setLeft(one);
    two.setRight(six);

    List<Node> sixAncestors = new ArrayList<>();
    sixAncestors.add(two);
    sixAncestors.add(three);
    sixAncestors.add(seven);
    assertTrue(ancestorFinder.getAncestors(6, seven).equals(sixAncestors));

    List<Node> threeAncestors = new ArrayList<>();
    threeAncestors.add(seven);
    assertTrue(ancestorFinder.getAncestors(3, seven).equals(threeAncestors));
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
