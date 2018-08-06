package assignment6.ParkingLot;

import java.util.Objects;

/**
 * Represents a car with an ID.
 */
public class Car {
  private final int id;

  public Car(int carNumber) {
    this.id = carNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Car car = (Car) o;
    return id == car.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return this == noCar ? "No car" : "Car " + id;
  }

  public static Car noCar = new Car(0);
}
