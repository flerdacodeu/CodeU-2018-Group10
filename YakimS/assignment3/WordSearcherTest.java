package com.shaya;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Assert;
import java.util.HashSet;
import org.junit.Test;


public class WordSearcherTest {

    @Test
    public void allWordsInGrid()
    {
        char[][] charsGrid1 = new char[][]
                {
                        {'A', 'A', 'R'},
                        {'T', 'C', 'D'},
                };

        HashSet<String> dictionaryWordsSet = new HashSet<>();
        dictionaryWordsSet.add("CAR");
        dictionaryWordsSet.add("CARD");
        dictionaryWordsSet.add("CART");
        dictionaryWordsSet.add("CAT");
        HashSet<String> prefixesSet = new HashSet<>();
        prefixesSet.add("CAR");
        prefixesSet.add("CARD");
        prefixesSet.add("CART");
        prefixesSet.add("CAT");
        prefixesSet.add("CA");
        prefixesSet.add("C");
        prefixesSet.add("");

        Dictionary dictionary = new Dictionary(prefixesSet,dictionaryWordsSet);


        WordSearcher wordSearcher = new WordSearcher();
        HashSet<String> wordsFoundInGrid1 = (HashSet<String>) wordSearcher.allWordsInGrid(dictionary,charsGrid1);

        Assert.assertThat(wordsFoundInGrid1,IsCollectionContaining.hasItems("CAR","CARD","CAT"));


        char[][] charsGrid2 = new char[][]
                {
                        {},
                        {},
                };

        HashSet<String> wordsFoundInGrid2 = (HashSet<String>) wordSearcher.allWordsInGrid(dictionary,charsGrid2);
        Assert.assertThat(wordsFoundInGrid2,IsCollectionContaining.hasItems());


        char[][] charsGrid3 = new char[][]
                {
                        {'A', 'A', 'R'},
                        {'T', 'C', 'D'},
                        {'R', 'A'}
                };

        HashSet<String> wordsFoundInGrid3 = (HashSet<String>) wordSearcher.allWordsInGrid(dictionary,charsGrid3);
        Assert.assertThat(wordsFoundInGrid3,IsCollectionContaining.hasItems("CAR","CARD","CAT","CART"));
    }
}