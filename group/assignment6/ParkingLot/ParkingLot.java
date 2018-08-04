package assignment6Package.ParkingLot;

import assignment6Package.BiMap.BiMap;

import java.util.*; //TODO: DO NOT USE "*"

public class ParkingLot implements Comparable<ParkingLot> {

    BiMap<Space,Car> carSpaceBiMap;

    public ParkingLot(HashMap<Space,Car> spaceToCar)
    {
        carSpaceBiMap = new BiMap<>(spaceToCar);
    }

    public Space getEmptySpace()
    {
        return getSpaceByCar(Car.noCar);
    }

    private void swapCarsSpacesThroghEmptySpace(Car car1, Car car2, List<CarMove> carMoveList)  //TODO: SPELLING
    {
        Space car1Space = getSpaceByCar(car1);
        Space car2Space = getSpaceByCar(car2);
        Space emptySpace = getEmptySpace();

        carMoveList.add(makeAMove(car1,car1Space,emptySpace));
        carMoveList.add(makeAMove(car2,car2Space,car1Space));
        carMoveList.add(makeAMove(car1,emptySpace,car2Space));
        makeAMove(Car.noCar,null, emptySpace);
    }

    private CarMove makeAMove(Car car, Space from, Space to)
    {
        carSpaceBiMap.put(to, car);
        if(!car.equals(Car.noCar))
        {
            return new CarMove(car,from,to);
        }
        return null;
    }

    public void moveCarToEmptySpace(Car car, List<CarMove> carMoveList)  //TODO: SPELLING
    {
        Space toBeFilledEmptySpace = getEmptySpace();
        Space toBeEmptyFilledSpace = getSpaceByCar(car);

        carMoveList.add(makeAMove(car,toBeEmptyFilledSpace,toBeFilledEmptySpace));
        makeAMove(Car.noCar,null, toBeEmptyFilledSpace);
    }
    public void moveCarToEmptySpace(Car car)  //TODO: SPELLING
    {
        Space toBeFilledEmptySpace = getEmptySpace();
        Space toBeEmptyFilledSpace = getSpaceByCar(car);

        makeAMove(car,toBeEmptyFilledSpace,toBeFilledEmptySpace);
        makeAMove(Car.noCar,null, toBeEmptyFilledSpace);
    }

    public List<CarMove> rearrangeParkingLot(ParkingLot another) throws Exception {
        if(!isSameParkingLotDiffOrder(another))
        {
            throw new Exception("Not same parking lot in defferent order");
        }

        List<CarMove> carMoveList = new ArrayList<>();

        // for each space, the loop put the car that should be there, according to the "another" parking lot
        // In iteration i - there are i parking spaces that are in order according to "another" parking lot
        // ,so in iteration n we know for sure that all spaces are in order according to "another" parking lot
        // Because each iteration time efficiency is O(1), than n iterations are O(n)
        for(Space space : carSpaceBiMap.getKeysSet())
        {
            Car carToMove = another.getCarBySpace(space);
            Car carToRemove = this.getCarBySpace(space);

            if(carToMove.equals(carToRemove)) {
                continue;
            }
            else if(carToMove.equals(Car.noCar)) {
                moveCarToEmptySpace(carToRemove, carMoveList);
            }
            else if (carToRemove.equals(Car.noCar))
            {
                moveCarToEmptySpace(carToMove, carMoveList);
            }
            else {
                swapCarsSpacesThroghEmptySpace(carToMove, carToRemove, carMoveList);
            }
        }
        return carMoveList;
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

    public boolean isSameParkingLotDiffOrder(ParkingLot another)
    {
        return   another.carSpaceBiMap.getKeysSet().equals(carSpaceBiMap.getKeysSet()) &&
                 another.carSpaceBiMap.getValuesSet().equals(carSpaceBiMap.getValuesSet()) ;                                                                          //TODO: need to check equality also in cars
    }

    // returns 0 is exactly same parking lot at the same order
    @Override
    public int compareTo(ParkingLot pl) {
        if(pl.equals(this))
        {
            return 0;
        }
        return -1;
    }

    public HashMap<Space,Car> getSpaceByCarMap()
    {
         return carSpaceBiMap.getKeyToValueMap();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot that = (ParkingLot) o;
        return Objects.equals(carSpaceBiMap, that.carSpaceBiMap);
    }

    @Override
    public int hashCode() {

        return Objects.hash(carSpaceBiMap);
    }
}
