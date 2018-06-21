package com.shaya;

import java.util.HashSet;
import java.util.Set;

public class WordSearcher
{

    /**
     * Given a grid of letters and a dictionary, find all the words from the
     * dictionary that can be formed in the grid.
     * The algorithm is case-sensitive ("Cat" != "CAT")
     *
     * The rules for forming a word:
     * ● You can start at any position.
     * ● You can move to one of the 8 adjacent cells (horizontally/vertically/diagonally).
     * ● You can't visit the same cell twice in the same word.
     *
     *
     * The algorithm iterate throw the grid cells to search for words starts with the
     * char withing each cell.
     *
     * @param dictionary - a dictionary which contains the word and the prefixes of the words
     * @param charsGrid -  grid of chars.
     * @return set of all the words found in the grid that are also found in the dictionary.
     */
     public Set<String> allWordsInGrid(Dictionary dictionary, char[][] charsGrid)
    {
        HashSet<String> foundWords = new HashSet<>();
        for(int x = 0 ; x < charsGrid.length ; x++)
        {
            for(int y = 0 ; y < charsGrid[0].length ; y++)
            {
                allWordsInGrid(dictionary,charsGrid ,x,y, "", foundWords);
            }
        }
        return foundWords;
    }

    /**
     *  This method will add prefix+char[x][y] to set of found words if it is in the dictionary.
     *  Than search recursively all around the 8 adjacent cells to current location for another
     *  char to add to the prefix+char[x][y], to create another candidate word.
     *
     *
     * @param dictionary - a dictionary which contains the word and the prefixes of the words
     * @param charsGrid -  grid of chars.
     * @param x - marks the current X position in the grid
     * @param y - marks the current Y position in the grid
     * @param prefix - current prefix. The method will find words starting with this string.
     * @param foundWords - set of all the words that are already found in the grid.
     */
    private void allWordsInGrid(Dictionary dictionary, char[][] charsGrid, int x, int y, String prefix, HashSet<String> foundWords) {


        //  If out of bound of the grid, or if this path not lead to a word
        // or if char in this location has been used in the current prefix
        if ( x < 0 || x >= charsGrid.length || y < 0 || y >= charsGrid[x].length
             || !dictionary.isPrefix(prefix) || charsGrid[x][y] == '0')
        {
            return;
        }

        // Update the set of found words
        if (dictionary.isWord(prefix)) {
            foundWords.add(prefix);
        }

        // Create new prefix by adding current char
        String currentPrefix = prefix + charsGrid[x][y];

        // Create updated grid with mark in current char location
        // indicated that it has been used in the currentPrefix
        char[][] currentCharGrid = cloneArray(charsGrid);
        currentCharGrid[x][y] = 0;

        // Search for word with the updated prefix and grid horizontally/vertically/diagonally
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                allWordsInGrid(dictionary, currentCharGrid, x + i, y + j, currentPrefix, foundWords);
            }
        }
    }

    /**
     * Clone 2-dim array of chars
     *
     * @param src - the array which will be copied
     * @return a clone of the given array
     */
    private char[][] cloneArray(char[][] src) {
        char[][] target = new char[src.length][src[0].length];
        for (int i = 0; i < src.length; i++)
        {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }
}
