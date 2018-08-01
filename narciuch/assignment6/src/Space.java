public class Space implements Comparable {
  private final char id;

  public Space(char id) {
    this.id = id;
  }

  public char getId() {
    return id;
  }

  @Override
  public String toString() {
    return "" + id;
  }

  @Override
  public int compareTo(Object o) {
    if (this == o) return 0;
    if (o == null || getClass() != o.getClass()) return 1;

    Space space = (Space) o;

    return id < space.id ? -1 : 1;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Space space = (Space) o;

    return id == space.id;
  }

  @Override
  public int hashCode() {
    return (int) id;
  }


}
