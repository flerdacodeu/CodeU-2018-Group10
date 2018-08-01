public class Car {
  private final int id;

  static Car noCar = new Car(0);

  public Car(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "" + id;
  }
}
