package model.farm;

import model.Position;

// TODO this is very similar to farmer
public class DemolishMarker {
    private Position position;

    public DemolishMarker(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
