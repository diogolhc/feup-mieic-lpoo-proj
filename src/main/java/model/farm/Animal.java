package model.farm;

import model.Position;

public class Animal extends AnimatedElement{
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
