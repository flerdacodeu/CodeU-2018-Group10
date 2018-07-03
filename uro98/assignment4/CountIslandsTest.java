import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountIslandsTest {

  @Test
  public void testCanCount() {
    boolean[][] map = new boolean[][] {
      {false, true, false, true},
      {true, true, false, false},
      {false, false, true, false},
      {false, false, true, false}
    };
    
    int numberOfIslands = CountIslands.countIslands(4, 4, map);
    
    assertEquals(3, numberOfIslands);
  }

  @Test
  public void testEmptyMapReturnsZero() {
    boolean[][] map = new boolean[0][0];
    
    int numberOfIslands = CountIslands.countIslands(0, 0, map);
    
    assertEquals(0, numberOfIslands);
  }
  
  @Test
  public void testAllFalseReturnsZero() {
    boolean[][] map = new boolean[][] {
      {false, false, false, false},
      {false, false, false, false},
      {false, false, false, false},
      {false, false, false, false}
    };
    
    int numberOfIslands = CountIslands.countIslands(4, 4, map);
    
    assertEquals(0, numberOfIslands);
  }
  
  @Test
  public void testAllTrueReturnsOne() {
    boolean[][] map = new boolean[][] {
      {true, true, true, true},
      {true, true, true, true},
      {true, true, true, true},
      {true, true, true, true}
    };
    
    int numberOfIslands = CountIslands.countIslands(4, 4, map);
    
    assertEquals(1, numberOfIslands);
  }
}
