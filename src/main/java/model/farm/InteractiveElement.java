package model.farm;

import model.Position;

// TODO maybe a better name would be "Building" or "FixedEntity" or something
// but that's not something important to decide now
public abstract class InteractiveElement {
    protected final Position topLeft;

    protected InteractiveElement(Position topLeft) {
        this.topLeft = topLeft;
    }

    public abstract boolean isTraversable(Position position);
}
