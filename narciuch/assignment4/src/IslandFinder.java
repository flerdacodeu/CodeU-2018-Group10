import java.util.*;

public class IslandFinder {

  /**
   * Returns the number of islands in the lands matrix.
   * <p>
   * <p>Two tiles in the matrix are part of the same island if
   * they are both land and are adjacent horizontally or vertically.
   *
   * @param lands nested boolean array, with true representing land,
   *              false representing water
   * @return the number of islands in the lands matrix
   */
  public int getIslands(boolean[][] lands) {
    if (lands.length == 0 || lands[0].length == 0) {
      return 0;
    }
    int nrRows = lands.length;
    int nrColumns = lands[0].length;
    Map<Integer, Set<Integer>> islands = new HashMap<>();
    for (int row = 0; row < nrRows; row++) {
      for (int column = 0; column < nrColumns; column++) {
        if (!lands[row][column]) {
          //Water tile, so we move to the next tile
          continue;
        }
        List<Integer> currentTileIslandNrs = new ArrayList<>();
        //Check above tile
        if (row != 0) {
          if (lands[row - 1][column]) {
            int islandIndex = getIslandKey(islands, getUniquePos(row - 1, column, nrColumns));
            //Add above tile island nr to the list of islands the current tile is a part of
            currentTileIslandNrs.add(islandIndex);
          }
        }
        //Check in left tile
        if (column != 0) {
          if (lands[row][column - 1]) {
            int islandIndex = getIslandKey(islands, getUniquePos(row, column - 1, nrColumns));
            //Add left tile island nr to the list of islands the current tile is a part of
            currentTileIslandNrs.add(islandIndex);
          }
        }

        //We have the list of islands the tile currently belongs to
        //Now we update the list of islands to include this tile
        int currentTile = getUniquePos(row, column, nrColumns);
        if (currentTileIslandNrs.size() == 0) {
          Set<Integer> newIsland = new HashSet<>();
          newIsland.add(currentTile);
          islands.put(islands.size(), newIsland);
        } else if (currentTileIslandNrs.size() == 1) {
          islands.get(currentTileIslandNrs.get(0)).add(currentTile);
        } else {//Tile is a member of 2 islands
          //Add tile arbitrarily to 1st island
          islands.get(currentTileIslandNrs.get(0)).add(currentTile);
          if (currentTileIslandNrs.get(0) != currentTileIslandNrs.get(1)) {
            //Merge the 2 different islands into 1
            islands = mergeIslands(islands, currentTileIslandNrs);
          }
        }
      }
    }

    return islands.size();
  }

  /**
   * Merges the values of islands, i.e the integer sets, that have keys specified by the islandKeys,
   * into one set that it keeps in islands, i.e. merges multiple islands into one.
   * <p>
   * <p>Returns a new copy of islands after this merge.
   *
   * @param islands    a mapping from a unique integer key to a set of integers,
   *                   where each integer set represents an island
   * @param islandKeys the keys of those sets in 'islands' that are to be merged
   * @return a new copy of islands which has merged the islands as specified and not changed the
   * other islands within 'islands'
   */
  private Map<Integer, Set<Integer>> mergeIslands(Map<Integer, Set<Integer>> islands, List<Integer> islandKeys) {
    Set<Integer> mergeInto = islands.get(islandKeys.get(0));
    for (int mergeIslandIndex = 1; mergeIslandIndex < islandKeys.size(); mergeIslandIndex++) {
      int islandKey = islandKeys.get(mergeIslandIndex);
      mergeInto.addAll(islands.get(islandKey));
      islands.remove(islandKey);
    }
    return islands;
  }


  /**
   * We assume each tilePosition is in islands at most once.
   *
   * @param islands
   * @param tilePosition
   * @return key of the island containing the tilePosition value if it exists in 'islands'.
   * If the tilePosition is not in 'islands' then -1 is returned.
   */
  private int getIslandKey(Map<Integer, Set<Integer>> islands, int tilePosition) {
    for (int key : islands.keySet()) {
      if (islands.get(key).contains(tilePosition)) {
        return key;
      }
    }
    return -1;
  }

  /**
   * Returns a unique integer identifier for each cell in a matrix,
   * which in this case is it's position as a function of its row
   * and column.
   * <p>
   * <p>Starting from 0 for the top left cell and moving across the rows
   * then down the columns with each subsequent cell being the successor
   * integer of the previous one.
   *
   * @param row       the row of the cell you want to get the unique identifier of
   * @param column    the column of the cell to get the unique identifier of
   * @param nrColumns total number of columns within the matrix
   * @return an integer being a unique identifier of the cell at the specified
   * row and column
   */
  private int getUniquePos(int row, int column, int nrColumns) {
    return nrColumns * row + column;
  }
}
