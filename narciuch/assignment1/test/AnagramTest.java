import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnagramTest {

  Anagram anagram = new Anagram();

  @Test
  public void correctlyIdentifiesWordAnagramCaseSensitive() {
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
  public void doesNotChangeInputWords() {
    String s1 = "listen";
    String s2 = "silent";
    anagram.isAnagram("listen", "silent");
    assertTrue(s1.equals("listen"));
    assertTrue(s2.equals("silent"));
  }

  @Test
  public void correctlyIdentifiesSentenceAnagram() {
    assertTrue(anagram.isAnagramSentence("hello world", "ldwor llohe"));
    assertTrue(anagram.isAnagramSentence("b a c", "a b c"));
    assertFalse(anagram.isAnagramSentence("apple pie", "pabble eye"));
  }

  @Test
  public void ignoresPunctuation() {
    //in words
    assertTrue(anagram.isAnagramSentence("hello, world", "l!dwor ll.ohe"));
    assertFalse(anagram.isAnagram("ap%p.,le", "p\"0&abble"));
    //in sentences
    assertTrue(anagram.isAnagramSentence("b  ,  , a c", "a £////b 2 c"));
    assertFalse(anagram.isAnagramSentence("ap999ple pie ... #", "pabb23le eye;"));
  }
}
