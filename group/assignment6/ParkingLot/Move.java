package assignment6.ParkingLot;

import java.util.Objects;

/**
 * Represents a move of a car.
 */
public class Move {
  private final Car car;
  
  public Move(Car car) {
    this.car = car;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o == null || !(o instanceof Move)) {
      return false;
    }
    Move move = (Move) o;
    return this.car.equals(move.car);
  }
  
  @Override
  public int hashCode() {
      return Objects.hash(car);
  }

  @Override
  public String toString() {
    return "Move " + this.car;
  }
}