package assignment4;

public class IslandsCounter {
  
  /**
   * Counts the number of islands in a 2-dimensional map of tiles.
   * Each tile is either land or water.
   * Two tiles belong to the same island if they are both land and are adjacent
   * horizontally or vertically, but not diagonally.
   * 
   * @param map is a 2-dimensional array of booleans,
   * where false means water and true means land
   * 
   * @return the number of islands
   */
  public int countIslands(boolean[][] map) {
    if (map.length == 0 || map[0].length == 0) {
      throw new IllegalArgumentException("Map of tiles is empty.");
    }
    int rows = map.length;
    int columns = map[0].length;
    boolean[][] visited = new boolean[rows][columns];

    int count = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (map[i][j] == true && !visited[i][j]) {
          checkAdjacentTiles(map, i, j, visited);
          ++count;
        }
      }
    }
    return count;
  }

/**
 * Checks whether a given tile is land and if it hasn't been visited before,
 * marks it as visited and then checks its adjacent tiles.
 * 
 * @param map is given map of tiles
 * @param row is current row of tile that is checked
 * @param column is current column of tile that is checked
 * @param visited is array of booleans that contains information whether a tile is visited
 */
  private void checkAdjacentTiles(boolean[][] map, int row, int column, boolean[][] visited) {
    int rows = map.length;
    int columns = map[0].length;
    if (row >= 0 && row < rows && column >= 0 && column < columns && map[row][column] == true
        && !visited[row][column]) {
      visited[row][column] = true;
      checkAdjacentTiles(map, row + 1, column, visited);
      checkAdjacentTiles(map, row - 1, column, visited);
      checkAdjacentTiles(map, row, column + 1, visited);
      checkAdjacentTiles(map, row, column - 1, visited);
    }
  }
}
