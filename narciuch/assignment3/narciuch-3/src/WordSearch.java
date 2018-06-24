import java.util.*;

public class WordSearch {

  /**
   * Given a grid of letters and a dictionary, find the set of words
   * from the dictionary that can be formed in the grid.
   *
   * <p>If there are multiple instances of a word in the grid,
   * then this word is returned only once in the result.
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
   * @return set of words from the dictionary that can be formed in the grid
   */
  public Set<String> findWords(char[][] grid, Dictionary dict) {
    Set<String> wordsFound = new TreeSet<>();
    if (grid.length == 0) {
      return wordsFound;
    }
    if (grid[0].length == 0) {
      return wordsFound;
    }
    int nrRows = grid.length;
    int nrColumns = grid[0].length;
    for (int row = 0; row < nrRows; row++) {
      for (int column = 0; column < nrColumns; column++) {
        //Process for each letter in the grid

        //Default boolean value is false - i.e. unvisited letter
        VisitedMatrix visited = new VisitedMatrix(nrRows, nrColumns);

        String currentWord = "";

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
   * @param visited    an immutable array with the same dimensions as the grid.
   *                   The boolean entry at row i, column j corresponds to whether we have
   *                   already visited the letter at row i, column j in the grid, and hence
   *                   should not revisit it.
   * @param row        the row of the cell currently being considered as the start point of the possible words
   * @param column     the column of the cell currently being considered as the start point of the possible words
   * @param wordsFound set of words we have already been able to form in the grid
   */
  private void findFurtherWords(char[][] grid, Dictionary dict, String letters,
                                final VisitedMatrix visited, int row, int column, Set<String> wordsFound) {
    int nrRows = grid.length;
    int nrColumns = grid[0].length;
    VisitedMatrix newVisited = visited.visit(row, column);
    String newLetters = letters + grid[row][column];

    //For the case of one letter words
    if (dict.isWord(newLetters)) {
      wordsFound.add(newLetters);
    }

    int[][] neighbours = {{row - 1, column - 1}, {row - 1, column}, {row - 1, column + 1},
      {row, column - 1}, {row, column}, {row, column + 1},
      {row + 1, column - 1}, {row + 1, column}, {row + 1, column + 1},};

    for (int[] cell : neighbours) {
      int newRow = cell[0];
      int newColumn = cell[1];
      if (inBounds(nrRows, nrColumns, newRow, newColumn)
        && !newVisited.isVisited(newRow, newColumn)
        && dict.isPrefix(newLetters + grid[newRow][newColumn])) {

        String newestLetters = newLetters + grid[newRow][newColumn];
        if (dict.isWord(newestLetters)) {
          wordsFound.add(newestLetters);
        }
        findFurtherWords(grid, dict, newLetters, newVisited, newRow, newColumn, wordsFound);

      }
    }
  }


  /**
   * Helper for findFurtherWords.
   *
   * <p>Determines if the position with row 'row' and column 'column' is a valid cell position.
   *
   * @param nrRows total number of rows in the array
   * @param nrColumns total number of columns in the array
   * @param row possible cell row
   * @param column possible cell column
   * @return true if the position with row 'row' and column 'column' is valid, else returns false
   */
  private boolean inBounds(int nrRows, int nrColumns, int row, int column) {
    if ((0 <= row) && (row < nrRows)
      && (0 <= column) && (column < nrColumns)) {
      return true;
    }
    return false;
  }
}
