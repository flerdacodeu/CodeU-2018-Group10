package assignment6;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CarRearrangmentService {
    private BiMap<Space, Car> beforeMap;
    private BiMap<Space, Car> afterMap;

    public List<CarMove> rearrangeCars(HashMap<Space, Car> spaceToCarBefore, HashMap<Space, Car> spaceToCarAfter) {
        List<CarMove> carMoves = new ArrayList<>();
        beforeMap = new BiMap<>(spaceToCarBefore);
        afterMap = new BiMap<>(spaceToCarAfter);
        afterMap.getKeysSet()
                .forEach(e -> {
                    Car before = beforeMap.getValue(e);
                    Car after = afterMap.getValue(e);
                    if(before != null && after != null) {
                        if (before.getCarNumber() == after.getCarNumber()) {
                            beforeMap.getValue(e).setState(RearrangeState.REARRANGED);
                        }
                    }
                });
        rearrangeCarsUtil(carMoves);
        return carMoves;
    }

    private void rearrangeCarsUtil(List<CarMove> carMoves) {
        for (Car car : beforeMap.getValuesSet()) {
            if (car != null && car.getState().equals(RearrangeState.NOT_REARRANGED)) {
                rearrangeCarsRecursive(carMoves, car);
            }
        }
    }

    private void rearrangeCarsRecursive(List<CarMove> carMoves, Car car) {
        RearrangeState currentState = car.getState();

        if(currentState.equals(RearrangeState.NOT_REARRANGED)) {
            car.setState(RearrangeState.REARRANGING);
            Space desiredSpace = afterMap.getKey(car);
            Space space = beforeMap.getKey(beforeMap.getValue(desiredSpace));
            if(space.getState().equals(SpaceState.NOT_EMPTY)) {
                rearrangeCarsRecursive(carMoves, beforeMap.getValue(desiredSpace));
            }
            if(space.getState().equals(SpaceState.EMPTY)) {
                carMoves.add(new CarMove(car.getCarNumber(), beforeMap.getKey(car).getSpaceValue(),
                        afterMap.getKey(car).getSpaceValue()));
                car.setState(RearrangeState.REARRANGED);
                desiredSpace.setState(SpaceState.NOT_EMPTY);
                beforeMap.getKey(car).setState(SpaceState.EMPTY);
                beforeMap.put(desiredSpace, car);
            }
        }

        if(currentState.equals(RearrangeState.REARRANGING)) {
            Space emptySpace = findEmptySpace();
            emptySpace.setState(SpaceState.NOT_EMPTY);
            carMoves.add(new CarMove(car.getCarNumber(), beforeMap.getKey(car).getSpaceValue(),
                    emptySpace.getSpaceValue()));
            beforeMap.getKey(car).setState(SpaceState.EMPTY);
            beforeMap.put(emptySpace, car);
            car.setState(RearrangeState.NOT_REARRANGED);
        }
    }

    private Space findEmptySpace() {
        return beforeMap.getKeysSet().stream().filter(e -> e.getState().equals(SpaceState.EMPTY)).findFirst().get();
    }
}