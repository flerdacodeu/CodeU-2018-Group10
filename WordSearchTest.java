package assignment3;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class WordSearchTest {
  WordSearch wordSearch = new WordSearch();

  @Test
  public void findAllWords() {
    char[][] grid = {{'A', 'A', 'R'}, {'T', 'C', 'D'}};
    HashSet<String> words = new HashSet<>();
    words.add("CAR");
    words.add("CARD");
    words.add("CART");
    words.add("CAT");
    Dictionary dictionary = new Dictionary(words);

    Set<String> wordsFound = WordSearch.findAllWords(grid, dictionary);
    
    Set<String> wordsExpected = new HashSet<>();
    wordsExpected.add("CAR");
    wordsExpected.add("CARD");
    wordsExpected.add("CAT");

    assertTrue (wordsFound.contains("CAR"));
    assertFalse (wordsFound.contains("CAP"));
    assertEquals (wordsExpected, wordsFound);
  }

  @Test
  public void emptyGrid() {
    char[][] grid = {};
    HashSet<String> words = new HashSet<>();
    words.add("CAR");
    words.add("CARD");
    words.add("CART");
    words.add("CAT");
    Dictionary dictionary = new Dictionary(words);

    Set<String> wordsFound = WordSearch.findAllWords(grid, dictionary);

    assertTrue (wordsFound.isEmpty());
  }

  @Test
  public void emptyDictionary() {
    char[][] grid = {{'A', 'A', 'R'}, {'T', 'C', 'D'}};
    HashSet<String> words = new HashSet<>();
    Dictionary dictionary = new Dictionary(words);

    Set<String> wordsFound = WordSearch.findAllWords(grid, dictionary);

    assertTrue (wordsFound.isEmpty());
  }

}
