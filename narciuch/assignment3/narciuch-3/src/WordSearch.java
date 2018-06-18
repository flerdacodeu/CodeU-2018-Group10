import java.util.ArrayList;
import java.util.List;

public class WordSearch {

  /**
   * Given a grid of letters and a dictionary, find all the words
   * from the dictionary that can be formed in the grid.
   *
   * <p>If there are multiple instances of a word in the grid,
   * then this word is returned multiple times in the result.
   *
   * <p>When forming a word, we assume:
   * <ul>
   * <li>Words can start at any position within the grid.</li>
   * <li>We can move to one of the 8 adjacent cells (horizontally/vertically/diagonally)</li>
   * <li>We cannot visit the same cell more than once in the same word.</li>
   * </ul>
   *
   * @param grid of letters in which to look for words from the dictionary
   * @param dict the dictionary of words to look for
   * @return list of words from the dictionary that can be formed in the grid
   */
  public List<String> findWords(char[][] grid, Dictionary dict) {
    List<String> wordsFound = new ArrayList<>();
    if (grid.length == 0) {
      return wordsFound;
    }
    int nr_rows = grid.length;
    int nr_columns = grid[0].length;
    for (int row = 0; row < nr_rows; row++) {
      for (int column = 0; column < nr_columns; column++) {
        //Process for each letter in the grid

        //Default boolean value is false - i.e. unvisited letter
        boolean[][] visited = new boolean[nr_rows][nr_columns];

        List<Character> currentWord = new ArrayList<>();

        findFurtherWords(grid, dict, currentWord, visited, row, column, wordsFound);
      }
    }
    return wordsFound;
  }

  /**
   * Helper for findWords.
   *
   * <p>Starting at the letter at row 'row' and column 'column' within the grid, this method
   * recursively checks all possible adjacent* letters in the grid, checking if the already
   * visited letters on the current path together with the new adjacent letter are a prefix of
   * a word in the dictionary.
   *
   * <p>If they are a prefix, they could form a dictionary word, so we check this and if they do
   * we add this word to the list of words found.
   *
   * <p>If they are a prefix, we must recursively call this method to check if we can form
   * another prefix or word by adding another adjacent letter to the existing letters.
   *
   * <p>*By adjacent letters, we mean adjacent horizontally, vertically or diagonally from the
   * current letter we are considering.
   *
   * <p>When forming words, we assume we cannot visit the same cell more than once in the same word.
   *
   * @param grid       the grid of letters we recursively check in
   * @param dict       the dictionary of words we are looking for
   * @param letters    the letters we have visited on our current path through the grid
   * @param visited    an array with the same dimensions as the grid.
   *                   The boolean entry at row i, column j corresponds to whether we have
   *                   already visited the letter at row i, column j in the grid, and hence
   *                   should not revisit it.
   * @param row        the row of the cell currently being considered as the start point of the possible words
   * @param column     the column of the cell currently being considered as the start point of the possible words
   * @param wordsFound list of words we have already been able to form in the grid
   */
  private void findFurtherWords(char[][] grid, Dictionary dict, List<Character> letters,
                                boolean[][] visited, int row, int column, List<String> wordsFound) {
    int nr_rows = grid.length;
    int nr_columns = grid[0].length;
    visited[row][column] = true;
    letters.add(grid[row][column]);

    //For the case of one letter words
    if (dict.isWord(charListToString(letters))) {
      wordsFound.add(charListToString(letters));
    }

    int[][] neighbours = {{row - 1, column - 1}, {row - 1, column}, {row - 1, column + 1},
      {row, column - 1}, {row, column}, {row, column + 1},
      {row + 1, column - 1}, {row + 1, column}, {row + 1, column + 1},};

    for (int[] cell : neighbours) {
      int new_row = cell[0];
      int new_column = cell[1];
      if (inBounds(nr_rows, nr_columns, new_row, new_column)
        && !visited[new_row][new_column]
        && dict.isPrefix(charListToString(letters) + grid[new_row][new_column])) {

        String newLetters = charListToString(letters) + grid[new_row][new_column];
        if (dict.isWord(newLetters)) {
          wordsFound.add(newLetters);
        }
        findFurtherWords(grid, dict, letters, visited, new_row, new_column, wordsFound);

      }
    }
    letters.remove(letters.size() - 1);
  }


  /**
   * Helper for findFurtherWords.
   *
   * <p>Determines if the position with row 'row' and column 'column' is a valid cell position.
   *
   * @param nr_rows total number of rows in the array
   * @param nr_columns total number of columns in the array
   * @param row possible cell row
   * @param column possible cell column
   * @return true if the position with row 'row' and column 'column' is valid, else returns false
   */
  private boolean inBounds(int nr_rows, int nr_columns, int row, int column) {
    if ((0 <= row) && (row < nr_rows)
      && (0 <= column) && (column < nr_columns)) {
      return true;
    }
    return false;
  }

  /**
   * Helper for findFurtherWords.
   *
   * <p>Converts a list of characters into a string
   *
   * @param list the list of characters to convert into a string
   * @return the list of characters concatenated into a string
   */
  private String charListToString(List<Character> list) {
    StringBuilder sb = new StringBuilder(list.size());
    for (Character elem : list) {
      sb.append(elem);
    }
    return sb.toString();
  }
}
