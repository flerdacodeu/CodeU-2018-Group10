/**
 * This class implements an immutable structure that keeps track
 * of which cells in a matrix have been visited.
 */
public class VisitedMatrix {
  private boolean[][] visited;

  public VisitedMatrix(int nrRows, int nrColumns) {
    this.visited = new boolean[nrRows][nrColumns];
  }

  private VisitedMatrix(boolean[][] newVisited) {
    this.visited = newVisited;
  }

  /**
   * Records that the cell at row 'row' and column 'column'
   * has been visited.
   *
   * @param row    the row of the cell to be visited
   * @param column the column of the cell to be visited
   * @return a new instance of VisitedMatrix that retains the
   * information about all previously visited cells as well as
   * the newly visited one.
   */
  public VisitedMatrix visit(int row, int column) {
    boolean[][] newVisited = cloneArray(visited);
    newVisited[row][column] = true;
    return new VisitedMatrix(newVisited);
  }

  /**
   * Determines whether the cell at row 'row' and column 'column'
   * has been visited
   *
   * @param row
   * @param column
   * @return true if the cell has been visited, false otherwise
   */
  public boolean isVisited(int row, int column) {
    return visited[row][column];
  }

  /**
   * Creates and returns an exact copy of the provided table.
   *
   * @param table the array to be copied
   * @return an exact copy of the table
   */
  private boolean[][] cloneArray(boolean[][] table) {
    boolean[][] newTable = new boolean[table.length][table[0].length];
    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[0].length; j++) {
        newTable[i][j] = table[i][j];
      }
    }
    return newTable;
  }
}
