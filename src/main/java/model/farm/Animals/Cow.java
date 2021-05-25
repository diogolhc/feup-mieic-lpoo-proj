package model.farm.Animals;

import gui.Color;
import model.Position;

public class Cow extends Animal{
    public static final int eatingRate = 5;
    public static final int starvingRate = 1;

    public Cow(Position position, Hunger hunger, char identifier) {
        super(position, hunger, identifier, starvingRate, eatingRate);
    }

    @Override
    public void setChar(char identifier) {
        this.identifier = identifier;
    }

    @Override
    public char getChar() {
        return this.identifier;
    }

    @Override
    public boolean isDead() {
        return this.getHunger().getHungerCounter() == 0;
    }
}
