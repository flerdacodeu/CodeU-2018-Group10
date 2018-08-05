package assignment6.ParkingLot;

import assignment6.BiMap.BiMap;
import assignment6.Graph.Graph;

import java.util.*;

public class ParkingLotWithReserved {

    private final BiMap<ReservedSpace,Car> carSpaceBiMap;
    private Graph<ParkingLotWithReserved> parkingOrderPossible;


    public ParkingLotWithReserved(HashMap<ReservedSpace,Car> spaceToCar) {
        carSpaceBiMap = new BiMap<>(spaceToCar);
    }
    public ParkingLotWithReserved(ParkingLotWithReserved other) {
        carSpaceBiMap = new BiMap<>(other.getSpaceByCarMap());
    }
    public List<CarMove> rearrange(ParkingLotWithReserved end)
    {
        if (this.equals(end)){
            return new ArrayList<>();
        }

        Set<Car> firstMoveOptions = this.getEmptySpace().getReservedFor();
        Set<ParkingLotWithReserved> beenThereParkingLots = new HashSet<>();
        beenThereParkingLots.add(this);

        for(Car car : firstMoveOptions)
        {
            ArrayList<CarMove> carMoves = new ArrayList<>();
            ParkingLotWithReserved afterMovePL = new ParkingLotWithReserved(this.getSpaceByCarMap());
            afterMovePL.moveCarToEmptySpace(car, null);

            carMoves.add(new CarMove(car, getSpaceByCar(car),this.getEmptySpace()));

            if(afterMovePL.equals(end)) {
                return carMoves;
            }

            List<CarMove> moves = rearrangeUtil(afterMovePL, end, carMoves, beenThereParkingLots);
            if(moves != null) {
                return moves;
            }
        }
        return null;
    }
    private List<CarMove> rearrangeUtil(ParkingLotWithReserved current, ParkingLotWithReserved end,
                                        List<CarMove> previousMoves,
                                        Set<ParkingLotWithReserved> previousPL) {
        Set<Car> moveOptions = current.getEmptySpace().getReservedFor();
        for(Car car : moveOptions)
        {
            ParkingLotWithReserved afterMovePL = new ParkingLotWithReserved(current.getSpaceByCarMap());
            afterMovePL.moveCarToEmptySpace(car, null);

            ArrayList<CarMove> currentMoves = new ArrayList<>(previousMoves);
            if( previousPL.contains(afterMovePL)) {     // so we wont get inside a circle
                continue;
            }
            else {
                currentMoves.add(new CarMove(car, current.getSpaceByCar(car),current.getEmptySpace()));
            }

            if(afterMovePL.equals(end)) {
                return currentMoves;
            }

            Set<ParkingLotWithReserved> currentPL = new HashSet<>(previousPL);
            currentPL.add(afterMovePL);

            List<CarMove> moves = rearrangeUtil(afterMovePL, end, currentMoves, currentPL);
            if(moves != null) {
                return moves;
            }
        }
        return null;
    }

    int counterRecur;
    int counterEdges;
    int counterLoop;
    public void createAllParkingLotOrderConfigurationsGraph(ParkingLotWithReserved PLOrderConfig)
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
    private void createAllParkingLotOrderConfigurationsGraphUtil(ParkingLotWithReserved PLOrderConfig) {
        counterRecur++;
        Set<Car> firstMoveOptions = PLOrderConfig.getEmptySpace().getReservedFor();
        for(Car car : firstMoveOptions)
        {
            counterLoop++;
            ParkingLotWithReserved afterMovePL = new ParkingLotWithReserved(PLOrderConfig.getSpaceByCarMap());
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


    private void moveCarToEmptySpace(Car car, List<CarMove> carMoveList) {
        ReservedSpace toBeFilledEmptySpace = getEmptySpace();
        ReservedSpace toBeEmptyFilledSpace = getSpaceByCar(car);

        CarMove carMove = makeMove(car,toBeEmptyFilledSpace,toBeFilledEmptySpace);
        if(carMoveList != null)
        {
            carMoveList.add(carMove);
        }
        makeMove(Car.noCar,null, toBeEmptyFilledSpace);
    }

    private CarMove makeMove(Car car, ReservedSpace from, ReservedSpace to) {
        carSpaceBiMap.put(to, car);
        if (!car.equals(Car.noCar)) {
            return new CarMove(car,from,to);
        }
        return null;
    }


    public static boolean areMovesCreateEndResult(ParkingLotWithReserved start,
                                           ParkingLotWithReserved end,
                                           List<CarMove> carMoves) {
        ParkingLotWithReserved startCopy = new ParkingLotWithReserved(start);
        for(CarMove carMove : carMoves)
        {
            Car car = carMove.getCar();
            startCopy.moveCarToEmptySpace(car,null);
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
        ParkingLotWithReserved that = (ParkingLotWithReserved) o;
        return Objects.equals(carSpaceBiMap, that.carSpaceBiMap);
    }

    @Override
    public int hashCode() {

        return Objects.hash(carSpaceBiMap);
    }
}
