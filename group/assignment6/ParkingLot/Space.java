package assignment6.ParkingLot;

import java.util.Objects;
import java.util.Set;

public class Space {
    private final int id;
    private final Set<Car> reservedFor;

    public Set<Car> getReservedFor() {
        return reservedFor;
    }

    public Space(int id, Set<Car> reservedFor) {
        this.id = id;
        this.reservedFor = reservedFor;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Space " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return id == space.id &&
                Objects.equals(reservedFor, space.reservedFor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservedFor);
    }
}
