package assignment6.ParkingLot;

import assignment6.BiMap.BiMap;
import assignment6.Graph.Graph;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class ParkingLotWithReservedSpaces {

    private final BiMap<ReservedSpace,Car> carSpaceBiMap;
    private Graph<ParkingLotWithReservedSpaces> parkingOrderPossible;


    public ParkingLotWithReservedSpaces(HashMap<ReservedSpace,Car> spaceToCar) {
        carSpaceBiMap = new BiMap<>(spaceToCar);
    }
    public ParkingLotWithReservedSpaces(ParkingLotWithReservedSpaces other) {
        carSpaceBiMap = new BiMap<>(other.getSpaceByCarMap());
    }
    public List<DetailedMove> rearrange(ParkingLotWithReservedSpaces end)
    {
        if (this.equals(end)){
            return new ArrayList<>();
        }

        Set<Car> firstMoveOptions = this.getEmptySpace().getReservedFor();
        Set<ParkingLotWithReservedSpaces> beenThereParkingLots = new HashSet<>();
        beenThereParkingLots.add(this);

        for(Car car : firstMoveOptions)
        {
            ArrayList<DetailedMove> carMoves = new ArrayList<>();
            ParkingLotWithReservedSpaces afterMovePL = new ParkingLotWithReservedSpaces(this.getSpaceByCarMap());
            afterMovePL.moveCarToEmptySpace(car, null);

            carMoves.add(new DetailedMove(car, getSpaceByCar(car),this.getEmptySpace()));

            if(afterMovePL.equals(end)) {
                return carMoves;
            }

            List<DetailedMove> moves = rearrangeUtil(afterMovePL, end, carMoves, beenThereParkingLots);
            if(moves != null) {
                return moves;
            }
        }
        return null;
    }
    private List<DetailedMove> rearrangeUtil(ParkingLotWithReservedSpaces current,
                                            ParkingLotWithReservedSpaces end,
                                                List<DetailedMove> previousMoves,
                                                    Set<ParkingLotWithReservedSpaces> previousPL) {
        Set<Car> moveOptions = current.getEmptySpace().getReservedFor();
        for(Car car : moveOptions)
        {
            ParkingLotWithReservedSpaces afterMovePL = new ParkingLotWithReservedSpaces(current.getSpaceByCarMap());
            afterMovePL.moveCarToEmptySpace(car, null);

            ArrayList<DetailedMove> currentMoves = new ArrayList<>(previousMoves);
            if( previousPL.contains(afterMovePL)) {     // so we wont get inside a circle
                continue;
            }
            else {
                currentMoves.add(new DetailedMove(car, current.getSpaceByCar(car),current.getEmptySpace()));
            }

            if(afterMovePL.equals(end)) {
                return currentMoves;
            }

            Set<ParkingLotWithReservedSpaces> currentPL = new HashSet<>(previousPL);
            currentPL.add(afterMovePL);

            List<DetailedMove> moves = rearrangeUtil(afterMovePL, end, currentMoves, currentPL);
            if(moves != null) {
                return moves;
            }
        }
        return null;
    }

    int counterRecur;
    int counterEdges;
    int counterLoop;
    public void createAllParkingLotOrderConfigurationsGraph(ParkingLotWithReservedSpaces PLOrderConfig)
    {
        if (parkingOrderPossible == null)
        {
            parkingOrderPossible = new Graph<>();
            createAllParkingLotOrderConfigurationsGraphUtil(PLOrderConfig);

            System.out.println("\n counterRecur:"+counterRecur);
            System.out.println("\n counterEdges"+counterEdges);
            System.out.println("\n counterLoop"+counterLoop);
        }
    }
    private void createAllParkingLotOrderConfigurationsGraphUtil(ParkingLotWithReservedSpaces PLOrderConfig) {
        counterRecur++;
        Set<Car> firstMoveOptions = PLOrderConfig.getEmptySpace().getReservedFor();
        for(Car car : firstMoveOptions)
        {
            counterLoop++;
            ParkingLotWithReservedSpaces afterMovePL = new ParkingLotWithReservedSpaces(PLOrderConfig.getSpaceByCarMap());
            afterMovePL.moveCarToEmptySpace(car, null);

            boolean isGraphContainsConfiguration = parkingOrderPossible.containsVertex(afterMovePL);

            if(parkingOrderPossible.addEdge(PLOrderConfig, afterMovePL))
            {
                counterEdges++;
            }
            if(!isGraphContainsConfiguration)
            {
                createAllParkingLotOrderConfigurationsGraphUtil(afterMovePL);
            }
        }
    }


    private void moveCarToEmptySpace(Car car, List<DetailedMove> carMoveList) {
        ReservedSpace toBeFilledEmptySpace = getEmptySpace();
        ReservedSpace toBeEmptyFilledSpace = getSpaceByCar(car);

        DetailedMove carMove = makeMove(car,toBeEmptyFilledSpace,toBeFilledEmptySpace);
        if(carMoveList != null)
        {
            carMoveList.add(carMove);
        }
        makeMove(Car.noCar,null, toBeEmptyFilledSpace);
    }

    private DetailedMove makeMove(Car car, ReservedSpace from, ReservedSpace to) {
        carSpaceBiMap.put(to, car);
        if (!car.equals(Car.noCar)) {
            return new DetailedMove(car,from,to);
        }
        return null;
    }


    public static boolean areMovesCreateEndResult(ParkingLotWithReservedSpaces start,
                                                  ParkingLotWithReservedSpaces end,
                                                  List<DetailedMove> carMoves) {
        ParkingLotWithReservedSpaces startCopy = new ParkingLotWithReservedSpaces(start);
        if (carMoves != null) {
            for(DetailedMove carMove : carMoves) {
                Car car = carMove.getCar();
                startCopy.moveCarToEmptySpace(car,null);
            }
        }
        return startCopy.equals(end);
    }

    // getters & setters

    public ReservedSpace getEmptySpace() {
        return getSpaceByCar(Car.noCar);
    }
    private ReservedSpace getSpaceByCar(Car car) {
        return carSpaceBiMap.getKey(car);
    }
    public HashMap<ReservedSpace,Car> getSpaceByCarMap() {
        return carSpaceBiMap.getKeyToValueMap();
    }


    // Override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLotWithReservedSpaces that = (ParkingLotWithReservedSpaces) o;
        return Objects.equals(carSpaceBiMap, that.carSpaceBiMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carSpaceBiMap);
    }
}
