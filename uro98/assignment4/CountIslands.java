import java.util.Arrays;

public class CountIslands {

  /**
   * Returns the number of islands in a given map.
   * @param rowCount  the number of rows in the map
   * @param colCount  the number of columns in the map
   * @param map       the boolean map where true is land and false is water
   * @return the number of islands in integer
   */
  public static int countIslands(int rowCount, int colCount, boolean[][] map) throws IllegalArgumentException {
    
    if (!isMatchingDimensions(rowCount, colCount, map)) {
      throw new IllegalArgumentException("The map does not have the specified dimensions.");
    }
    
    boolean[][] mapCopy = new boolean[rowCount][colCount];
    for (int i = 0; i < rowCount; i++) {
      mapCopy[i] = Arrays.copyOf(map[i], rowCount);
    }
    
    int count = 0;

    for (int y = 0; y < colCount; y++) {
      for (int x = 0; x < rowCount; x++) {
        if (findIsland(y, x, rowCount, colCount, mapCopy)) {
          count++;
        }
      }
    }

    return count;
  }

  private static boolean isMatchingDimensions(int rowCount, int colCount, boolean[][] map) {
    boolean isMatching = true;
    
    if (rowCount != map.length) {
      isMatching = false;
    }
    
    for (boolean[] row : map) {
      if (row.length != colCount) {
        isMatching = false;
      }
    }
    
    return isMatching;
  }

  private static boolean findIsland(int y, int x, int rowCount, int colCount, boolean[][] map) {

    if (isOutOfBounds(y, x, rowCount, colCount)) {
      return false;
    }

    if (map[y][x] == true) {
      // Disable the land as it has been found
      map[y][x] = false;
      
      // The surrounding land is part of the same island
      visitSurroundingTiles(y, x, rowCount, colCount, map);

      return true;
    }
    
    return false;
  }
  
  private static boolean isOutOfBounds(int y, int x, int rowCount, int colCount) {
    return (y < 0 || y >= rowCount || x < 0 || x >= colCount);
  }

  /**
   * Disables vertically and horizontally adjacent tiles.
   */
  private static void visitSurroundingTiles(int y, int x, int rowCount, int colCount, boolean[][] map) {
    findIsland(y + 1, x, rowCount, colCount, map);
    findIsland(y - 1, x, rowCount, colCount, map);
    findIsland(y, x + 1, rowCount, colCount, map);
    findIsland(y, x - 1, rowCount, colCount, map);
  }
}
