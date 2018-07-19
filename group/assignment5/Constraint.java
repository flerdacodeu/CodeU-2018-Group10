package assignment5;

/**
 * This class represents a constraint made up of two vertecies of a graph.
 * First is the vertex from which we can go to the second one.
 * @param <T>
 */
public class Constraint<T extends Comparable<T>> {
    public T first;
    public T second;

    public Constraint(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return "Character " + second + " cannot be ordered before " + first;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Constraint<?> that = (Constraint<?>) o;

        if (first != null ? !first.equals(that.first) : that.first != null) return false;
        return second != null ? second.equals(that.second) : that.second == null;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
