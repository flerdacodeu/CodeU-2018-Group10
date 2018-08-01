import java.util.HashMap;
import java.util.Set;

public class ParkingLot {
  private BiMap<Space, Car> parkingLot;

  public ParkingLot() {
    parkingLot = new BiMap(new HashMap<>());
  }

  public void addSpaceCarPairById(char spaceId, int carId) {
    parkingLot.put(new Space(spaceId), new Car(carId));
  }

  public void addSpaceCarPair(Space space, Car car) {
    parkingLot.put(space, car);
  }

  public ParkingLot(BiMap<Space, Car> parkingLot) {
    this.parkingLot = parkingLot;
  }

  public BiMap<Space, Car> getParkingLot() {
    return parkingLot;
  }

  public Space getEmptySpace() {
    return getSpaceForCar(Car.noCar);
  }

  public Set<Space> getSpaces() {
    return parkingLot.getKeysSet();
  }

  public Set<Car> getCars() {
    return parkingLot.getValuesSet();
  }

  public Car getCarInSpace(Space space) {
    return parkingLot.getValue(space);
  }

  public Space getSpaceForCar(Car car) {
    return parkingLot.getKey(car);
  }

  public void rearrangeParkingLot(ParkingLot initialState, ParkingLot finalState) {
    ParkingLot currentState = ParkingLot.copyParkingLot(initialState);

    for (Space space : initialState.getSpaces()) {
      System.out.println(currentState);
      Car carInSpace = currentState.getCarInSpace(space);
      Car carWhichShouldBeInSpace = finalState.getCarInSpace(space);
      Space whereCarIsNow = currentState.getSpaceForCar(carWhichShouldBeInSpace);
      Space emptySpace = currentState.getEmptySpace();
      currentState.move(carInSpace, space, emptySpace);
      currentState.move(carWhichShouldBeInSpace, whereCarIsNow, space);
    }
  }

  public void move(Car car, Space from, Space to) {
    //TODO unneccessary info here. Check if to is empty?
    System.out.println("Move car " + car + " from " + from + " to " + to);
    Car otherCar = getCarInSpace(to);
    parkingLot.put(from, otherCar);
    parkingLot.put(to, car);
  }

  public static ParkingLot copyParkingLot(ParkingLot parkingLot) {
    HashMap<Space,Car>  newParkingHashMap = new HashMap<>();
    for (Space space : parkingLot.getSpaces()) {
      Space newSpace = new Space(space.getId());
      Car newCar = new Car(parkingLot.getCarInSpace(space).getId());
      newParkingHashMap.put(newSpace, newCar);
    }
    BiMap<Space, Car> newParkingLot = new BiMap<>(newParkingHashMap);
    return new ParkingLot(newParkingLot);
  }

  @Override
  public String toString() {
    return "ParkingLot{" + parkingLot + '}';
  }
}
