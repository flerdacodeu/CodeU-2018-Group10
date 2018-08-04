package assignment6.ParkingLot;

import assignment6.BiMap.BiMap;
import assignment6.ParkingLot.enums.RearrangeState;
import assignment6.ParkingLot.enums.SpaceState;

import java.util.*;


public class ParkingLot implements Comparable<ParkingLot> {

    private BiMap<Space,Car> carSpaceBiMap;

    public ParkingLot(HashMap<Space,Car> spaceToCar) {
        carSpaceBiMap = new BiMap<>(spaceToCar);
    }

    public List<CarMove> rearrangeParkingLot(ParkingLot another) throws IllegalArgumentException {
        if (!isSameParkingLotDiffOrder(another)) {
            throw new IllegalArgumentException("Not same parking lot in different order");
        }

        List<CarMove> carMoveList = new ArrayList<>();

        // for each space, the loop put the car that should be there, according to the "another" parking lot
        // In iteration i - there are i parking spaces that are in order according to "another" parking lot
        // ,so in iteration n we know for sure that all spaces are in order according to "another" parking lot
        // Because each iteration time efficiency is O(1), than n iterations are O(n)
        for (Space space : carSpaceBiMap.getKeysSet()) {
            Car carToMove = another.getCarBySpace(space);
            Car carToRemove = this.getCarBySpace(space);

            if (carToMove.equals(Car.noCar)) {
                moveCarToEmptySpace(carToRemove, carMoveList);
            }
            else if (carToRemove.equals(Car.noCar)) {
                moveCarToEmptySpace(carToMove, carMoveList);
            }
            else {
                swapCarsSpacesThroughEmptySpace(carToMove, carToRemove, carMoveList);
            }
        }
        return carMoveList;
    }

    public List<CarMove> rearrangeInFewerSteps(ParkingLot another) {
        List<CarMove> carMoves = new ArrayList<>();
        Map<Car, RearrangeState> carStateMap = new HashMap<>();
        Map<Space, SpaceState> spaceStateMap = new HashMap<>();
        initStateMaps(carStateMap, spaceStateMap, another);
        rearrangeInFewerStepsUtil(carMoves, another, carStateMap, spaceStateMap);
        return carMoves;
    }

    private void rearrangeInFewerStepsUtil(List<CarMove> carMoves, ParkingLot another,
                                           Map<Car, RearrangeState> carStateMap,
                                           Map<Space, SpaceState> spaceStateMap) {
        for (Car car : carSpaceBiMap.getValuesSet()) {
            if (!car.equals(Car.noCar) && carStateMap.get(car).equals(RearrangeState.NOT_REARRANGED)) {
                rearrangeInFewerStepsUtilRecursive(carMoves, car, another, carStateMap, spaceStateMap);
            }
        }
    }

    private void rearrangeInFewerStepsUtilRecursive(List<CarMove> carMoves, Car car, ParkingLot another, Map<Car,
            RearrangeState> carStateMap, Map<Space, SpaceState> spaceStateMap) {
        RearrangeState currentState = carStateMap.get(car);

        if (currentState.equals(RearrangeState.NOT_REARRANGED)) {
            carStateMap.put(car, RearrangeState.REARRANGING);
            Space desiredSpace = another.getCarSpaceBiMap().getKey(car);
            if (spaceStateMap.get(desiredSpace).equals(SpaceState.NOT_EMPTY)) {
                rearrangeInFewerStepsUtilRecursive(carMoves, carSpaceBiMap.getValue(desiredSpace), another,
                        carStateMap, spaceStateMap);
            }
            if (spaceStateMap.get(desiredSpace).equals(SpaceState.EMPTY)) {
                carMoves.add(new CarMove(car, carSpaceBiMap.getKey(car),
                        another.getCarSpaceBiMap().getKey(car)));
                updateCarSpaceStates(car, desiredSpace, true,  carStateMap, spaceStateMap);
                moveCarToEmptySpace(car);
            }
        }

        if (currentState.equals(RearrangeState.REARRANGING)) {
            Space emptySpace = getEmptySpace();
            carMoves.add(new CarMove(car, carSpaceBiMap.getKey(car), emptySpace));
            updateCarSpaceStates(car, emptySpace, false,  carStateMap, spaceStateMap);
            moveCarToEmptySpace(car);
        }
    }

