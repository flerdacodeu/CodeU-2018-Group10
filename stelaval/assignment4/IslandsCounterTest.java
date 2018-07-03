package assignment4;

import static org.junit.Assert.*;
import org.junit.Test;

public class IslandsCounterTest {
  
  @Test
  public void countIslandsExample() {
    IslandsCounter counter = new IslandsCounter();
    
    boolean[][] map = new boolean[][] {
      {false, true, false, true},
      {true, true, false, false},
      {false, false, true, false},
      {false, false, true, false}
    };
    
    assertEquals (counter.countIslands(map), 3);
  }
  
  @Test
  public void onlyWaterTiles() {
    IslandsCounter counter = new IslandsCounter();
    
    boolean[][] map = new boolean[][] {
      {false, false, false, false, false},
      {false, false, false, false, false},
      {false, false, false, false, false},
      {false, false, false, false, false}
    };
    
    assertEquals (counter.countIslands(map), 0);
  }
  
  @Test
  public void onlyLandTiles() {
    IslandsCounter counter = new IslandsCounter();
    
    boolean[][] map = new boolean[][] {
      {true, true, true},
      {true, true, true},
      {true, true, true},
      {true, true, true},
      {true, true, true}
    };
    
    assertEquals (counter.countIslands(map), 1);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void emptyMap() {
    IslandsCounter counter = new IslandsCounter();
    
    boolean[][] map = new boolean[][] {
      {},
      {},
      {},
      {}
    };
    
    counter.countIslands(map);
  }
}
