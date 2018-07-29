package assignment6;

import java.util.Objects;

public  class Space {
    private char spaceValue;
    private SpaceState state;

    public Space() {
        this.state = SpaceState.EMPTY;
    }

    public Space(char spaceValue) {
        this.spaceValue = spaceValue;
        this.state = SpaceState.NOT_EMPTY;
    }

    public Space(char spaceValue, SpaceState state) {
        this.spaceValue = spaceValue;
        this.state = state;
    }

    public char getSpaceValue() {
        return spaceValue;
    }

    public void setSpaceValue(char spaceValue) {
        this.spaceValue = spaceValue;
    }

    public SpaceState getState() {
        return state;
    }

    public void setState(SpaceState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return spaceValue == space.spaceValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spaceValue);
    }
}