package model.farm;

import model.Position;

public abstract class Animal {
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

    public abstract void eat();  // Stockyards have food and animals will decrease its food;
}
