package assignment6.test;

import assignment6.ParkingLot.Car;
import assignment6.ParkingLot.Move;
import assignment6.ParkingLot.CarMove;
import assignment6.ParkingLot.ParkingLot;
import assignment6.ParkingLot.Space;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
  public void testRearrangeParkingLotExample() {
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

    System.out.println(initialState);
    System.out.println(initialState.rearrange(goalState));
    System.out.println(initialState);
    System.out.println(goalState);

    assertTrue(initialState.equals(goalState));
  }

  @Test
  public void testRearrangeParkingLotExampleReversed() {
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

    System.out.println(initialState);
    System.out.println(initialState.rearrange(goalState));
    System.out.println(initialState);
    System.out.println(goalState);

    assertTrue(initialState.equals(goalState));
  }

  @Test
  public void testRearrangeInFewerSteps() {
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

    System.out.println(initialState);
    System.out.println(initialState.rearrangeInFewerSteps(goalState));
    System.out.println(initialState);
    System.out.println(goalState);

    assertTrue(initialState.equals(goalState));
  }

  @Test
  public void testRearrangeInFewerStepsTestWithCircuit() {
    initialMap.put(space1, Car.noCar);
    initialMap.put(space2, car1);
    initialMap.put(space3, car2);
    initialMap.put(space4, car3);
    initialMap.put(space5, car4);
    ParkingLot initialState = new ParkingLot(initialMap);

    goalMap.put(space1, Car.noCar);
    goalMap.put(space2, car2);
    goalMap.put(space3, car3);
    goalMap.put(space4, car4);
    goalMap.put(space5, car1);
    ParkingLot goalState = new ParkingLot(goalMap);

    System.out.println(initialState);
    System.out.println(initialState.rearrangeInFewerSteps(goalState));
    System.out.println(initialState);
    System.out.println(goalState);

    assertTrue(initialState.equals(goalState));
  }

  @Test
  public void testRearrangeInFewerStepsTestWithCircuitAnd2ConnectedComponents() {
    initialMap.put(space1, Car.noCar);
    initialMap.put(space2, car1);
    initialMap.put(space3, car2);
    initialMap.put(space4, car3);
    initialMap.put(space5, car4);
    ParkingLot initialState = new ParkingLot(initialMap);

    goalMap.put(space1, Car.noCar);
    goalMap.put(space2, car3);
    goalMap.put(space3, car2);
    goalMap.put(space4, car4);
    goalMap.put(space5, car1);
    ParkingLot goalState = new ParkingLot(goalMap);

    System.out.println(initialState);
    System.out.println(initialState.rearrangeInFewerSteps(goalState));
    System.out.println(initialState);
    System.out.println(goalState);

    assertTrue(initialState.equals(goalState));
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
    List<Move> expectedMoves = new ArrayList<Move>();
    expectedMoves.add(new Move(car2));
    expectedMoves.add(new Move(car1));
    expectedMoves.add(new Move(car3));
    
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
    List<List<Move>> validMoveSequences = new ArrayList<List<Move>>();
    
    addValidMoveSequences(validMoveSequences);

    assertTrue(validMoveSequences.contains(moves));
  }

  private void addValidMoveSequences(List<List<Move>> validMoveSequences) {
    List<Move> validMove1 = new ArrayList<Move>();
    validMove1.add(new Move(car1));
    validMove1.add(new Move(car3));
    validMove1.add(new Move(car2));
    validMove1.add(new Move(car1));
    validMoveSequences.add(validMove1);
    
    List<Move> validMove2 = new ArrayList<Move>();
    validMove2.add(new Move(car2));
    validMove2.add(new Move(car1));
    validMove2.add(new Move(car3));
    validMove2.add(new Move(car2));
    validMoveSequences.add(validMove2);
    
    List<Move> validMove3 = new ArrayList<Move>();
    validMove3.add(new Move(car3));
    validMove3.add(new Move(car2));
    validMove3.add(new Move(car1));
    validMove3.add(new Move(car3));
    validMoveSequences.add(validMove3);
  }
  
  @Test
  public void testEmptyCarPark() {
    ParkingLot initialState = new ParkingLot(initialMap);
    ParkingLot goalState = new ParkingLot(goalMap);
    
    List<Move> moves = initialState.getCarMoves(goalState);
    List<Move> expectedMoves = new ArrayList<Move>();
    
    assertTrue(expectedMoves.equals(moves));
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

        System.out.println(allMoves.size());
        for (List<CarMove> carMoves : allMoves) {
            if(carMoves.size() == 3) {
                System.out.println("YEEEAH, sequence of moves with the least amount if moves is also here !");
            }
        }
        //GIRLS, IT WOOOOOOOOOOOOOOOOOOOOOOOOORKS !!
        for (List<CarMove> carMoves : allMoves) {
            initialState = new ParkingLot(initialMap);
            initialState.makeMoves(carMoves);
            assertEquals(goalState, initialState);
        }
    }
}