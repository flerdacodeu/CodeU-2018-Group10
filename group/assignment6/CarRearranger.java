import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarRearranger {
  
  /**
   * Find a list of moves to rearrange cars from an initial state to a goal state.
   * This method assumes there is exactly one empty space.
   * @param initialState an array of space numbers from the perspective of cars
   * @param goalState    an array of car numbers from the perspective of spaces
   * @return a list of moves
   */
  public static List<Move> rearrangeCars(int[] initialState, int[] goalState) {
    
    List<Move> moves = new ArrayList<Move>();
    
    int[] currentState = Arrays.copyOf(initialState, initialState.length);
    
    for (int i = 0; i < currentState.length; i++) {
      int emptySpaceNum = currentState[0];
      int goalCarNum = goalState[emptySpaceNum];
      int goalCarPosition = currentState[goalCarNum];
      
      if (shouldNotBeEmpty(emptySpaceNum, goalState)) {
        currentState[goalCarNum] = emptySpaceNum;
        currentState[0] = goalCarPosition;
        
        moves.add(new Move(goalCarNum, goalCarPosition, emptySpaceNum));
      } else {
        break;
      }
    }
    
    return moves;
  }

  private static boolean shouldNotBeEmpty(int emptySpaceNum, int[] goalState) {
    return goalState[emptySpaceNum] != 0;
  }
}