    private void updateCarSpaceStates(Car car, Space emptySpace, boolean isCarRearranged,
                                      Map<Car, RearrangeState> carStateMap, Map<Space, SpaceState> spaceStateMap) {
        spaceStateMap.put(emptySpace, SpaceState.NOT_EMPTY);
        spaceStateMap.put(carSpaceBiMap.getKey(car), SpaceState.EMPTY);
        if (isCarRearranged) {
            carStateMap.put(car, RearrangeState.REARRANGED);
        } else {
            carStateMap.put(car, RearrangeState.NOT_REARRANGED);
        }
    }

    private void initStateMaps(Map<Car, RearrangeState> carStateMap, Map<Space, SpaceState> spaceStateMap, ParkingLot another) {
        carSpaceBiMap.getKeysSet()
                .forEach(e -> {
                    Car before = carSpaceBiMap.getValue(e);
                    Car after = another.getCarSpaceBiMap().getValue(e);
                    carStateMap.put(before, RearrangeState.NOT_REARRANGED);
                    spaceStateMap.put(e, SpaceState.NOT_EMPTY);
                    if(before != Car.noCar && after != Car.noCar) {
                        if (before.equals(after)) {
                            carStateMap.put(before, RearrangeState.REARRANGED);
                        }
                    }
                });
        spaceStateMap.put(getEmptySpace(), SpaceState.EMPTY);
    }

    private void swapCarsSpacesThroughEmptySpace(Car car1, Car car2, List<CarMove> carMoveList) {
        Space car1Space = getSpaceByCar(car1);
        Space car2Space = getSpaceByCar(car2);
        Space emptySpace = getEmptySpace();

        carMoveList.add(makeMove(car1,car1Space,emptySpace));
        carMoveList.add(makeMove(car2,car2Space,car1Space));
        carMoveList.add(makeMove(car1,emptySpace,car2Space));
        makeMove(Car.noCar,null, emptySpace);
    }

    private CarMove makeMove(Car car, Space from, Space to) {
        carSpaceBiMap.put(to, car);
        if (!car.equals(Car.noCar)) {
            return new CarMove(car,from,to);
        }
        return null;
    }

    private void moveCarToEmptySpace(Car car, List<CarMove> carMoveList) {
        Space toBeFilledEmptySpace = getEmptySpace();
        Space toBeEmptyFilledSpace = getSpaceByCar(car);

        carMoveList.add(makeMove(car,toBeEmptyFilledSpace,toBeFilledEmptySpace));
        makeMove(Car.noCar,null, toBeEmptyFilledSpace);
    }

    public void moveCarToEmptySpace(Car car) {
        Space toBeFilledEmptySpace = getEmptySpace();
        Space toBeEmptyFilledSpace = getSpaceByCar(car);

        makeMove(car,toBeEmptyFilledSpace,toBeFilledEmptySpace);
        makeMove(Car.noCar,null, toBeEmptyFilledSpace);
    }

    public Space getEmptySpace() {
        return getSpaceByCar(Car.noCar);
    }

    private Car getCarBySpace(Space space) {
        return carSpaceBiMap.getValue(space);
    }

    private Space getSpaceByCar(Car car) {
        return carSpaceBiMap.getKey(car);
    }

    private boolean isSameParkingLotDiffOrder(ParkingLot another) {
        return   another.carSpaceBiMap.getKeysSet().equals(carSpaceBiMap.getKeysSet()) &&
                 another.carSpaceBiMap.getValuesSet().equals(carSpaceBiMap.getValuesSet()) ;                                                                          //TODO: need to check equality also in cars
    }

    public BiMap<Space, Car> getCarSpaceBiMap() {
        return carSpaceBiMap;
    }

    public HashMap<Space,Car> getSpaceByCarMap() {
        return carSpaceBiMap.getKeyToValueMap();
    }

    @Override
    public String toString() {
        return carSpaceBiMap.toString();
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
