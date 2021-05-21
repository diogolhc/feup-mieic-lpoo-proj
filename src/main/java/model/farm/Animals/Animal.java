package model.farm.Animals;

import model.Position;

public abstract class Animal {
    //TODO HOW SHOULD ANIMALS DIE AND CHECK WHICH ALIMENTS THEY GIVE (MILK, CORN, EGGS...)
    private Position position;
    private Hunger hunger;

    public Animal(Position position, Hunger hunger) {
        this.position = position;
        this.hunger = hunger;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract char getChar();
}
