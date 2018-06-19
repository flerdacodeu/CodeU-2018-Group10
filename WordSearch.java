package assignment3;

import java.util.HashSet;
import java.util.Set;

public class WordSearch {

  /**
   * Given a grid of letters and a dictionary, finds all the words from the dictionary that can be
   * formed in the grid
   * 
   * If a word occurs multiple times in a grid, the word is returned only once
   * 
   * @param grid of letters in which to look for words
   * @param dictionary of words
   * 
   * @return the set of all words found in the grid
   */
  public static Set<String> findAllWords(char[][] grid, Dictionary dictionary) {
    HashSet<String> wordsFound = new HashSet<>();

    int rows = grid.length;
    if (rows == 0) {
      return wordsFound;
    }

    int columns = grid[0].length;
    if (columns == 0) {
      return wordsFound;
    }

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        boolean[][] isVisited = new boolean[rows][columns];
        String currentString = "";
        findWords(grid, dictionary, currentString, isVisited, r, c, wordsFound);
      }
    }
    return wordsFound;
  }

  /**
   * Checks recursively all of the adjacent cells in the grid, whether the current string combined
   * with the new letter is a word or a prefix of a word from the dictionary
   * 
   * If it is a prefix, continues to check whether it will form a word from the dictionary
   * 
   * @param grid of letters in which to look for words
   * @param dictionary of words
   * @param currentString is a string of the letters that are already visited
   * @param isVisited is an array of booleans, that contains information whether a cell is visited
   *        or not
   * @param row is the current row
   * @param col is the current column
   * @param wordsFound is a set of words, already found in the grid
   */
  private static void findWords(char[][] grid, Dictionary dictionary, String currentString,
      boolean[][] isVisited, int row, int col, HashSet<String> wordsFound) {
    int rows = grid.length;
    int columns = grid[0].length;
    isVisited[row][col] = true;
    currentString += grid[row][col];

    if (dictionary.isWord(currentString)) {
      wordsFound.add(currentString);
    }

    if (dictionary.isPrefix(currentString)) {
      for (int r = row - 1; r <= row + 1 && r < rows; r++) {
        for (int c = col - 1; c <= col + 1 && c < columns; c++) {
          if (r >= 0 && c >= 0 && !isVisited[r][c]) {
            findWords(grid, dictionary, currentString, isVisited, r, c, wordsFound);
          }
        }
      }
    }
    currentString = currentString.substring(0, currentString.length() - 1);
    isVisited[row][col] = false;
  }
}
