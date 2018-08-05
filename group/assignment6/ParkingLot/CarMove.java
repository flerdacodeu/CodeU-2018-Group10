package assignment6.ParkingLot;

import java.util.Objects;

/**
 * This class represents a move of a car
 * in a Parking Lot. Move is represented by
 * a car itself, a space on which car is situated
 * and a space to which a car will be moved.
 */
public class CarMove {
    private final Car car;
    private final Space from;
    private final Space to;


    public CarMove(Car car, Space from, Space to) {
        this.car = car;
        this.from = from;
        this.to = to;
    }

    public Car getCar() {
        return car;
    }

    public Space getFrom() {
        return from;
    }

    public Space getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarMove carMove = (CarMove) o;
        return Objects.equals(car, carMove.car) &&
                Objects.equals(from, carMove.from) &&
                Objects.equals(to, carMove.to);
    }

    @Override
    public String toString() {
        return "\nmove " + car +  " from " + from + " to " + to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(car, from, to);
    }
}
