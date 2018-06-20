import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FindAllWordsTest {

  @Test
  public void testFindAllWords() {
    String[][] array = new String[][] {
      {"A", "A", "R"},
      {"T", "C", "D"}
    };
    
    Grid grid = new Grid(2, 3);
    grid.populate(array);
    
    Dictionary dictionary = new Dictionary();
    dictionary.addWords(Arrays.asList("CAR", "CARD", "CART", "CAT"));
    
    Set<String> wordSet = FindAllWords.findAllWords(grid, dictionary);
    
    Set<String> expected = new HashSet<String>();
    expected.add("CAR");
    expected.add("CARD");
    expected.add("CAT");
    
    assertEquals(expected, wordSet);
    assertFalse(dictionary.getWordSet().equals(wordSet));
  }

  @Test
  public void testSearchWords() {
    String[][] array = new String[][] {
      {"A", "A", "R"},
      {"T", "C", "D"}
    };
    
    Grid grid = new Grid(2, 3);
    grid.populate(array);
    
    Dictionary dictionary = new Dictionary();
    dictionary.addWords(Arrays.asList("CAR", "CARD", "CART", "CAT"));
    
    Set<String> wordSet = FindAllWords.searchWords(
        grid,
        dictionary,
        new boolean[grid.getHeight()][grid.getWidth()],
        new HashSet<String>(),
        new ArrayList<String>(),
        1,
        1);
    
    Set<String> expected = new HashSet<String>();
    expected.add("CAR");
    expected.add("CARD");
    expected.add("CAT");
    
    assertEquals(expected, wordSet);
    assertFalse(dictionary.getWordSet().equals(wordSet));
  }
}
