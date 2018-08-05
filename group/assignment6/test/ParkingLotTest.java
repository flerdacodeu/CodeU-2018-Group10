package assignment6.test;

import assignment6.ParkingLot.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {

  private Space space1;
  private Space space2;
  private Space space3;
  private Space space4;
  private Space space5;
  private Car car1;
  private Car car2;
  private Car car3;
  private Car car4;
  HashMap<Space, Car> initialMap;
  HashMap<Space, Car> goalMap;

  @Before
  public void setUp() {
    space1 = new Space(1);
    space2 = new Space(2);
    space3 = new Space(3);
    space4 = new Space(4);
    space5 = new Space(5);
    car1 = new Car(1);
    car2 = new Car(2);
    car3 = new Car(3);
    car4 = new Car(4);
    initialMap = new HashMap<>();
    goalMap = new HashMap<>();
  }

  @Test
  public void testGetCarMovesWithExample() {
    initialMap.put(space1, car1);
    initialMap.put(space2, car2);
    initialMap.put(space4, car3);
    initialMap.put(space3, Car.noCar);
    ParkingLot initialState = new ParkingLot(initialMap);

    goalMap.put(space2, car1);
    goalMap.put(space3, car2);
    goalMap.put(space1, car3);
    goalMap.put(space4, Car.noCar);
    ParkingLot goalState = new ParkingLot(goalMap);

    List<Move> moves = initialState.getCarMoves(goalState);
    List<Move> expectedMoves = new ArrayList<>();
    expectedMoves.add(new Move(car2));
    expectedMoves.add(new Move(car1));
    expectedMoves.add(new Move(car3));

    assertTrue(expectedMoves.equals(moves));
  }

  @Test
  public void testGetCarMovesWithReversedExample() {
    initialMap.put(space2, car1);
    initialMap.put(space3, car2);
    initialMap.put(space1, car3);
    initialMap.put(space4, Car.noCar);
    ParkingLot initialState = new ParkingLot(initialMap);

    goalMap.put(space1, car1);
    goalMap.put(space2, car2);
    goalMap.put(space4, car3);
    goalMap.put(space3, Car.noCar);
    ParkingLot goalState = new ParkingLot(goalMap);

    List<Move> moves = initialState.getCarMoves(goalState);
    List<Move> expectedMoves = new ArrayList<>();
    expectedMoves.add(new Move(car3));
    expectedMoves.add(new Move(car1));
    expectedMoves.add(new Move(car2));

    assertTrue(expectedMoves.equals(moves));
  }

  @Test
  public void testCanHandleCircularDependency() {
    initialMap.put(space1, car1);
    initialMap.put(space2, car2);
    initialMap.put(space3, car3);
    initialMap.put(space4, Car.noCar);
    ParkingLot initialState = new ParkingLot(initialMap);

    goalMap.put(space2, car1);
    goalMap.put(space3, car2);
    goalMap.put(space1, car3);
    goalMap.put(space4, Car.noCar);
    ParkingLot goalState = new ParkingLot(goalMap);

    List<Move> moves = initialState.getCarMoves(goalState);
    /* Need to check moves against all valid move sequences
     * because getKeySet() of HashMaps may not always be the
     * same order of keys when iterating over them.
     */
    List<List<Move>> validMoveSequences = getMoveLists(new Move[][]{
      {new Move(car1), new Move(car3), new Move(car2), new Move(car1)},
      {new Move(car2), new Move(car1), new Move(car3), new Move(car2)},
      {new Move(car3), new Move(car2), new Move(car1), new Move(car3)}
    });

    assertTrue(validMoveSequences.contains(moves));
  }

  @Test
  public void testCanHandleMultipleCircularDependencies() {
    initialMap.put(space1, car1);
    initialMap.put(space2, car2);
    initialMap.put(space3, car3);
    initialMap.put(space4, car4);
    initialMap.put(space5, Car.noCar);
    ParkingLot initialState = new ParkingLot(initialMap);

    goalMap.put(space2, car1);
    goalMap.put(space1, car2);
    goalMap.put(space4, car3);
    goalMap.put(space3, car4);
    goalMap.put(space5, Car.noCar);
    ParkingLot goalState = new ParkingLot(goalMap);

    List<Move> moves = initialState.getCarMoves(goalState);
    List<List<Move>> validMoveSequences = getMoveLists(new Move[][]{
        {new Move(car1), new Move(car2), new Move(car1), new Move(car3), new Move(car4), new Move(car3)},
        {new Move(car1), new Move(car2), new Move(car1), new Move(car4), new Move(car3), new Move(car4)},
        {new Move(car2), new Move(car1), new Move(car2), new Move(car3), new Move(car4), new Move(car3)},
        {new Move(car2), new Move(car1), new Move(car2), new Move(car4), new Move(car3), new Move(car4)},
        {new Move(car3), new Move(car4), new Move(car3), new Move(car1), new Move(car2), new Move(car1)},
        {new Move(car3), new Move(car4), new Move(car3), new Move(car2), new Move(car1), new Move(car2)},
        {new Move(car4), new Move(car3), new Move(car4), new Move(car1), new Move(car2), new Move(car1)},
        {new Move(car4), new Move(car3), new Move(car4), new Move(car2), new Move(car1), new Move(car2)}
    });

    assertTrue(validMoveSequences.contains(moves));
  }
  
  /**
   * Transforms an array of Move arrays into a list of Move lists.
   * @param moveArrays
   * @return list of Move lists
   */
  private List<List<Move>> getMoveLists(Move[][] moveArrays) {
    List<List<Move>> moveLists = new ArrayList<>();
    for (Move[] moveArray : moveArrays) {
      List<Move> moveList = new ArrayList<>();
      Collections.addAll(moveList, moveArray);
      moveLists.add(moveList);
    }
    return moveLists;
  }

  @Test
  public void testEmptyParkingLot() {
    ParkingLot initialState = new ParkingLot(initialMap);
    ParkingLot goalState = new ParkingLot(goalMap);

    List<Move> moves = initialState.getCarMoves(goalState);
    List<Move> expectedMoves = new ArrayList<>();

    assertEquals(expectedMoves, moves);
  }

  @Test
  public void allRearrangementsTest() {
    initialMap.put(space1, car1);
    initialMap.put(space2, car2);
    initialMap.put(space4, car3);
    initialMap.put(space3, Car.noCar);
    ParkingLot initialState = new ParkingLot(initialMap);

    goalMap.put(space2, car1);
    goalMap.put(space3, car2);
    goalMap.put(space1, car3);
    goalMap.put(space4, Car.noCar);
    ParkingLot goalState = new ParkingLot(goalMap);

    List<List<CarMove>> allMoves = initialState.getAllPossibleRearrangements(goalState);

    for (List<CarMove> carMoves : allMoves) {
      initialState = new ParkingLot(initialMap);
      initialState.makeMoves(carMoves);
      assertEquals(goalState, initialState);
    }
  }
}