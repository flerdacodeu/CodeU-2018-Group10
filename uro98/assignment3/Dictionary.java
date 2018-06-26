import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a collection of words.
 */
public class Dictionary {
  private final Set<String> wordSet = new HashSet<String>();
  private final Set<String> prefixSet = new HashSet<String>();

  public Set<String> getWordSet() {
    return wordSet;
  }
  
  /**
   * Store the word and its prefixes in the dictionary.
   * @param word String
   */
  public void addWord(String word) {
    wordSet.add(word);
    
    for (int i = 1; i <= word.length(); i++) {
      prefixSet.add(word.substring(0, i));
    }
  }
  
  /**
   * Store the list of words and their prefixes in the dictionary.
   * @param words list of Strings
   */
  public void addWords(List<String> words) {
    for (String word : words) {
      addWord(word);
    }
  }
  
  /**
   * Returns true if the word exists in the dictionary.
   * @param word String
   * @return boolean
   */
  public boolean isWord(String word) {
    return wordSet.contains(word);
  }
  
  /**
   * Returns true if the prefix exists in the dictionary.
   * @param prefix String
   * @return boolean
   */
  public boolean isPrefix(String prefix) {
    return prefixSet.contains(prefix);
  }
}
