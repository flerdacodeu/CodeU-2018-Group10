/**
 * Represents a grid of letters.
 */
public class Grid {
  private final int height;
  private final int width;
  private char[][] grid;

  /**
   * Creates a grid where each row and column has the same dimension.
   * @param height  an integer
   * @param width   an integer
   */
  public Grid(int height, int width) {
    this.height = height;
    this.width = width;
    this.grid = new char[height][width];
  }
  
  public int getHeight() {
    return height;
  }
  
  public int getWidth() {
    return width;
  }
  
  /**
   * Get the element at the specified position in the grid.
   * @param x integer
   * @param y integer
   * @return String
   */
  public char getLetter(int x, int y) {
    return grid[x][y];
  }
  
  /**
   * Populate the grid with the given 2D array if the array has no jagged edges.
   * @param grid a 2D char array
   * @throws IllegalArgumentException
   */
  public void populate(char[][] grid) throws IllegalArgumentException {
    boolean rectangular = true;
    
    for (char[] row : grid) {
      if (row.length != width) {
        rectangular = false;
      }
    }
    
    if (rectangular) {
      this.grid = grid;
    } else {
      throw new IllegalArgumentException("All rows of the grid must have the same dimension.");
    }
  }
}
