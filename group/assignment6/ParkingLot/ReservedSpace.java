package assignment6.ParkingLot;

import java.util.Objects;
import java.util.Set;

/**
 * Represents a space with an ID that has a set of cars which it is reserved for.
 */
public class ReservedSpace extends Space{
  final private Set<Car> reservedFor;

  public ReservedSpace(int id, Set<Car> reservedFor) {
    super(id);
    this.reservedFor = reservedFor;
  }

  public Set<Car> getReservedFor() {
    return reservedFor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReservedSpace space = (ReservedSpace) o;
    return this.getId() == space.getId()
        && Objects.equals(reservedFor, space.getReservedFor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), reservedFor);
  }
}
