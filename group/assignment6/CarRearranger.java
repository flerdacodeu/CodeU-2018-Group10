import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CarRearranger {
  
  //TODO: sort, refactor, comments/javadoc, rename, edge cases
  
  /**
   * Find a list of moves to rearrange cars from an initial state to a goal state.
   * This method assumes there is exactly one empty space.
   * @param initialState an array of space numbers from the perspective of cars
   * @param goalState    an array of car numbers from the perspective of spaces
   * @return a list of moves
   */
  public static List<Move> getCarMoves(int[] initialState, int[] goalState) {
    
    List<Move> moves = new ArrayList<Move>();
    int[] currentState = Arrays.copyOf(initialState, initialState.length);
    //int[] currentState = changeStateFormat(initialState);
    
    moveCars(currentState, goalState, moves);
    return moves;
  }

  private static int[] changeStateFormat(int[] state) {
    // index is space, value is car
    // to index is car, value is space
    int[] transformedState = Arrays.copyOf(state, state.length);
    
    sort(transformedState, 0, state.length);
    
    return transformedState;
  }
  
  private static void sort(int[] state, int lowerBound, int higherBound) {
    
    while (lowerBound < higherBound) {
      int middleIndex = partition(state, lowerBound, higherBound);
      
      sort(state, lowerBound, middleIndex - 1);
      sort(state, middleIndex, higherBound);
    }
  }

  private static int partition(int[] state, int lowerBound, int higherBound) {
    int lower = lowerBound;
    int higher = lowerBound;
    
    Random rand = new Random();
    int pivotIndex = rand.nextInt((higherBound - lowerBound) + 1) + lowerBound;
    int pivotValue = state[pivotIndex];
    
    for (int i = lowerBound; i < higherBound; i++) {
      if (state[higher] > pivotValue) {
        //swap
        //increment lower
      }
      
      //increment higher
    }
    
    //put pivot in right place
    
    return pivotIndex;
  }

  private static void moveCars(int[] currentState, int[] goalState, List<Move> moves) {
    for (int i = 0; i < currentState.length; i++) {
      int emptySpaceNum = currentState[0];
      
      if (!spaceShouldBeEmpty(emptySpaceNum, goalState)) {
        int carNum = getGoalCarNum(emptySpaceNum, goalState);
        moveCarToEmptySpace(carNum, currentState, moves);
      } else {
        for (int j = 0; j < currentState.length; j++) {
          int carNum = j;
          if (carIsInWrongPlace(carNum, currentState, goalState)) {
            moveCarToEmptySpace(carNum, currentState, moves);
            moveCars(currentState, goalState, moves);
          }
        }
      }
    }
  }

  private static boolean spaceShouldBeEmpty(int emptySpaceNum, int[] goalState) {
    return goalState[emptySpaceNum] == 0;
  }

  private static int getGoalCarNum(int spaceNum, int[] goalState) {
    return goalState[spaceNum];
  }
  
  private static void moveCarToEmptySpace(int carNum, int[] currentState, List<Move> moves) {
    int spaceNum = currentState[carNum];
    int emptySpaceNum = currentState[0];
    currentState[carNum] = emptySpaceNum;
    currentState[0] = spaceNum;
    moves.add(new Move(carNum));
  }

  private static boolean carIsInWrongPlace(int carNum, int[] currentState, int[] goalState) {
    int currentPlaceNum = currentState[carNum];
    return goalState[currentPlaceNum] != carNum;
  }
}
