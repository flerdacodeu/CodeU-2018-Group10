package assignment6.ParkingLot;

import java.util.Objects;

public class CarMove {
    private Car car;
    private Space from;
    private Space to;


    public CarMove(Car car, Space from, Space to) {
        this.car = car;
        this.from = from;
        this.to = to;
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
