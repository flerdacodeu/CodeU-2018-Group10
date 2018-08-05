package assignment6.test;

import assignment6.ParkingLot.Car;
import assignment6.ParkingLot.CarMove;
import assignment6.ParkingLot.ParkingLotWithReserved;
import assignment6.ParkingLot.ReservedSpace;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ParkingLotWithReservedTest {


  private ReservedSpace space1;
  private ReservedSpace space2;
  private ReservedSpace space3;
  private ReservedSpace space4;

  private Car car1;
  private Car car2;
  private Car car3;

  private HashMap<ReservedSpace, Car> initialMap;
  private HashMap<ReservedSpace, Car> goalMap;

  private HashSet<Car> reservedForSpace1;
  private HashSet<Car> reservedForSpace2;
  private HashSet<Car> reservedForSpace3;
  private HashSet<Car> reservedForSpace4;

  @Before
  public void setUp() {
    car1 = new Car(1);
    car2 = new Car(2);
    car3 = new Car(3);
    initialMap = new HashMap<>();
    goalMap = new HashMap<>();
    reservedForSpace1 = new HashSet<>();
    reservedForSpace2 = new HashSet<>();
    reservedForSpace3 = new HashSet<>();
    reservedForSpace4 = new HashSet<>();
  }

  @Test
  public void createGraphForNoReservedSpacesAssignmentExample() {

    // create reserved car set for spaces
    HashSet<Car> allCars = new HashSet<>();
    allCars.add(car1);
    allCars.add(car2);
    allCars.add(car3);

    // create spaces
    space1 = new ReservedSpace(1, allCars);
    space2 = new ReservedSpace(2, allCars);
    space3 = new ReservedSpace(3, allCars);
    space4 = new ReservedSpace(4, allCars);

    // create start Parking lot
    initialMap.put(space1, car1);
    initialMap.put(space2, car2);
    initialMap.put(space4, car3);
    initialMap.put(space3, Car.noCar);
    ParkingLotWithReserved parkingLotStart = new ParkingLotWithReserved(initialMap);

    // create end Parking lot
    goalMap.put(space2, car1);
    goalMap.put(space3, car2);
    goalMap.put(space1, car3);
    goalMap.put(space4, Car.noCar);
    ParkingLotWithReserved parkingLotEnd = new ParkingLotWithReserved(goalMap);

    List<CarMove> rearrangementCarMoves = parkingLotStart.rearrange(parkingLotEnd);
    Assert.assertTrue(ParkingLotWithReserved.areMovesCreateEndResult(parkingLotStart, parkingLotEnd,
        rearrangementCarMoves));
  }

  @Test
  public void createGraphForSmallExample() {
    // create reserved car sets for spaces
    HashSet<Car> allCars = new HashSet<>();
    allCars.add(car1);
    allCars.add(car2);
    allCars.add(car3);

    // create reserved car sets for spaces
    reservedForSpace3 = new HashSet<>();
    reservedForSpace3.add(car3);
    reservedForSpace3.add(car2);
    reservedForSpace4 = new HashSet<>();
    reservedForSpace4.add(car3);

    // create spaces
    space1 = new ReservedSpace(1, allCars);
    space2 = new ReservedSpace(2, allCars);
    space3 = new ReservedSpace(3, reservedForSpace3);
    space4 = new ReservedSpace(4, reservedForSpace4);

    // create start Parking lot
    initialMap.put(space1, car1);
    initialMap.put(space2, car2);
    initialMap.put(space3, car3);
    initialMap.put(space4, Car.noCar);
    ParkingLotWithReserved parkingLotStart = new ParkingLotWithReserved(initialMap);

    // create end Parking lot
    goalMap.put(space1, Car.noCar);
    goalMap.put(space2, car1);
    goalMap.put(space3, car2);
    goalMap.put(space4, car3);
    ParkingLotWithReserved parkingLotEnd = new ParkingLotWithReserved(goalMap);

    List<CarMove> rearrangementCarMoves = parkingLotStart.rearrange(parkingLotEnd);
    Assert.assertTrue(ParkingLotWithReserved.areMovesCreateEndResult(parkingLotStart, parkingLotEnd,
        rearrangementCarMoves));
  }

  @Test
  public void createGraphForExample() {

    // create reserved car sets for spaces
    reservedForSpace1 = new HashSet<>();
    reservedForSpace1.add(car1);
    reservedForSpace1.add(car3);
    reservedForSpace2 = new HashSet<>();
    reservedForSpace2.add(car2);
    reservedForSpace3 = new HashSet<>();
    reservedForSpace3.add(car1);
    reservedForSpace3.add(car2);
    reservedForSpace4 = new HashSet<>();
    reservedForSpace4.add(car1);
    reservedForSpace4.add(car3);

    // create spaces
    space1 = new ReservedSpace(1, reservedForSpace1);
    space2 = new ReservedSpace(2, reservedForSpace2);
    space3 = new ReservedSpace(3, reservedForSpace3);
    space4 = new ReservedSpace(4, reservedForSpace4);

    // create start Parking lot
    initialMap.put(space1, car1);
    initialMap.put(space2, Car.noCar);
    initialMap.put(space3, car2);
    initialMap.put(space4, car3);
    ParkingLotWithReserved parkingLotStart = new ParkingLotWithReserved(initialMap);

    // create end Parking lot
    goalMap.put(space1, car3);
    goalMap.put(space2, car2);
    goalMap.put(space3, car1);
    goalMap.put(space4, Car.noCar);
    ParkingLotWithReserved parkingLotEnd = new ParkingLotWithReserved(goalMap);

    List<CarMove> rearrangementCarMoves = parkingLotStart.rearrange(parkingLotEnd);
    Assert.assertTrue(ParkingLotWithReserved.areMovesCreateEndResult(parkingLotStart, parkingLotEnd,
        rearrangementCarMoves));
  }

  @Test (expected = IllegalArgumentException.class)
  public void parkingLotWithCarInSpaceItInstReservedFor() {         //TODO: NAME IT BETTER?

    // create reserved car sets for spaces
    HashSet<Car> allCars = new HashSet<>();
    allCars.add(car1);
    allCars.add(car2);
    allCars.add(car3);
    reservedForSpace1 = new HashSet<>();
    reservedForSpace1.add(car1);
    reservedForSpace2 = new HashSet<>();
    reservedForSpace2.add(car2);
    reservedForSpace3 = new HashSet<>();
    reservedForSpace3.add(car3);

    // create spaces
    space1 = new ReservedSpace(1, reservedForSpace1);
    space2 = new ReservedSpace(2, reservedForSpace2);
    space3 = new ReservedSpace(3, reservedForSpace3);
    space4 = new ReservedSpace(4, allCars);

    // create Parking lot
    goalMap = new HashMap<>();
    goalMap.put(space1, car3);
    goalMap.put(space2, car1);
    goalMap.put(space3, car2);
    goalMap.put(space4, Car.noCar);

    new ParkingLotWithReserved(goalMap);
  }

  @Test (expected = IllegalArgumentException.class)
  public void notEnoughSpaces() {
    // create car
    car1 = new Car(1);

    // create reserved car set for space
    reservedForSpace1 = new HashSet<>();
    reservedForSpace1.add(car1);

    // create space
    space1 = new ReservedSpace(1, reservedForSpace1);

    // create Parking lot
    initialMap = new HashMap<>();
    initialMap.put(space1, car1);
    new ParkingLotWithReserved(initialMap);
  }
}
