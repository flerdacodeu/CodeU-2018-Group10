package assignment6;

public class CarMove {
    private int carNumber;
    private char from;
    private char to;

    public CarMove(int carNumber, char from, char to) {
        this.carNumber = carNumber;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Move car #" + carNumber + " from space " + from + " to space " + to + ".";
    }
}
