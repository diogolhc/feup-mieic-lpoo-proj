package model.farm.building;

import model.Position;

public abstract class Building {
    private final Position topLeft;

    public Building(Position topLeft) {
        this.topLeft = topLeft;
    }

    public Position getTopLeftPosition() {
        return this.topLeft;
    }

    // TODO another possibility would be to return a Region (i.e. getTraversableRegion and getInteractiveRegion)
    //      that would have a contains() method.
    //      this would allow to reduce logic duplication in the future because of most regions being rectangles
    public abstract boolean isTraversable(Position position);
    public abstract boolean isInInteractiveZone(Position position);
}
