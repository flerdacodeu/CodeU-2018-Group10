import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AlphabetFinderTest<T> {

  @Test
  public void basicAlphabetFinderTest() {
    AlphabetFinder finder = new AlphabetFinder();
    finder.addWord(Arrays.asList('A', 'R', 'T'));
    finder.addWord(Arrays.asList('R', 'A', 'T'));
    finder.addWord(Arrays.asList('C', 'A', 'T'));
    finder.addWord(Arrays.asList('C', 'A', 'R'));
    List<T> resultAlphabet = finder.getAlphabet();
    for (T letter : resultAlphabet) {
      System.out.print(letter + " ");
    }
    System.out.println();
    assertTrue(resultAlphabet.containsAll(Arrays.asList('A', 'R', 'T', 'C')));
    assertTrue(resultAlphabet.indexOf('A') < resultAlphabet.indexOf('R'));
    assertTrue(resultAlphabet.indexOf('R') < resultAlphabet.indexOf('C'));
    assertTrue(resultAlphabet.indexOf('T') < resultAlphabet.indexOf('R'));
  }
}
