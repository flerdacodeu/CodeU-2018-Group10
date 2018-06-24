import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;

public class WordSearchTest {
  String[] words = {"CAR", "CARD", "CART", "CAT"};
  Dictionary dict = new Dictionary(words);
  WordSearch wordSearch = new WordSearch();
  char[][] grid = {{'A', 'A', 'R'}, {'T', 'C', 'D'}};

  @Test
  public void findsWordsInGrid() {
    Set<String> wordsFound = wordSearch.findWords(grid, dict);
    assert(wordsFound.equals(new TreeSet<>(Arrays.asList("CAR", "CARD", "CAT"))));
  }

  @Test
  public void findsWordsInGridLarger() {
    String[] words = {"DOG", "LOG", "GONE", "LONE", "ILL", "APPLE", "NOTINGRID", "NOPE"};
    Dictionary dict = new Dictionary(words);
    char[][] grid = {{'I', 'N', 'E', 'A'}, {'L', 'O', 'G', 'M'}, {'L', 'D', 'R', 'L'}, {'S', 'C', 'F', 'O'}};
    Set<String> wordsFound = wordSearch.findWords(grid, dict);
    assert(wordsFound.equals(new TreeSet<>(Arrays.asList("DOG", "LOG", "GONE", "LONE", "ILL"))));
  }

  @Test
  public void returnsMultipleInstancesOfWordIfSameWordMultipleTimesInGrid() {
    String[] words = {"A", "AA"};
    Dictionary dict = new Dictionary(words);
    char[][] grid = {{'A', 'A'}, {'A', 'A'}, {'A', 'A'}};
    Set<String> wordsFound = wordSearch.findWords(grid, dict);
    assert(wordsFound.equals(new TreeSet<>(Arrays.asList("A", "AA"))));
  }

  @Test
  public void returnsEmptyWordListWhenGridEmpty() {
    char[][] emptyGrid = {};
    Set<String> words = wordSearch.findWords(emptyGrid, dict);
    assert(words.size() == 0);
  }

  @Test
  public void returnsEmptyWordListWhenDictionaryEmpty() {
    String[] noWordsInDict = {};
    Dictionary emptyDict = new Dictionary(noWordsInDict);
    Set<String> wordsFound = wordSearch.findWords(grid, emptyDict);
    assert(wordsFound.size() == 0);
  }
}
