package assignment3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents an object with the
 * help of which you can efficiently check which
 * of the words from a given list can be found in a given grid of letters
 */
public class WordSearch {
    private Dictionary dictionary;

    /**
     * @param words is a list of words which will
     * be used to form a dictionary
     */
    public WordSearch(List<String> words) {
        dictionary = new Dictionary(words);
    }

    public WordSearch(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * This method is for searching for all the words in a dictionary
     * that can be formed using a grid of letters.
     * The rules for searching in a grid are the following :
     * A search for a word starts at any position in a grid.
     * It is allowed to move to one of the 8 adjacent cells (horizontally |
     * vertically | diagonally ).
     * During the search for one word one cell cannot be visited more than once.
     * @param board is a grid of characters which can be used to form
     * a word.
     * It is assumed that board contains only lower case characters.
     * @return the list of words from a dictionary which can be formed
     * in a grid of words
     */
    public Set<String> findWords(char[][] board) {
        Set<String> words = new HashSet<>();
        StringBuilder prefix;
        boolean[][] isVisited;
        for (int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                prefix = new StringBuilder();
                isVisited = new boolean[board.length][board[0].length];
                dfs(board, i, j, prefix, words, isVisited);
            }
        }
        return words;
    }

    /**
     * This method is a Depth First Search for words in a grid of letters.
     * @param board is a given grid of letters
     * @param i is an index of row of the grid
     * @param j is an index of column in the grid
     * @param prefix is a current prefix that was made during the search
     * @param words is a set of words found
     * @param isVisited is an array to mark visited cells in grid
     * isVisited[i][j] is true if we have already visited board[i][j]
     * during the search
     */
    private void dfs(char[][] board, int i, int j, StringBuilder prefix, Set<String> words, boolean[][] isVisited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || isVisited[i][j]) {
            return;
        }
        prefix.append(board[i][j]);
        if(!dictionary.isPrefix(prefix.toString())) {
            prefix.deleteCharAt(prefix.length() - 1);
            return;
        }
        if(dictionary.isWord(prefix.toString())) {
            words.add(prefix.toString());
        }
        isVisited[i][j] = true;
        for (int k = i - 1; k < i + 2; k++) {
            for(int l = j - 1; l < j + 2; l++) {
                if(k != i || l != j) {
                    dfs(board, k, l, prefix, words, isVisited);
                }
            }
        }
        isVisited[i][j] = false;
        prefix.deleteCharAt(prefix.length() - 1);
    }
}
