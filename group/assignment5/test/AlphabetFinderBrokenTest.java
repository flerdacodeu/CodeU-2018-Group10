
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlphabetFinderBrokenTest {

  @Test
  public void testFindExampleAlphabet() {

    String[] dictionary = new String[]{
        "ART", "RAT", "CAT", "CAR"
    };
    
    List<List<Character>> validAlphabets = new ArrayList<List<Character>>();
    validAlphabets.add(Arrays.asList('A', 'T', 'R', 'C'));
    validAlphabets.add(Arrays.asList('T', 'A', 'R', 'C'));
    
    List<Character> alphabet = AlphabetFinderBroken.findAlphabet(dictionary);
    System.out.println(alphabet);
    
    assertTrue(validAlphabets.contains(alphabet));
  }
  
  @Test
  public void testSimple() {
    
    String[] dictionary = new String[]{
        "ART"
    };
    
    List<List<Character>> validAlphabets = new ArrayList<List<Character>>();
    validAlphabets.add(Arrays.asList('A', 'R', 'T'));
    
    List<Character> alphabet = AlphabetFinderBroken.findAlphabet(dictionary);
    
    assertTrue(validAlphabets.contains(alphabet));
  }
  
  @Test
  public void testMore() {
    
    String[] dictionary = new String[]{
        "ART", "RAT"
    };
    
    List<List<Character>> validAlphabets = new ArrayList<List<Character>>();
    validAlphabets.add(Arrays.asList('A', 'R', 'T'));
    
    List<Character> alphabet = AlphabetFinderBroken.findAlphabet(dictionary);
    
    assertTrue(validAlphabets.contains(alphabet));
  }
  
  @Test
  public void testEvenMore() {
    
    String[] dictionary = new String[]{
        "ART", "RAT", "CAT"
    };
    
    List<List<Character>> validAlphabets = new ArrayList<List<Character>>();
    validAlphabets.add(Arrays.asList('A', 'R', 'C', 'T'));
    
    List<Character> alphabet = AlphabetFinderBroken.findAlphabet(dictionary);
    
    assertTrue(validAlphabets.contains(alphabet));
  }
}
