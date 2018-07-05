package com.shaya.CodeUAss4Package;

import java.util.Arrays;

public class IslandsCounter {

    /**
     *  Counts the number of islands in a map.
     *  Two tiles belong to the same island if they are both land and are
     *  adjacent horizontally or vertically, but not diagonally.
     *
     * @param map - 2-dimensional array,
     *            which each tile of it is either land(=true) or water(=false).
     * @return number of "islands" in the map.
     */
    public int countIslands(boolean[][] map) {
        // make sure the given array is 2-dim
        if(map.length==0){
            return 0;
        }

        int[][] islandNumMap = new int[map.length][map[0].length];
        for (int i = 0; i < islandNumMap.length; i++) {
            // "-1" will be an indication that the corresponding cell in the boolean map have not been investigated
            Arrays.fill(islandNumMap[i], -1);
        }
        int islandCounter = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] && islandNumMap[i][j] == -1) {
                    investigateNearCellsOnMap(map, islandNumMap, i, j, ++islandCounter);
                }
            }
        }
        return islandCounter;
    }

    private void investigateNearCellsOnMap(boolean[][] boolMap, int[][] islandNumMap, int i, int j, int islandNum) {

        // If indexes out of bounds
        if(i >= boolMap.length || j >= boolMap[0].length || j < 0 || i < 0) {
            return;
        }

        // If this part of the boolean map have been searched
        if( islandNumMap[i][j] != -1 ) {
            return;
        }

        if( !boolMap[i][j]) {
            islandNumMap[i][j] = 0; // Indicates that this part of the map is water
        } else {
            // Indicates that this part of the map is part of the island with the number "islandNum"
            islandNumMap[i][j] = islandNum;

            // Investigate horizontal and vertical cells
            investigateNearCellsOnMap(boolMap, islandNumMap, i + 1, j, islandNum);
            investigateNearCellsOnMap(boolMap, islandNumMap, i - 1, j, islandNum);
            investigateNearCellsOnMap(boolMap, islandNumMap, i, j + 1, islandNum);
            investigateNearCellsOnMap(boolMap, islandNumMap, i, j - 1, islandNum);
        }
    }
}
