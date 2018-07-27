package assignment6Package;

import java.util.*; //TODO: DO NOT USE "*"

public class ParkingLot {

    BiMap<Space,Car> carSpaceBiMap;

    public ParkingLot(HashMap<Space,Car> spaceToCar)
    {
        carSpaceBiMap = new BiMap<>(spaceToCar);
    }

    private Space getEmptySpace()
    {
        return getSpaceByCar(Car.noCar);
    }

    private void swapCarsSpacesThroghEmptySpace(Car car1, Car car2)  //TODO: SPELLING
    {
        Space car1Space = getSpaceByCar(car1);
        Space car2Space = getSpaceByCar(car2);
        Space emptySpace = getEmptySpace();

        carSpaceBiMap.put(emptySpace, car1);
        carSpaceBiMap.put(car1Space, car2);
        carSpaceBiMap.put(car2Space, car1);

        System.out.println("move " + car1 +  " from " + car1Space + " to " + car2Space);
    }

    public void rearrangeParkingLot(ParkingLot another)
    {
        if(!another.carSpaceBiMap.getKeysSet().equals(carSpaceBiMap.getKeysSet()))  //TODO: need to check equality also in cars
        {
            //TODO: announce parking lot don't match in spaces
        }

        for(Space space : carSpaceBiMap.getKeysSet())
        {
            Car carToMove = another.getCarBySpace(space);
            Car carToRemove =  getCarBySpace(space);

            swapCarsSpacesThroghEmptySpace(carToMove, carToRemove);
        }
    }

    Car getCarBySpace(Space space) {
        return carSpaceBiMap.getValue(space);
    }

    Space getSpaceByCar(Car car) {
        return carSpaceBiMap.getKey(car);
    }

    @Override
    public String toString() {
        return carSpaceBiMap.toString();
    }

    public static class Car {
        private final int id;

        public Car(int carNumber) {
            this.id = carNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Car car = (Car) o;
            return id == car.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return this==noCar ? "NoCar" : "Car" + id;
        }

        static Car noCar = new Car(0);
    }

    public static class Space {
        private final int id;

        public Space(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Space space = (Space) o;
            return id == space.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Space " + id;
        }
    }
}
