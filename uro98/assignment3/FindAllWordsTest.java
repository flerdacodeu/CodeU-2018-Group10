import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FindAllWordsTest {

  @Test
  public void testOnlyPossibleWordsAreFound() {
    char[][] array = new char[][] {
      {'A', 'A', 'R'},
      {'T', 'C', 'D'}
    };
    
    Grid grid = new Grid(2, 3);
    grid.populate(array);
    
    Dictionary dictionary = new Dictionary();
    dictionary.addWords(Arrays.asList("CAR", "CARD", "CART", "CAT", "RAT", "TAR"));
    
    Set<String> wordSet = FindAllWords.findAllWords(grid, dictionary);
    
    Set<String> expected = new HashSet<String>();
    expected.add("CAR");
    expected.add("CARD");
    expected.add("CAT");
    expected.add("RAT");
    expected.add("TAR");
    
    assertEquals(expected, wordSet);
  }
  
  @Test
  public void testEmptyGrid() {
    Grid grid = new Grid(0, 0);
    
    Dictionary dictionary = new Dictionary();
    dictionary.addWords(Arrays.asList("CAR", "CARD", "CART", "CAT"));
    
    Set<String> wordSet = FindAllWords.findAllWords(grid, dictionary);
    
    Set<String> expected = new HashSet<String>();
    
    assertEquals(expected, wordSet);
  }
  
  @Test
  public void testEmptyDictionary() {
    char[][] array = new char[][] {
      {'A', 'A', 'R'},
      {'T', 'C', 'D'}
    };
    
    Grid grid = new Grid(2, 3);
    grid.populate(array);
    
    Dictionary dictionary = new Dictionary();
    
    Set<String> wordSet = FindAllWords.findAllWords(grid, dictionary);
    
    Set<String> expected = new HashSet<String>();
    
    assertEquals(expected, wordSet);
  }
}
