import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagram {

  /**
   * Returns a boolean value true if the two strings are anagrams.
   * @param a             the first string
   * @param b             the second string
   * @param caseSensitive whether the comparison should be case sensitive
   * @return              true or false
   */
  public static boolean wordAnagram(String a, String b, boolean caseSensitive) {
    if (!caseSensitive) {
      a = a.toLowerCase();
      b = b.toLowerCase();
    }

    Map<Character, Integer> hisA = new HashMap<Character, Integer>();
    Map<Character, Integer> hisB = new HashMap<Character, Integer>();

    if (a.length() != b.length()) {
      return false;
    }

    // Fill histograms
    for (int i = 0; i < a.length(); i++) {
      hisA.put(a.charAt(i), hisA.getOrDefault(a.charAt(i), 0) + 1);
      hisB.put(b.charAt(i), hisB.getOrDefault(b.charAt(i), 0) + 1);
    }

    if (hisA.equals(hisB)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns a boolean value true if the two sentences are anagrams
   * (ie each word is an anagram of a word in the other sentence).
   * Words are split by spaces.
   * @param a             the first sentence
   * @param b             the second sentence
   * @param caseSensitive whether the comparison should be case sensitive
   * @return              true or false
   */
  public static boolean sentenceAnagram(String a, String b, boolean caseSensitive) {
    ArrayList<String> listA = new ArrayList<String>(Arrays.asList(a.split(" ")));
    ArrayList<String> listB = new ArrayList<String>(Arrays.asList(b.split(" ")));

    if (a.length() != b.length() || listA.size() != listB.size()) {
      return false;
    }

    // for each word in listA, look for an anagram in listB
    for (int i = 0; i < listA.size(); i++) {
      Boolean anagram = false;
      for (int j = 0; j < listB.size(); j++) {
        if (wordAnagram(listA.get(i), listB.get(j), caseSensitive)) {
          anagram = true;
          listB.remove(j);
          break;
        }
      }
      if (!anagram) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(wordAnagram("listen", "silent", false));
    System.out.println(sentenceAnagram("listen triangle", "silent integral", false));
  }
}
