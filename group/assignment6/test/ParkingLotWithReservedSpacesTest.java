package assignment6.test;

import assignment6.ParkingLot.Car;
import assignment6.ParkingLot.CarMove;
import assignment6.ParkingLot.ParkingLotWithReservedSpaces;
import assignment6.ParkingLot.ReservedSpace;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ParkingLotWithReservedSpacesTest {

  @Test
  public void createGraphForNoReservedSpacesAssignmentExample() {
    Car car1 = new Car(1);
    Car car2 = new Car(2);
    Car car3 = new Car(3);
    HashSet<Car> allCars = new HashSet<>();
    allCars.add(car1);
    allCars.add(car2);
    allCars.add(car3);
    ReservedSpace space1 = new ReservedSpace(1, allCars);
    ReservedSpace space2 = new ReservedSpace(2, allCars);
    ReservedSpace space3 = new ReservedSpace(3, allCars);
    ReservedSpace space4 = new ReservedSpace(4, allCars);

    HashMap<ReservedSpace, Car> startHashMap = new HashMap<>();
    startHashMap.put(space1, car1);
    startHashMap.put(space2, car2);
    startHashMap.put(space4, car3);
    startHashMap.put(space3, Car.noCar);


    HashMap<ReservedSpace, Car> endHashMap = new HashMap<>();
    endHashMap.put(space2, car1);
    endHashMap.put(space3, car2);
    endHashMap.put(space1, car3);
    endHashMap.put(space4, Car.noCar);


    ParkingLotWithReservedSpaces parkingLotStart = new ParkingLotWithReservedSpaces(startHashMap);
    ParkingLotWithReservedSpaces parkingLotEnd = new ParkingLotWithReservedSpaces(endHashMap);

    List<CarMove> rearengementCarMoves = parkingLotStart.rearrange(parkingLotEnd);
    Assert.assertTrue(ParkingLotWithReservedSpaces.areMovesCreateEndResult(parkingLotStart, parkingLotEnd,
        rearengementCarMoves));
  }

  @Test
  public void createGraphForSmallExample() {
    // create cars
    Car car1 = new Car(1);
    Car car2 = new Car(2);
    Car car3 = new Car(3);

    // create reserved car sets for spaces
    HashSet<Car> allCars = new HashSet<>();
    allCars.add(car1);
    allCars.add(car2);
    allCars.add(car3);
    HashSet<Car> reservedForSpace3 = new HashSet<>();
    reservedForSpace3.add(car3);
    reservedForSpace3.add(car2);
    HashSet<Car> reservedForSpace4 = new HashSet<>();
    reservedForSpace4.add(car3);

    // create spaces
    ReservedSpace space1 = new ReservedSpace(1, allCars);
    ReservedSpace space2 = new ReservedSpace(2, allCars);
    ReservedSpace space3 = new ReservedSpace(3, reservedForSpace3);
    ReservedSpace space4 = new ReservedSpace(4, reservedForSpace4);

    // create start Parking lot
    HashMap<ReservedSpace, Car> startHashMap = new HashMap<>();
    startHashMap.put(space1, car1);
    startHashMap.put(space2, car2);
    startHashMap.put(space3, car3);
    startHashMap.put(space4, Car.noCar);

    // create end Parking lot
    HashMap<ReservedSpace, Car> endHashMap = new HashMap<>();
    endHashMap.put(space1, Car.noCar);
    endHashMap.put(space2, car1);
    endHashMap.put(space3, car2);
    endHashMap.put(space4, car3);

    ParkingLotWithReservedSpaces parkingLotStart = new ParkingLotWithReservedSpaces(startHashMap);
    ParkingLotWithReservedSpaces parkingLotEnd = new ParkingLotWithReservedSpaces(endHashMap);

    List<CarMove> rearengementCarMoves = parkingLotStart.rearrange(parkingLotEnd);
    Assert.assertTrue(ParkingLotWithReservedSpaces.areMovesCreateEndResult(parkingLotStart, parkingLotEnd,
        rearengementCarMoves));
  }

  @Test
  public void createGraphForExample() {
    // create cars
    Car car1 = new Car(1);
    Car car2 = new Car(2);
    Car car3 = new Car(3);

    // create reserved car sets for spaces
    HashSet<Car> allCars = new HashSet<>();
    allCars.add(car1);
    allCars.add(car2);
    allCars.add(car3);
    HashSet<Car> reservedForSpace1 = new HashSet<>();
    reservedForSpace1.add(car1);
    reservedForSpace1.add(car3);
    HashSet<Car> reservedForSpace2 = new HashSet<>();
    reservedForSpace2.add(car2);
    HashSet<Car> reservedForSpace3 = new HashSet<>();
    reservedForSpace3.add(car1);
    reservedForSpace3.add(car2);
    HashSet<Car> reservedForSpace4 = new HashSet<>();
    reservedForSpace4.add(car1);
    reservedForSpace4.add(car3);

    // create spaces
    ReservedSpace space1 = new ReservedSpace(1, reservedForSpace1);
    ReservedSpace space2 = new ReservedSpace(2, reservedForSpace2);
    ReservedSpace space3 = new ReservedSpace(3, reservedForSpace3);
    ReservedSpace space4 = new ReservedSpace(4, reservedForSpace4);

    // create start Parking lot
    HashMap<ReservedSpace, Car> startHashMap = new HashMap<>();
    startHashMap.put(space1, car1);
    startHashMap.put(space2, Car.noCar);
    startHashMap.put(space3, car2);
    startHashMap.put(space4, car3);

    // create end Parking lot
    HashMap<ReservedSpace, Car> endHashMap = new HashMap<>();
    endHashMap.put(space1, car3);
    endHashMap.put(space2, car2);
    endHashMap.put(space3, car1);
    endHashMap.put(space4, Car.noCar);

    ParkingLotWithReservedSpaces parkingLotStart = new ParkingLotWithReservedSpaces(startHashMap);
    ParkingLotWithReservedSpaces parkingLotEnd = new ParkingLotWithReservedSpaces(endHashMap);

    List<CarMove> rearrangementCarMoves = parkingLotStart.rearrange(parkingLotEnd);
    Assert.assertTrue(ParkingLotWithReservedSpaces.areMovesCreateEndResult(parkingLotStart, parkingLotEnd,
        rearrangementCarMoves));
  }

  @Test
  public void createGraphForNoPossibleRearrangement() {
    // create cars
    Car car1 = new Car(1);
    Car car2 = new Car(2);
    Car car3 = new Car(3);

    // create reserved car sets for spaces
    HashSet<Car> allCars = new HashSet<>();
    allCars.add(car1);
    allCars.add(car2);
    allCars.add(car3);
    HashSet<Car> reservedForSpace1 = new HashSet<>();
    reservedForSpace1.add(car1);
    HashSet<Car> reservedForSpace2 = new HashSet<>();
    reservedForSpace2.add(car2);
    HashSet<Car> reservedForSpace3 = new HashSet<>();
    reservedForSpace3.add(car3);

    // create spaces
    ReservedSpace space1 = new ReservedSpace(1, reservedForSpace1);
    ReservedSpace space2 = new ReservedSpace(2, reservedForSpace2);
    ReservedSpace space3 = new ReservedSpace(3, reservedForSpace3);
    ReservedSpace space4 = new ReservedSpace(4, allCars);

    // create start Parking lot
    HashMap<ReservedSpace, Car> startHashMap = new HashMap<>();
    startHashMap.put(space1, car1);
    startHashMap.put(space2, car2);
    startHashMap.put(space3, car3);
    startHashMap.put(space4, Car.noCar);

    // create end Parking lot
    HashMap<ReservedSpace, Car> endHashMap = new HashMap<>();
    endHashMap.put(space1, car3);
    endHashMap.put(space2, car1);
    endHashMap.put(space3, car2);
    endHashMap.put(space4, Car.noCar);

    ParkingLotWithReservedSpaces parkingLotStart = new ParkingLotWithReservedSpaces(startHashMap);
    ParkingLotWithReservedSpaces parkingLotEnd = new ParkingLotWithReservedSpaces(endHashMap);

    List<CarMove> rearrangementCarMoves = parkingLotStart.rearrange(parkingLotEnd);
    Assert.assertFalse(ParkingLotWithReservedSpaces.areMovesCreateEndResult(parkingLotStart,
        parkingLotEnd, rearrangementCarMoves));
  }

  @Test
  public void createGraphForNoNeededRearrangement() {
    // create cars
    Car car1 = new Car(1);

    // create reserved car sets for spaces
    HashSet<Car> allCars = new HashSet<>();
    allCars.add(car1);
    HashSet<Car> reservedForSpace1 = new HashSet<>();
    reservedForSpace1.add(car1);
    HashSet<Car> reservedForSpace2 = new HashSet<>();

    // create spaces
    ReservedSpace space1 = new ReservedSpace(1, reservedForSpace1);
    ReservedSpace space2 = new ReservedSpace(2, reservedForSpace2);

    // create start Parking lot
    HashMap<ReservedSpace, Car> startHashMap = new HashMap<>();
    startHashMap.put(space1, car1);

    // create end Parking lot
    HashMap<ReservedSpace, Car> endHashMap = new HashMap<>();
    endHashMap.put(space1, car1);

    ParkingLotWithReservedSpaces parkingLotStart = new ParkingLotWithReservedSpaces(startHashMap);
    ParkingLotWithReservedSpaces parkingLotEnd = new ParkingLotWithReservedSpaces(endHashMap);

    List<CarMove> rearrangementCarMoves = parkingLotStart.rearrange(parkingLotEnd);
    Assert.assertTrue(ParkingLotWithReservedSpaces.areMovesCreateEndResult(parkingLotStart, parkingLotEnd,
        rearrangementCarMoves));
  }

}
