import java.util.Arrays;

public class Anagram {

  /**
   * Used in isAnagram() and isAnagramSentence()
   * to ignore all characters not in a-z / A-Z / space.
   */
  private static final String replaceRegex = "[^a-zA-z ]";

  /**
   * Determines if two strings are anagrams of each other,
   * i.e. they are made of the same letters in a different order.
   * By default, this method is case sensitive.
   * Input strings are unchanged.
   * Ignores symbols as specified in the replaceRegex
   *
   * @param string1 : First string
   * @param string2 : Possible anagram of the first string
   * @return : true if string1 and string2 are anagrams of one another
   */
  public boolean isAnagram(String string1, String string2) {

    //Remove desired characters as specified in replaceRegex
    String str1 = string1.replaceAll(replaceRegex, "");
    String str2 = string2.replaceAll(replaceRegex, "");

    //Quick length check. Anagrams must have the same length.
    if (str1.length() != str2.length()) {
      return false;
    }

    //Sort and compare character by character
    char[] chars1 = sortCharsInString(str1);
    char[] chars2 = sortCharsInString(str2);
    for (int i = 0; i < chars1.length; i++) {
      if (chars1[i] != chars2[i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Sorts characters within the word.
   *
   * @param word: the string to sort the characters of
   * @return sorted array of chars
   */
  private char[] sortCharsInString(String word) {
    char[] chars = word.toCharArray();
    Arrays.sort(chars);
    return chars;
  }

  /**
   * Version of isAnagram() that can be made case insensitive
   */
  public boolean isAnagram(String string1, String string2, Boolean isCaseSensitive) {
    if (!isCaseSensitive) {
      return isAnagram(string1.toLowerCase(), string2.toLowerCase());
    } else {
      return isAnagram(string1, string2);
    }
  }

  /**
   * Determines if one sentence is an anagram of another,
   * i.e. each word in the resulting sentence is an anagram of one of the words
   * in the original sentence.
   * The order of letters within each word can be shuffled as well as the order of the words.
   * Splits up sentences on space characters.
   * Input strings are unchanged.
   * Ignores characters as specified in replaceRegex.
   *
   * @param sentence1
   * @param sentence2, which we want to determine whether it is an anagram of sentence1.
   * @return true if sentence2 is a sentence anagram of sentence1.
   */
  public boolean isAnagramSentence(String sentence1, String sentence2) {

    String[] words1 = preProcessSentence(sentence1);
    String[] words2 = preProcessSentence(sentence2);

    //Compare word by word if each word in sentence2 is an anagram of the word in sentence1
    for (int i = 0; i < words1.length; i++) {
      if (!isAnagram(words1[i], words2[i])) {
        return false;
      }
    }
    return true;
  }

  /**
   * Helper function for isAnagramSentence().
   * Remove desired characters as specified in replaceRegex;
   * splits sentence into words;
   * sorts characters within each word;
   * finally sorts the entire word array.
   *
   * @param sentence to preprocess
   * @return preprocessed array of strings,
   * which are the words in the sentence ordered internally then externally
   */
  private String[] preProcessSentence(String sentence) {
    //Remove desired characters as specified in replaceRegex
    String string = sentence.replaceAll(replaceRegex, "");

    //Split sentences on spaces into words
    String[] words = string.split("\\s+");

    //Sort characters within each word
    for (int i = 0; i < words.length; i++) {
      char[] chars = sortCharsInString(words[i]);
      words[i] = new String(chars);
    }

    //Sort the entire word array
    Arrays.sort(words);

    return words;
  }
}
