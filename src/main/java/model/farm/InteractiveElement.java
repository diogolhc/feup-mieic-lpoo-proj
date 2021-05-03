package model.farm;

import model.Position;

public abstract class InteractiveElement {
    protected final Position topLeft;

    protected InteractiveElement(Position topLeft) {
        this.topLeft = topLeft;
    }

    public abstract int getSize();
    public abstract boolean isTraversable(Position position);
}
