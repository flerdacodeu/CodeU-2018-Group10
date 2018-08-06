package assignment6.ParkingLot;

import java.util.Objects;

/**
 * Represents a move from a space to another by a car.
 */
public class DetailedMove extends Move {
  private final Space fromSpace;
  private final Space toSpace;

  public DetailedMove(Car car, Space fromSpace, Space toSpace) {
    super(car);
    this.fromSpace = fromSpace;
    this.toSpace = toSpace;
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
    DetailedMove carMove = (DetailedMove) o;
    return Objects.equals(getCar(), carMove.getCar())
        && Objects.equals(fromSpace, carMove.fromSpace)
        && Objects.equals(toSpace, carMove.toSpace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCar(), fromSpace, toSpace);
  }

  @Override
  public String toString() {
    return "\nMove " + getCar() +  " from " + fromSpace + " to " + toSpace;
  }
}
