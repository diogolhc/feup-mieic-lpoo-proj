package model.farm;

import model.Position;

import java.io.Serializable;

// TODO maybe this could be unified with Farmer?
public class Animal implements Serializable {
    private Position position;

    public Animal(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
