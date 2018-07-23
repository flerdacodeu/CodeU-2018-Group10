/**
 * This class represents an order-relation constraint between two Comparable objects from the same type.
 * 'former' is the vertex from which we can go to the second one, 'latter'.
 */
public class Constraint<T> {
    private T former;
    private T latter;

    public Constraint(T former, T latter) {
        this.former = former;
        this.latter = latter;
    }

    public String toString() {
        return "Value " + latter + " cannot be ordered before " + former;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Constraint<?> that = (Constraint<?>) o;

        if (former != null ? !former.equals(that.former) : that.former != null) {
            return false;
        }
        return latter != null ? latter.equals(that.latter) : that.latter == null;
    }

    @Override
    public int hashCode() {
        int result = former != null ? former.hashCode() : 0;
        result = 31 * result + (latter != null ? latter.hashCode() : 0);
        return result;
    }
}
