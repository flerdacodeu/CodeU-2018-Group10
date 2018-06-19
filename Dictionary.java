package assignment3;

import java.util.Set;

public class Dictionary {
  private Set<String> words;

  // Every word in a dictionary appears only once
  public Dictionary(Set<String> words) {
    this.words = words;
  }

  // Returns whether the given string is a valid word
  public boolean isWord(String str) {
    return words.contains(str);
  }

  // Returns whether the given string is a prefix of at least
  // one word in the dictionary
  public boolean isPrefix(String str) {
    for (String word : words) {
      if (word.startsWith(str)) {
        return true;
      }
    }
    return false;
  }
}
