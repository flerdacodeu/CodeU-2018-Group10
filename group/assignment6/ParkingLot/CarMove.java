package assignment6.ParkingLot;

import java.util.Objects;

/**
 * Represents a move from a space to another by a car.
 */
public class CarMove {
  private final Car car;
  private final Space fromSpace;
  private final Space toSpace;

  public CarMove(Car car, Space fromSpace, Space toSpace) {
    this.car = car;
    this.fromSpace = fromSpace;
    this.toSpace = toSpace;
  }

  public Car getCar() {
    return car;
  }

  public Space getFrom() {
    return fromSpace;
  }

  public Space getTo() {
    return toSpace;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CarMove carMove = (CarMove) o;
    return Objects.equals(car, carMove.car)
        && Objects.equals(fromSpace, carMove.fromSpace)
        && Objects.equals(toSpace, carMove.toSpace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(car, fromSpace, toSpace);
  }

  @Override
  public String toString() {
    return "\nMove " + car +  " from " + fromSpace + " to " + toSpace;
  }
}
