import java.util.Arrays;

public class Anagram {

  /**
   * Determines if two strings are anagrams of each other
   * By default, isAnagram is case sensitive.
   * Input strings are unchanged.
   * Symbols not in a-z, A-Z, or space are ignored.
   * @param s1 : First string
   * @param s2 : Possible anagram of the first string
   * @return : true if s1 and s2 are anagrams of one another
   *  i.e. they are made of the same letters in a different order.
   */
  public boolean isAnagram(String s1, String s2) {
    String string1 = s1.replaceAll("[^a-zA-z ]", "");
    String string2 = s2.replaceAll("[^a-zA-z ]", "");
    if (string1.length() != string2.length()) {
      return false;
    }
    char[] chars1 = string1.toCharArray();
    char[] chars2 = string2.toCharArray();
    Arrays.sort(chars1);
    Arrays.sort(chars2);
    for (int i = 0; i < chars1.length; i++) {
      if (chars1[i] != chars2[i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Possible case insensitive version of isAnagram()
   */
  public boolean isAnagram(String s1, String s2, Boolean isCaseSensitive) {
    if (!isCaseSensitive) {
      return isAnagram(s1.toLowerCase(), s2.toLowerCase());
    }
    else {
      return isAnagram(s1, s2);
    }
  }

  /**
   * Determines is one sentence is an anagram of another.
   * Splits up sentences on whitespace characters.
   * @param s1
   * @param s2
   * @return true if s2 is a sentence such that each word is an anagram
   * of one of the words in s1. The order of letters within each word can
   * be shuffled as well as the order of the words.
   */
  public boolean isAnagramSentence(String s1, String s2) {
    String string1 = s1.replaceAll("[^a-zA-z ]", "");
    String string2 = s2.replaceAll("[^a-zA-z ]", "");
    String[] words1 = string1.split("\\s+");
    String[] words2 = string2.split("\\s+");
    for (int i = 0; i < words1.length; i++) {
      char[] chars1 = words1[i].toCharArray();
      Arrays.sort(chars1);
      words1[i] = new String(chars1);

      char[] chars2 = words2[i].toCharArray();
      Arrays.sort(chars2);
      words2[i] = new String(chars2);
    }
    Arrays.sort(words1);
    Arrays.sort(words2);
    for (int i = 0; i < words1.length; i++) {
      if (!isAnagram(words1[i], words2[i])) {
        return false;
      }
    }
    return true;
  }
}
