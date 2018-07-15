package assignment5;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnknownAlphabetTest {
    private UnknownAlphabet unknownAlphabet = new UnknownAlphabet();

    @Test
    public void testConsistentDictionary() {
        List<String> dictionary = Arrays.asList(
                "ART", "RAT", "CAT", "CAR"
        );
        List<List<Character>> possibleExpectedOutput = new ArrayList<>();
        possibleExpectedOutput.add(Arrays.asList('A', 'T', 'R', 'C'));
        possibleExpectedOutput.add(Arrays.asList('T', 'A', 'R', 'C'));

        assertTrue(possibleExpectedOutput.contains(unknownAlphabet.getAlphabet(dictionary)));
    }

    @Test
    public void testAllAlphabetsInConsistentDictionary() {
        List<String> dictionary = Arrays.asList(
                "ART", "RAT", "CAT", "CAR"
        );
        List<List<Character>> expectedResult = new ArrayList<>();
        expectedResult.add(Arrays.asList('A', 'T', 'R', 'C'));
        expectedResult.add(Arrays.asList('T', 'A', 'R', 'C'));
        List<List<Character>> actualResult = unknownAlphabet.getAllPossibleAlphabets(dictionary);
        assertEquals(expectedResult, actualResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInconsistentDictionary() {
        List<String> dictionary = Arrays.asList(
                "AB", "BC", "BA", "CC"
        );
        unknownAlphabet.getAlphabet(dictionary);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyDictionary() {
        unknownAlphabet.getAlphabet(new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDictionaryWithNoChars() {
        unknownAlphabet.getAllPossibleAlphabets(Arrays.asList("", "", ""));
    }
}
