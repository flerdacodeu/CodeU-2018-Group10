package assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;

public class WordSearchTest {
    private WordSearch wordSearch;

    @Before
    public void init() {
        List<String> words = Arrays.asList("car", "card", "cart", "cat");
        Dictionary dictionary = new Dictionary(words);
        wordSearch = new WordSearch(dictionary);
    }

    @Test
    public void findWordsTest() {
        char[][] grid = new char[][] {
                {'a', 'a', 'r'},
                {'t', 'c', 'd'}
        };
        Set<String> expectedWords =  new HashSet<>(Arrays.asList("car", "card", "cat"));
        Set<String> actualWords = wordSearch.findWords(grid);
        assertEquals(expectedWords, actualWords);
    }

    @Test
    public void emptyGridTest() {
        char[][] grid = new char[][] {
                {},
                {}
        };
        Set<String> expectedWords =  new HashSet<>();
        Set<String> actualWords = wordSearch.findWords(grid);
        assertEquals(expectedWords, actualWords);
    }
}
