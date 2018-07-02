import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class IslandFinderTest {
  IslandFinder islandFinder = new IslandFinder();

  @Test
  public void findsMultipleIslands() {
    boolean[][] land = {{false, true, false, true}, {true, true, false, false},
      {false, false, true, false}, {false, false, true, false}};
    assertTrue(islandFinder.getIslands(4, 4, land) == 3);
  }

  @Test
  public void maintainsOneIslandWhenAboveAndLeftCellArePartOfSameIsland() {
    boolean[][] land = {{false, true, true, false}, {true, true, true, false}, {false, false, false, false}};
    assertTrue(islandFinder.getIslands(3, 4, land) == 1);
  }

  @Test
  public void handlesEmptyLandMatrix() {
    boolean[][] land = {};
    assertTrue(islandFinder.getIslands(0, 0, land) == 0);
  }

  @Test
  public void handlesOneElementLandMatrix() {
    boolean[][] land = {{}};
    assertTrue(islandFinder.getIslands(1, 0, land) == 0);
  }

}
