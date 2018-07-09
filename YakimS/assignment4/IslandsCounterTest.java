package com.shaya.CodeUAss4Package;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IslandsCounterTest {

    @Test
    public void countIslandInExampleGrid() {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                        { false , true, false, true },
                        { true , true, false, false },
                        { false , false, true, false },
                        { false , false, true, false },
                };

        assertEquals(3, islandsCounter.countIslands(boolGrid));
    }

    @Test
    public void countIslandInAllWaterGrid() {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                        { false , false, false, false },
                        { false , false, false, false },
                        { false , false, false, false },
                        { false , false, false, false },
                };

        assertEquals(0 , islandsCounter.countIslands(boolGrid));
    }

    @Test
    public void countIslandInEmptyGrid() {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                        { },
                        { },
                        { },
                        { },
                };

        assertEquals( 0, islandsCounter.countIslands(boolGrid));
    }

    @Test
    public void countIslandInAllLandGrid() {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                        { true , true, true, true },
                        { true , true, true, true },
                        { true , true, true, true },
                        { true , true, true, true },
                };

        assertEquals( 1 , islandsCounter.countIslands(boolGrid));
    }

    @Test
    public void countDiagonalIslandsGrid() {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                        { true , false, true, false },
                        { false , true, false, true },
                        { true , false, true, false },
                        { false , true, false, true },
                };

        assertEquals( 8 , islandsCounter.countIslands(boolGrid));
    }

    @Test
    public void countIslandInOneDimArr() {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                };

        assertEquals( 0 , islandsCounter.countIslands(boolGrid));
    }
}