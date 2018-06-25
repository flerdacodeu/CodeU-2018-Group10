package assignment3;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DictionaryTest {
    private Dictionary dictionary;
    private List<String> words;
    private List<String> prefixes;

    @Before
    public void init() {
        words = Arrays.asList("car", "card", "cart", "cat");
        prefixes = Arrays.asList("c", "ca", "car", "card", "cart", "cat");
        dictionary = new Dictionary(words);
    }
    @Test
    public void buildDictionaryTest() {
        Set<String> wordsFromDictionary = dictionary.getWords();
        Set<String> prefixesFromDictionary = dictionary.getPrefixes();
        assertEquals(wordsFromDictionary, new HashSet<>(words));
        assertEquals(prefixesFromDictionary, new HashSet<>(prefixes));
    }

    @Test
    public void isWordTest() {
        assertTrue(dictionary.isWord("car"));
        assertFalse(dictionary.isWord("fdkl"));
        assertFalse(dictionary.isWord("ca"));
    }

    @Test
    public void isPrefixTest() {
        assertTrue(dictionary.isPrefix("c"));
        assertTrue(dictionary.isPrefix("ca"));
        assertTrue(dictionary.isPrefix("cat"));
        assertFalse(dictionary.isPrefix("fdkl"));
    }
}
