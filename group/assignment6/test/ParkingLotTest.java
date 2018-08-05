package assignment6.test;

import assignment6.ParkingLot.Car;
import assignment6.ParkingLot.CarMove;
import assignment6.ParkingLot.ParkingLot;
import assignment6.ParkingLot.Space;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParkingLotTest {

    @Test
    public void rearrangeParkingLotExampleTest() {
        Space space1 = new Space(1);
        Space space2 = new Space(2);
        Space space3 = new Space(3);
        Space space4 = new Space(4);
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        Car car3 = new Car(3);

        HashMap<Space, Car> startHashMap = new HashMap<>();
        startHashMap.put(space1, car1);
        startHashMap.put(space2, car2);
        startHashMap.put(space4, car3);
        startHashMap.put(space3, Car.noCar);
        ParkingLot parkingLotStart = new ParkingLot(startHashMap);

        HashMap<Space, Car> endHashMap = new HashMap<>();
        endHashMap.put(space2, car1);
        endHashMap.put(space3, car2);
        endHashMap.put(space1, car3);
        endHashMap.put(space4, Car.noCar);
        ParkingLot parkingLotEnd = new ParkingLot(endHashMap);

        System.out.println(parkingLotStart);
        System.out.println(parkingLotStart.rearrange(parkingLotEnd));
        System.out.println(parkingLotStart);
        System.out.println(parkingLotEnd);

        assertTrue(parkingLotStart.equals(parkingLotEnd));
    }

    @Test
    public void rearrangeParkingLotExampleReversedTest() {
        Space space1 = new Space(1);
        Space space2 = new Space(2);
        Space space3 = new Space(3);
        Space space4 = new Space(4);
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        Car car3 = new Car(3);

        HashMap<Space, Car> endHashMap = new HashMap<>();
        endHashMap.put(space1, car1);
        endHashMap.put(space2, car2);
        endHashMap.put(space4, car3);
        endHashMap.put(space3, Car.noCar);
        ParkingLot parkingLotEnd = new ParkingLot(endHashMap);

        HashMap<Space, Car> startHashMap = new HashMap<>();
        startHashMap.put(space2, car1);
        startHashMap.put(space3, car2);
        startHashMap.put(space1, car3);
        startHashMap.put(space4, Car.noCar);
        ParkingLot parkingLotStart = new ParkingLot(startHashMap);

        System.out.println(parkingLotStart);
        System.out.println(parkingLotStart.rearrange(parkingLotEnd));
        System.out.println(parkingLotStart);
        System.out.println(parkingLotEnd);

        assertTrue(parkingLotStart.equals(parkingLotEnd));
    }

    @Test
    public void rearrangeInFewerStepsTest() {
        Space space1 = new Space(1);
        Space space2 = new Space(2);
        Space space3 = new Space(3);
        Space space4 = new Space(4);
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        Car car3 = new Car(3);

        HashMap<Space, Car> startHashMap = new HashMap<>();
        startHashMap.put(space1, car1);
        startHashMap.put(space2, car2);
        startHashMap.put(space4, car3);
        startHashMap.put(space3, Car.noCar);
        ParkingLot parkingLotStart = new ParkingLot(startHashMap);

        HashMap<Space, Car> endHashMap = new HashMap<>();
        endHashMap.put(space2, car1);
        endHashMap.put(space3, car2);
        endHashMap.put(space1, car3);
        endHashMap.put(space4, Car.noCar);
        ParkingLot parkingLotEnd = new ParkingLot(endHashMap);

        System.out.println(parkingLotStart);
        System.out.println(parkingLotStart.rearrangeInFewerSteps(parkingLotEnd));
        System.out.println(parkingLotStart);
        System.out.println(parkingLotEnd);

        assertTrue(parkingLotStart.equals(parkingLotEnd));
    }

    @Test
    public void rearrangeInFewerStepsTestWithCircuitTest() {
        Space space1 = new Space(1);
        Space space2 = new Space(2);
        Space space3 = new Space(3);
        Space space4 = new Space(4);
        Space space5 = new Space(5);
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        Car car3 = new Car(3);
        Car car4 = new Car(4);

        HashMap<Space, Car> startHashMap = new HashMap<>();
        startHashMap.put(space1, Car.noCar);
        startHashMap.put(space2, car1);
        startHashMap.put(space3, car2);
        startHashMap.put(space4, car3);
        startHashMap.put(space5, car4);
        ParkingLot parkingLotStart = new ParkingLot(startHashMap);

        HashMap<Space, Car> endHashMap = new HashMap<>();
        endHashMap.put(space1, Car.noCar);
        endHashMap.put(space2, car2);
        endHashMap.put(space3, car3);
        endHashMap.put(space4, car4);
        endHashMap.put(space5, car1);
        ParkingLot parkingLotEnd = new ParkingLot(endHashMap);

        System.out.println(parkingLotStart);
        System.out.println(parkingLotStart.rearrangeInFewerSteps(parkingLotEnd));
        System.out.println(parkingLotStart);
        System.out.println(parkingLotEnd);

        assertTrue(parkingLotStart.equals(parkingLotEnd));
    }

    @Test
    public void rearrangeInFewerStepsTestWithCircuitAnd2ConnectedComponentsTest() {
        Space space1 = new Space(1);
        Space space2 = new Space(2);
        Space space3 = new Space(3);
        Space space4 = new Space(4);
        Space space5 = new Space(5);
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        Car car3 = new Car(3);
        Car car4 = new Car(4);

        HashMap<Space, Car> startHashMap = new HashMap<>();
        startHashMap.put(space1, Car.noCar);
        startHashMap.put(space2, car1);
        startHashMap.put(space3, car2);
        startHashMap.put(space4, car3);
        startHashMap.put(space5, car4);
        ParkingLot parkingLotStart = new ParkingLot(startHashMap);

        HashMap<Space, Car> endHashMap = new HashMap<>();
        endHashMap.put(space1, Car.noCar);
        endHashMap.put(space2, car3);
        endHashMap.put(space3, car2);
        endHashMap.put(space4, car4);
        endHashMap.put(space5, car1);
        ParkingLot parkingLotEnd = new ParkingLot(endHashMap);

        System.out.println(parkingLotStart);
        System.out.println(parkingLotStart.rearrangeInFewerSteps(parkingLotEnd));
        System.out.println(parkingLotStart);
        System.out.println(parkingLotEnd);

        assertTrue(parkingLotStart.equals(parkingLotEnd));
    }

    @Test
    public void allRearrangementsTest() {
        Space space1 = new Space(1);
        Space space2 = new Space(2);
        Space space3 = new Space(3);
        Space space4 = new Space(4);
        Car car1 = new Car(1);
        Car car2 = new Car(2);
        Car car3 = new Car(3);

        HashMap<Space, Car> startHashMap = new HashMap<>();
        startHashMap.put(space1, car1);
        startHashMap.put(space2, car2);
        startHashMap.put(space4, car3);
        startHashMap.put(space3, Car.noCar);
        ParkingLot parkingLotStart = new ParkingLot(startHashMap);

        HashMap<Space, Car> endHashMap = new HashMap<>();
        endHashMap.put(space2, car1);
        endHashMap.put(space3, car2);
        endHashMap.put(space1, car3);
        endHashMap.put(space4, Car.noCar);
        ParkingLot parkingLotEnd = new ParkingLot(endHashMap);

        List<List<CarMove>> allMoves = parkingLotStart.getAllPossibleRearrangements(parkingLotEnd);

        System.out.println(allMoves.size());
        for (List<CarMove> carMoves : allMoves) {
            if(carMoves.size() == 3) {
                System.out.println("YEEEAH, sequence of moves with the least amount if moves is also here !");
            }
        }
        //GIRLS, IT WOOOOOOOOOOOOOOOOOOOOOOOOORKS !!
        for (List<CarMove> carMoves : allMoves) {
            parkingLotStart = new ParkingLot(startHashMap);
            parkingLotStart.makeMoves(carMoves);
            assertEquals(parkingLotEnd, parkingLotStart);
        }
    }
}