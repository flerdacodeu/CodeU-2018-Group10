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
    
    List<Move> moves = CarRearranger.rearrangeCars(initialState, goalState);
    List<Move> expectedMoves = new ArrayList<Move>();
    
    expectedMoves.add(new Move(1, 1, 0));
    
    assertTrue(expectedMoves, moves);
  }
  
  @Test
  public void testCanMoveExampleCars() {
    int[] initialState = new int[] {
        2, 0, 1, 3
    };
    int[] goalState = new int[] {
        3, 1, 2, 0
    };
    
    List<Move> moves = CarRearranger.rearrangeCars(initialState, goalState);
    List<Move> expectedMoves = new ArrayList<Move>();
    
    expectedMoves.add(new Move(2, 1, 2));
    expectedMoves.add(new Move(1, 0, 1));
    expectedMoves.add(new Move(3, 3, 0));
    
    assertTrue(expectedMoves, moves);
  }

  private void assertTrue(List<Move> expectedMoves, List<Move> moves) {
    assertEquals(expectedMoves.size(), moves.size());
    
    for (int i = 0; i < expectedMoves.size(); i++) {
      assertEquals(expectedMoves.get(i), moves.get(i));
    }
  }
}
