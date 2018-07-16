package assignment5;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AlphabetDiscovererTest {
    private AlphabetDiscoverer alphabetDiscoverer = new AlphabetDiscoverer();

    @Test
    public void testConsistentDictionary() {
        List<String> dictionary = Arrays.asList(
                "ART", "RAT", "CAT", "CAR"
        );
        List<List<Character>> possibleExpectedOutput = new ArrayList<>();
        possibleExpectedOutput.add(Arrays.asList('A', 'T', 'R', 'C'));
        possibleExpectedOutput.add(Arrays.asList('T', 'A', 'R', 'C'));

        assertTrue(possibleExpectedOutput.contains(alphabetDiscoverer.getAlphabet(dictionary)));
    }

    @Test
    public void testAllAlphabetsInConsistentDictionary() {
        List<String> dictionary = Arrays.asList(
                "ART", "RAT", "CAT", "CAR"
        );
        List<List<Character>> expectedResult = new ArrayList<>();
        expectedResult.add(Arrays.asList('A', 'T', 'R', 'C'));
        expectedResult.add(Arrays.asList('T', 'A', 'R', 'C'));
        List<List<Character>> actualResult = alphabetDiscoverer.getAllPossibleAlphabets(dictionary);
        assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInconsistentDictionary() {
        List<String> dictionary = Arrays.asList(
                "AB", "BC", "BA", "CC"
        );
        alphabetDiscoverer.getAlphabet(dictionary);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyDictionary() {
        alphabetDiscoverer.getAlphabet(new ArrayList<>());
    }
}
