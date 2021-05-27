package model.region;

import model.Position;

public class PositionRegion implements Region {
    private final Position position;

    public PositionRegion(Position position) {
        this.position = position;
    }

    @Override
    public boolean contains(Position position) {
        return this.position.equals(position);
    }
}
