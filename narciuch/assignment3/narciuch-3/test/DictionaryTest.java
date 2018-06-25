import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class DictionaryTest {
  String[] words = {"CAR", "CARD", "CART", "CAT"};
  Dictionary dict = new Dictionary(words);

  @Test
  public void correctlyIdentifiesWord() {
    assert(dict.isWord("CAR"));
    assert(dict.isWord("CAT"));
    assert(dict.isWord("CART"));
    assert(!dict.isWord("NOTWORD"));
  }

  @Test
  public void correctlyIdentifiesPrefix() {
    assert(dict.isPrefix("C"));
    assert(dict.isPrefix("CA"));
    assert(dict.isPrefix("CAR"));
    assert(dict.isPrefix("CART"));
    assert(!dict.isPrefix("Z"));
    assert(!dict.isPrefix("APPLE"));
  }
}
