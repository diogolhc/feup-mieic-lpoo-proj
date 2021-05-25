package model.farm;

import model.Position;

// TODO maybe this could be unified with Farmer?
public class Animal {
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
