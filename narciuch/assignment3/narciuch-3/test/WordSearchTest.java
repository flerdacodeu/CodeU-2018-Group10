import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WordSearchTest {
  String[] words = {"CAR", "CARD", "CART", "CAT"};
  Dictionary dict = new Dictionary(words);
  WordSearch wordSearch = new WordSearch();
  char[][] grid = {{'A', 'A', 'R'}, {'T', 'C', 'D'}};

  @Test
  public void findsWordsInGrid() {
    List<String> wordsFound = wordSearch.findWords(grid, dict);
    assert(wordsFound.contains("CAR"));
    assert(wordsFound.contains("CARD"));
    assert(wordsFound.contains("CAT"));
  }

  @Test
  public void findsWordsInGridLarger() {
    String[] words = {"DOG", "LOG", "GONE", "LONE", "ILL", "APPLE", "NOTINGRID", "NOPE"};
    Dictionary dict = new Dictionary(words);
    char[][] grid = {{'I', 'N', 'E', 'A'}, {'L', 'O', 'G', 'M'}, {'L', 'D', 'R', 'L'}, {'S', 'C', 'F', 'O'}};
    List<String> wordsFound = wordSearch.findWords(grid, dict);
    assert(wordsFound.contains("DOG"));
    assert(wordsFound.contains("LOG"));
    assert(wordsFound.contains("GONE"));
    assert(wordsFound.contains("LONE"));
    assert(wordsFound.contains("ILL"));
    assert(!wordsFound.contains("APPLE"));
    assert(!wordsFound.contains("NOTINGRID"));
    assert(!wordsFound.contains("NOPE"));
  }

  @Test
  public void returnsMultipleInstancesOfWordIfSameWordMultipleTimesInGrid() {
    String[] words = {"A", "AA"};
    Dictionary dict = new Dictionary(words);
    char[][] grid = {{'A', 'A'}, {'A', 'A'}, {'A', 'A'}};
    List<String> wordsFound = wordSearch.findWords(grid, dict);
    List<String> expectedWords = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      expectedWords.add("A");
    }
    for (int i = 0; i < 12; i++) {
      expectedWords.add("AA");
    }
    assert(wordsFound.containsAll(expectedWords));
  }

  @Test
  public void returnsEmptyWordListWhenGridEmpty() {
    char[][] emptyGrid = {};
    List<String> words = wordSearch.findWords(emptyGrid, dict);
    assert(words.size() == 0);
  }

  @Test
  public void returnsEmptyWordListWhenDictionaryEmpty() {
    String[] noWordsInDict = {};
    Dictionary emptyDict = new Dictionary(noWordsInDict);
    List<String> wordsFound = wordSearch.findWords(grid, emptyDict);
    assert(wordsFound.size() == 0);
  }
}
