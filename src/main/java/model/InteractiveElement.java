package model;

public abstract class InteractiveElement {
    protected final Position topLeft;

    protected InteractiveElement(Position topLeft) {
        this.topLeft = topLeft;
    }

    public abstract boolean isTraversable(Position position);
}
