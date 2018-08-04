package assignment6.test;

import assignment6.ParkingLot.Car;
import assignment6.ParkingLot.ParkingLot;
import assignment6.ParkingLot.ParkingLotMoveService;
import assignment6.ParkingLot.Space;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class ParkingLotMoveServiceTest {

    @Test
    public void createGraphForNoReservedSpacesAssignmentExample() {
        ParkingLotMoveService parkingLotMoveService = new ParkingLotMoveService();

        Car car1 = new Car(1);
        Car car2 = new Car(2);
        Car car3 = new Car(3);
        HashSet<Car> allCars = new HashSet<>();
        allCars.add(car1);
        allCars.add(car2);
        allCars.add(car3);
        Space space1 = new Space(1, allCars);
        Space space2 = new Space(2, allCars);
        Space space3 = new Space(3, allCars);
        Space space4 = new Space(4, allCars);

        HashMap<Space, Car> startHashMap = new HashMap<>();
        startHashMap.put(space1,car1);
        startHashMap.put(space2,car2);
        startHashMap.put(space4,car3);
        startHashMap.put(space3, Car.noCar);
        ParkingLot parkingLotStart = new ParkingLot(startHashMap);

        HashMap<Space, Car> endHashMap = new HashMap<>();
        endHashMap.put(space2,car1);
        endHashMap.put(space3,car2);
        endHashMap.put(space1,car3);
        endHashMap.put(space4,Car.noCar);
        ParkingLot parkingLotEnd = new ParkingLot(endHashMap);


        try {
            parkingLotMoveService.updateParkingOrderPossible(parkingLotStart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    @Test
    public void createGraphForSmallExample() {
        ParkingLotMoveService parkingLotMoveService = new ParkingLotMoveService();

        //create cars
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
        Space space1 = new Space(1, allCars);
        Space space2 = new Space(2, allCars);
        Space space3 = new Space(3, reservedForSpace3);
        Space space4 = new Space(4, reservedForSpace4);

        // create start Parking lot
        HashMap<Space, Car> startHashMap = new HashMap();
        startHashMap.put(space1,car1);
        startHashMap.put(space2,car2);
        startHashMap.put(space3,car3);
        startHashMap.put(space4, Car.noCar);
        ParkingLot parkingLotStart = new ParkingLot(startHashMap);

        // create end Parking lot
        HashMap<Space, Car> endHashMap = new HashMap();
        endHashMap.put(space1,Car.noCar);
        endHashMap.put(space2,car1);
        endHashMap.put(space3,car2);
        endHashMap.put(space4,car3);
        ParkingLot parkingLotEnd = new ParkingLot(endHashMap);


        try {
            parkingLotMoveService.updateParkingOrderPossible(parkingLotStart, parkingLotEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}