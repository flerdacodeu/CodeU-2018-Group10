package assignment6;

import java.util.Objects;

public  class Car {
    private int carNumber;
    private RearrangeState state;

    public Car(int carNumber) {
        this.carNumber = carNumber;
        this.state = RearrangeState.NOT_REARRANGED;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public RearrangeState getState() {
        return state;
    }

    public void setState(RearrangeState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carNumber == car.carNumber;
    }

    @Override
    public int hashCode() {

        return Objects.hash(carNumber);
    }
}