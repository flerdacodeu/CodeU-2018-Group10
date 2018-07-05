package assignment4;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class IslandsCounterTest {
  
  IslandsCounter counter = new IslandsCounter();
  
  @Test
  public void countIslandsExample() {
    
    boolean[][] map = new boolean[][] {
      {false, true, false, true},
      {true, true, false, false},
      {false, false, true, false},
      {false, false, true, false}
    };
    
    assertEquals (3, counter.countIslands(map));
  }
  
  @Test
  public void onlyWaterTiles() {
    
    boolean[][] map = new boolean[][] {
      {false, false, false, false, false},
      {false, false, false, false, false},
      {false, false, false, false, false},
      {false, false, false, false, false}
    };
    
    assertEquals (0, counter.countIslands(map));
  }
  
  @Test
  public void onlyLandTiles() {
    
    boolean[][] map = new boolean[][] {
      {true, true, true},
      {true, true, true},
      {true, true, true},
      {true, true, true},
      {true, true, true}
    };
    
    assertEquals (1, counter.countIslands(map));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void emptyMap() {
    
    boolean[][] map = new boolean[][] {
      {},
      {},
      {},
      {}
    };
    
    counter.countIslands(map);
  }
}
