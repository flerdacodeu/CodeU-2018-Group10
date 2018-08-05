package assignment6.ParkingLot;

import assignment6.BiMap.BiMap;

import java.util.*;

// ============================== Solution to challenge 3 ==============================
public class ParkingLotWithReserved {

    private BiMap<ReservedSpace,Car> carSpaceBiMap;

    /**
   * @param spaceToCar - need to have more than one space,
   *                   one and only one the spaces should contain Car.noCar,
   *                   all cars should be in spaces reserved for them
   */
    public ParkingLotWithReserved(HashMap<ReservedSpace,Car> spaceToCar){
    if(isValidSpaceCarMap(spaceToCar)) {
      carSpaceBiMap = new BiMap<>(spaceToCar);
    }
  }

    /**
    * @return true if the parking lot given is valid according to the class specification above
    */
    private boolean isValidSpaceCarMap(HashMap<ReservedSpace,Car> spaceToCar) {
    if(spaceToCar == null || spaceToCar.size() < 2){
      throw new IllegalArgumentException("Invalid input - To little parking spaces. Need to have a least 2");
    }

    boolean isEmptySpaceFound = false;
    for(ReservedSpace space: spaceToCar.keySet()) {
        Car carInCurrentSpace = spaceToCar.get(space);
        if(carInCurrentSpace.equals(Car.noCar)) {
          if(!isEmptySpaceFound){
            isEmptySpaceFound = true;
          }
          else {
            throw new IllegalArgumentException("Invalid input - More than one empty space");
          }
        }
        else if(!space.getReservedFor().contains(carInCurrentSpace)){
          throw new IllegalArgumentException("Invalid input - One of the cars is in a space isn't resrved for it"); //TODO: English already!
        }
      }

      if(!isEmptySpaceFound){
        throw new IllegalArgumentException("Invalid input - No empty space is found");
      }
      return true;
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
          afterMovePL.moveCarToEmptySpace(car);

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
        afterMovePL.moveCarToEmptySpace(car);

        ArrayList<CarMove> currentMoves = new ArrayList<>(previousMoves);
        if( previousPL.contains(afterMovePL)) {     // so we wont get inside a circle
          continue;
        } else {
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


    private void moveCarToEmptySpace(Car car) {
      ReservedSpace toBeFilledEmptySpace = getEmptySpace();
      ReservedSpace toBeEmptyFilledSpace = getSpaceByCar(car);

      makeMove(car,toBeFilledEmptySpace);
      makeMove(Car.noCar, toBeEmptyFilledSpace);
    }

    private void makeMove(Car car, ReservedSpace to) {
      carSpaceBiMap.put(to, car);
    }


    public static boolean areMovesCreateEndResult(ParkingLotWithReserved start,
                                           ParkingLotWithReserved end,
                                           List<CarMove> carMoves) {
      ParkingLotWithReserved startCopy = new ParkingLotWithReserved(start);
      for(CarMove carMove : carMoves) {
        Car car = carMove.getCar();
        startCopy.moveCarToEmptySpace(car);
      }
      return startCopy.equals(end);
    }

    private ReservedSpace getEmptySpace() {
      return getSpaceByCar(Car.noCar);
    }
    private ReservedSpace getSpaceByCar(Car car) {
      return carSpaceBiMap.getKey(car);
    }

    private HashMap<ReservedSpace,Car> getSpaceByCarMap() {
      return carSpaceBiMap.getKeyToValueMap();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()){
        return false;
      }
      ParkingLotWithReserved other = (ParkingLotWithReserved) o;
      return Objects.equals(carSpaceBiMap, other.carSpaceBiMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carSpaceBiMap);
    }
}
