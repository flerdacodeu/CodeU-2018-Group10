import java.util.Arrays;
import java.util.List;

public class Dictionary {
  private static List<String> words;

  public Dictionary(String[] words) {
    this.words = Arrays.asList(words);
  }

  //Returns whether the given string is a valid word.
  boolean isWord(String str) {
    return words.contains(str);
  }

  //Returns whether the given string is a prefix of
  //at least one word in the dictionary
  boolean isPrefix(String str) {
    for (String word : words) {
      if (word.startsWith(str)) {
        return true;
      }
    }
    return false;
  }
}
