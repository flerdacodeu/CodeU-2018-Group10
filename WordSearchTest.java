package assignment3;

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

    assert (wordsFound.contains("CAR"));
    assert (wordsFound.contains("CAT"));
    assert (wordsFound.contains("CARD"));
    assert (!wordsFound.contains("CAP"));
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

    assert (wordsFound.size() == 0);
  }

  @Test
  public void emptyDictionary() {
    char[][] grid = {{'A', 'A', 'R'}, {'T', 'C', 'D'}};
    HashSet<String> words = new HashSet<>();
    Dictionary dictionary = new Dictionary(words);

    Set<String> wordsFound = WordSearch.findAllWords(grid, dictionary);

    assert (wordsFound.size() == 0);
  }

}
