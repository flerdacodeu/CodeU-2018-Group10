import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SinglyLinkedListTest {

  @Test
  public void canAddDifferentTypesToList() {
    SinglyLinkedList<String> stringList = new SinglyLinkedList<>();
    stringList.insert("hello");
    stringList.insert("");
    stringList.insert("hi there");
    assertTrue(stringList.getSize() == 3);

    SinglyLinkedList<Integer> intList = new SinglyLinkedList<>();
    intList.insert(5);
    intList.insert(-89);
    assertTrue(intList.getSize() == 2);
  }

  @Test
  public void canGetKthElement() {
    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    list.insert(0);
    list.insert(1);
    list.insert(2);
    list.insert(3);

    //From head
    assertTrue(list.getElemAtIndex(0) == 0);
    assertTrue(list.getElemAtIndex(1) == 1);
    assertTrue(list.getElemAtIndex(3) == 3);
    assertTrue(list.getElemAtIndex(11) == null);

    //From tail
    assertTrue(list.getKthElemFromTail(0) == 3);
    assertTrue(list.getKthElemFromTail(1) == 2);
    assertTrue(list.getKthElemFromTail(2) == 1);
    assertTrue(list.getKthElemFromTail(3) == 0);
  }

}
