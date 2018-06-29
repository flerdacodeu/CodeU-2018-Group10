package com.shaya.CodeUAss4Package;

import org.junit.Assert;

import static org.junit.Assert.*;

public class IslandsCounterTest {

    @org.junit.Test
    public void countIslandInExampleGrid()
    {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                        { false , true, false, true},
                        { true , true, false, false},
                        { false , false, true, false},
                        { false , false, true, false},
                };

        Assert.assertEquals(islandsCounter.countIsland(boolGrid), 3);
    }

    @org.junit.Test
    public void countIslandInAllWaterGrid()
    {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                        { false , false, false, false},
                        { false , false, false, false},
                        { false , false, false, false},
                        { false , false, false, false},
                };

        Assert.assertEquals(islandsCounter.countIsland(boolGrid), 0);
    }

    @org.junit.Test
    public void countIslandInEmptyGrid()
    {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                        { },
                        { },
                        { },
                        { },
                };

        Assert.assertEquals(islandsCounter.countIsland(boolGrid), 0);
    }

    @org.junit.Test
    public void countIslandInAllLandGrid()
    {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                        { true , true, true, true},
                        { true , true, true, true},
                        { true , true, true, true},
                        { true , true, true, true},
                };

        Assert.assertEquals(islandsCounter.countIsland(boolGrid), 1);
    }

    @org.junit.Test
    public void countDiagonalIslandsGrid()
    {
        IslandsCounter islandsCounter = new IslandsCounter();

        boolean[][] boolGrid = new boolean[][]
                {
                        { true , false, true, false},
                        { false , true, false, true},
                        { true , false, true, false},
                        { false , true, false, true},
                };

        Assert.assertEquals(islandsCounter.countIsland(boolGrid), 8);
    }
}