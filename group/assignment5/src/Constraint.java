/**
 * This class represents an order-relation constraint between two Comparable objects from the same type.
 * 'from' is the vertex from which we can go to the second one, 'to'.
 * @param <T>
 */
public class Constraint<T extends Comparable<T>> {
    public T from;
    public T to;

    public Constraint(T from, T to) {
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "Value " + to + " cannot be ordered before " + from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Constraint<?> that = (Constraint<?>) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        return to != null ? to.equals(that.to) : that.to == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
