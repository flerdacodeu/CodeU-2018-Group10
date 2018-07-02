import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IslandFinder {

  /**
   * Returns the number of islands in the lands matrix.
   *
   * <p>Two tiles in the matrix are part of the same island if
   * they are both land and are adjacent horizontally or vertically.
   *
   * @param nrRows    number of rows in the lands matrix
   * @param nrColumns number of columns in the lands matrix
   * @param lands     nested boolean array, with true representing land,
   *                  false representing water
   * @return the number of islands in the lands matrix
   */
  public int getIslands(int nrRows, int nrColumns, boolean[][] lands) {
    if (lands.length == 0 || lands[0].length == 0) {
      return 0;
    }
    List<List<Integer>> islands = new ArrayList<>();
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
            int islandIndex = getIslandIndex(islands, getUniquePos(row - 1, column, nrColumns));
            //Add above tile island nr to the list of islands the current tile is a part of
            currentTileIslandNrs.add(islandIndex);
          }
        }
        //Check in left tile
        if (column != 0) {
          if (lands[row][column - 1]) {
            int islandIndex = getIslandIndex(islands, getUniquePos(row, column - 1, nrColumns));
            //Add left tile island nr to the list of islands the current tile is a part of
            currentTileIslandNrs.add(islandIndex);
          }
        }

        //We have the list of islands the tile currently belongs to
        //Now we update the list of islands to include this tile
        int currentTile = getUniquePos(row, column, nrColumns);
        if (currentTileIslandNrs.size() == 0) {
          List<Integer> newIsland = new ArrayList<>();
          newIsland.add(currentTile);
          islands.add(newIsland);
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
   * Merges the inner arrays of islands, at the indices specified by the islandIndexes, into one
   * array that it keeps in the islands array.
   *
   * <p>Returns a new copy of islands after this merge.
   *
   * @param islands       a list of integer lists, where each inner integer list represents an island
   * @param islandIndexes the indices of those inner arrays in 'islands' that are to be merged
   * @return a new copy of islands which has merged the islands as specified and not changed the
   * other islands within the 'islands' nested list
   */
  private List<List<Integer>> mergeIslands(List<List<Integer>> islands, List<Integer> islandIndexes) {
    List<Integer> mergeInto = islands.get(islandIndexes.get(0));
    for (int mergeIslandIndex = 1; mergeIslandIndex < islandIndexes.size(); mergeIslandIndex++) {
      int islandNr = islandIndexes.get(mergeIslandIndex);
      for (int tileNr : islands.get(islandNr)) {
        mergeInto.add(tileNr);
      }
      islands.remove(islandNr);
      islands.add(islandNr, new ArrayList<>());
    }
    return islands.stream().filter(island -> island.size() != 0).collect(Collectors.toList());
  }


  /**
   * We assume each tilePosition is in the islands list at most once.
   *
   * @param islands
   * @param tilePosition
   * @return index of the island containing the tilePosition value if it exists in the list
   * of islands. If the tilePosition is not in the list of islands this returns -1.
   */
  private int getIslandIndex(List<List<Integer>> islands, int tilePosition) {
    int totalIslandNr = islands.size();
    for (int islandNr = 0; islandNr < totalIslandNr; islandNr++) {
      if (islands.get(islandNr).contains(tilePosition)) {
        return islandNr;
      }
    }
    return -1;
  }

  /**
   * Returns a unique integer identifier for each cell in a matrix,
   * which in this case is it's position as a function of its row
   * and column.
   *
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
