import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnagramTest {

  Anagram anagram = new Anagram();

  @Test
  public void correctlyIdentifiesWordAnagramCaseSensitive() {
    assertTrue(anagram.isAnagram("", ""));
    assertTrue(anagram.isAnagram("listen", "silent"));
    assertTrue(anagram.isAnagram("triangle", "integral"));
    assertFalse(anagram.isAnagram("apple", "pabble"));
  }

  @Test
  public void correctlyIdentifiesWordAnagramCaseInsensitive() {
    assertTrue(anagram.isAnagram("lIsTen", "sIlenT"));
    assertFalse(anagram.isAnagram("triangle", "intEgraL"));
    assertTrue(anagram.isAnagram("triangle", "intEgraL", false));
  }

  @Test
  public void correctlyIdentifiesSentenceAnagram() {
    assertTrue(anagram.isAnagramSentence("hello world", "ldwor llohe"));
    assertTrue(anagram.isAnagramSentence("abc def", "cba fed"));
    assertTrue(anagram.isAnagramSentence("abc def", "def abc"));
    assertTrue(anagram.isAnagramSentence("b a c", "a b c"));
    assertFalse(anagram.isAnagramSentence("apple pie", "pabble eye"));
  }

  @Test
  public void doesNotChangeInputWords() {
    String s1 = "listen";
    String s2 = "silent";
    anagram.isAnagram("listen", "silent");
    assertTrue(s1.equals("listen"));
    assertTrue(s2.equals("silent"));

    String sentence1 = "hello world";
    String sentence2 = "ldwor llohe";
    anagram.isAnagramSentence(sentence1, sentence2);
    assertTrue(sentence1.equals("hello world"));
    assertTrue(sentence2.equals("ldwor llohe"));
  }

  @Test
  public void ignoresPunctuation() {
    //For words
    assertTrue(anagram.isAnagramSentence("hello, wor\nld", "l!dwo\tr ll.ohe"));
    assertFalse(anagram.isAnagram("ap%p.,le", "p\"0&abble"));

    //For sentences
    assertTrue(anagram.isAnagramSentence("b  ,  , a c", "a £////b 2 c"));
    assertFalse(anagram.isAnagramSentence("ap999ple pie ... #", "pabb23le eye;"));
  }

  @Test
  public void correctlyIdentifiesStringsOfDifferentLengthsAsNotAnagrams() {
    //For words
    assertFalse(anagram.isAnagram("", "abc"));
    assertFalse(anagram.isAnagram("ab", "abcd"));
    //For sentences
    assertFalse(anagram.isAnagramSentence("", "l"));
    assertFalse(anagram.isAnagramSentence("", "l abc"));
    assertFalse(anagram.isAnagramSentence("h", "l abc"));
    assertFalse(anagram.isAnagramSentence("h a", "l abc"));
  }

}
