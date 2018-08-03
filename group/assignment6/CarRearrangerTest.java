import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CarRearrangerTest {

  @Test
  public void testCanMoveOneCar() {
    int[] initialState = new int[] {
        0, 1
    };
    int[] goalState = new int[] {
        1, 0
    };
    
    List<Move> moves = CarRearranger.getCarMoves(initialState, goalState);
    List<Move> expectedMoves = new ArrayList<Move>();
    
//    expectedMoves.add(new Move(1, 1, 0));
    expectedMoves.add(new Move(1));
    
    assertListEquals(expectedMoves, moves);
  }
  
  @Test
  public void testCanMoveExampleCars() {
    int[] initialState = new int[] {
        2, 0, 1, 3
    };
    int[] goalState = new int[] {
        3, 1, 2, 0
    };
    
    List<Move> moves = CarRearranger.getCarMoves(initialState, goalState);
    List<Move> expectedMoves = new ArrayList<Move>();
    
//    expectedMoves.add(new Move(2, 1, 2));
//    expectedMoves.add(new Move(1, 0, 1));
//    expectedMoves.add(new Move(3, 3, 0));
    expectedMoves.add(new Move(2));
    expectedMoves.add(new Move(1));
    expectedMoves.add(new Move(3));
    
    assertListEquals(expectedMoves, moves);
  }
  
  @Test
  public void testCanHandleCircularDependency() {
    int[] initialState = new int[] {
        3, 0, 1, 2
    };
    int[] goalState = new int[] {
        3, 1, 2, 0
    };
    
    List<Move> moves = CarRearranger.getCarMoves(initialState, goalState);
    List<Move> expectedMoves = new ArrayList<Move>();
    
//    expectedMoves.add(new Move(1, 0, 3));
//    expectedMoves.add(new Move(3, 2, 0));
//    expectedMoves.add(new Move(2, 1, 2));
//    expectedMoves.add(new Move(1, 3, 1));
    expectedMoves.add(new Move(1));
    expectedMoves.add(new Move(3));
    expectedMoves.add(new Move(2));
    expectedMoves.add(new Move(1));
    
    for (int i = 0; i < moves.size(); i++) {
      System.out.println(moves.get(i));
    }
    
    assertListEquals(expectedMoves, moves);
  }
  
  @Test
  public void testEmptyCarPark() {
    int[] initialState = new int[0];
    int[] goalState = new int[0];
    
    List<Move> moves = CarRearranger.getCarMoves(initialState, goalState);
    List<Move> expectedMoves = new ArrayList<Move>();
    
    assertListEquals(expectedMoves, moves);
  }

  private void assertListEquals(List<Move> expectedMoves, List<Move> moves) {
    assertEquals(expectedMoves.size(), moves.size());
    for (int i = 0; i < expectedMoves.size(); i++) {
      assertEquals(expectedMoves.get(i), moves.get(i));
    }
  }
}
