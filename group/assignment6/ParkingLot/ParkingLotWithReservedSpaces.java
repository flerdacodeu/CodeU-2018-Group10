package assignment6.ParkingLot;

import assignment6.BiMap.BiMap;

import java.util.*;

// ============================== Solution to challenge 3 ==============================
/**
 *  This class represent a parking lot with n>1 unique spaces, and n-1 unique cars.
 *  Spaces are reserved only for specified cars. 
 */
public class ParkingLotWithReservedSpaces {

  private BiMap<ReservedSpace,Car> carSpaceBiMap;

  /**
   * @param spaceToCar - This Map have few requirements: 
   *                       # Map size should be bigger that 1
   *                       # One and only one space should map to Car.noCar
   *                       # All cars should be in the reserved car list of the space they are in
   */
  public ParkingLotWithReservedSpaces(HashMap<ReservedSpace,Car> spaceToCar){
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
      afterMovePL.moveCarToEmptySpace(car);

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
    for (Car car : moveOptions) {
      ParkingLotWithReservedSpaces afterMovePL = new ParkingLotWithReservedSpaces(current.getSpaceByCarMap());
      afterMovePL.moveCarToEmptySpace(car);

      ArrayList<DetailedMove> currentMoves = new ArrayList<>(previousMoves);
      if (previousPL.contains(afterMovePL)) {     // so we wont get inside a circle
        continue;
      } else {
        currentMoves.add(new DetailedMove(car, current.getSpaceByCar(car),current.getEmptySpace()));
      }

      if (afterMovePL.equals(end)) {
        return currentMoves;
      }

      Set<ParkingLotWithReservedSpaces> currentPL = new HashSet<>(previousPL);
      currentPL.add(afterMovePL);

      List<DetailedMove> moves = rearrangeUtil(afterMovePL, end, currentMoves, currentPL);
      if (moves != null) {
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


  public static boolean areMovesCreateEndResult(ParkingLotWithReservedSpaces start,
      ParkingLotWithReservedSpaces end,
      List<DetailedMove> carMoves) {
    ParkingLotWithReservedSpaces startCopy = new ParkingLotWithReservedSpaces(start);
    for (DetailedMove carMove : carMoves) {
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
    ParkingLotWithReservedSpaces other = (ParkingLotWithReservedSpaces) o;
    return Objects.equals(carSpaceBiMap, other.carSpaceBiMap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(carSpaceBiMap);
  }
}