package assignment6Package;

import org.junit.Test;

import java.util.HashMap;

public class ParkingLotTest {

    @Test
    public void rearrangeParkingLotExample()
    {
        ParkingLot.Space space1 = new ParkingLot.Space(1);
        ParkingLot.Space space2 = new ParkingLot.Space(2);
        ParkingLot.Space space3 = new ParkingLot.Space(3);
        ParkingLot.Space space4 = new ParkingLot.Space(4);
        ParkingLot.Car car1 = new ParkingLot.Car(1);
        ParkingLot.Car car2 = new ParkingLot.Car(2);
        ParkingLot.Car car3 = new ParkingLot.Car(3);

        HashMap<ParkingLot.Space, ParkingLot.Car> startHashMap = new HashMap();
        startHashMap.put(space1,car1);
        startHashMap.put(space2,car2);
        startHashMap.put(space4,car3);
        startHashMap.put(space3, ParkingLot.Car.noCar);
        ParkingLot parkingLotStart = new ParkingLot(startHashMap);

        HashMap<ParkingLot.Space, ParkingLot.Car> endHashMap = new HashMap();
        endHashMap.put(space2,car1);
        endHashMap.put(space3,car2);
        endHashMap.put(space1,car3);
        endHashMap.put(space4,ParkingLot.Car.noCar);
        ParkingLot parkingLotEnd = new ParkingLot(endHashMap);

        System.out.println(parkingLotStart);
        parkingLotStart.rearrangeParkingLot(parkingLotEnd);
        System.out.println(parkingLotStart);
    }

    @Test
    public void rearrangeParkingLotExampleReversed()
    {
        ParkingLot.Space space1 = new ParkingLot.Space(1);
        ParkingLot.Space space2 = new ParkingLot.Space(2);
        ParkingLot.Space space3 = new ParkingLot.Space(3);
        ParkingLot.Space space4 = new ParkingLot.Space(4);
        ParkingLot.Car car1 = new ParkingLot.Car(1);
        ParkingLot.Car car2 = new ParkingLot.Car(2);
        ParkingLot.Car car3 = new ParkingLot.Car(3);

        HashMap<ParkingLot.Space, ParkingLot.Car> endHashMap = new HashMap();
        endHashMap.put(space1,car1);
        endHashMap.put(space2,car2);
        endHashMap.put(space4,car3);
        endHashMap.put(space3, ParkingLot.Car.noCar);
        ParkingLot parkingLotEnd = new ParkingLot(endHashMap);

        HashMap<ParkingLot.Space, ParkingLot.Car> startHashMap = new HashMap();
        startHashMap.put(space2,car1);
        startHashMap.put(space3,car2);
        startHashMap.put(space1,car3);
        startHashMap.put(space4,ParkingLot.Car.noCar);
        ParkingLot parkingLotStart = new ParkingLot(startHashMap);

        System.out.println(parkingLotStart);
        parkingLotStart.rearrangeParkingLot(parkingLotEnd);
        System.out.println(parkingLotStart);
    }
}