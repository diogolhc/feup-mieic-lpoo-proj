package model.farm;

import model.Position;

import java.io.Serializable;

public class Farmer implements Serializable {
    private Position position;

    public Farmer(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
