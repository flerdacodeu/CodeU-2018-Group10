import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

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
  
  @Test(expected = IllegalArgumentException.class)
  public void testNonMatchingDimensionsThrowsException() {
    boolean[][] map = new boolean[][] {
      {false, true, false, true},
      {true, true, false, false},
      {false, false, true, false},
      {false, false, true, false}
    };
    
    CountIslands.countIslands(2, 3, map);
  }
  
  @Test
  public void testDoesNotModifyMap() {
    boolean[][] map = new boolean[][] {
      {false, true, false, true},
      {true, true, false, false},
      {false, false, true, false},
      {false, false, true, false}
    };
    
    boolean[][] mapCopy = new boolean[][] {
      {false, true, false, true},
      {true, true, false, false},
      {false, false, true, false},
      {false, false, true, false}
    };
    
    CountIslands.countIslands(4, 4, map);
    
    assertArrayEquals(mapCopy, map);
  }
}
