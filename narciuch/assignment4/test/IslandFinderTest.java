import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class IslandFinderTest {
  IslandFinder islandFinder = new IslandFinder();

  @Test
  public void findsMultipleIslands() {
    boolean[][] land = {{false, true, false, true}, {true, true, false, false},
      {false, false, true, false}, {false, false, true, false}};
    assertEquals(islandFinder.getIslands(land), 3);
  }

  @Test
  public void maintainsOneIslandWhenAboveAndLeftCellArePartOfSameIsland() {
    boolean[][] land = {{false, true, true, false}, {true, true, true, false}, {false, false, false, false}};
    assertEquals(islandFinder.getIslands(land), 1);
  }

  @Test
  public void handlesEmptyLandMatrix() {
    boolean[][] land = {};
    assertEquals(islandFinder.getIslands(land), 0);
  }

  @Test
  public void handlesOneElementLandMatrix() {
    boolean[][] land = {{}};
    assertEquals(islandFinder.getIslands(land), 0);
  }

}
