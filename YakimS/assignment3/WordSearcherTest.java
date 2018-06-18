package com.shaya;

import java.util.HashSet;


public class WordSearcherTest {

    @org.junit.Test
    public void allWordsInGrid()
    {
        char[][] charsGrid = new char[][]
                {
                        {'A', 'A', 'R'},
                        {'T', 'C', 'D'},
                };

        HashSet wordsSet = new HashSet();
        wordsSet.add("CAR");
        wordsSet.add("CARD");
        wordsSet.add("CART");
        wordsSet.add("CAT");
        HashSet prefixesSet = new HashSet();
        prefixesSet.add("CAR");
        prefixesSet.add("CARD");
        prefixesSet.add("CART");
        prefixesSet.add("CAT");
        prefixesSet.add("CA");
        prefixesSet.add("C");
        prefixesSet.add("");
        Dictionary dictionary = new Dictionary(prefixesSet,wordsSet);

        WordSearcher wordSearcher = new WordSearcher();

        System.out.print(wordSearcher.allWordsInGrid(dictionary,charsGrid));
    }
}