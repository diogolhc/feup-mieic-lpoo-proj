package model.farm.Animals;

import model.Position;

public class Chicken extends Animal {
    public static final int eatingRate = 1;
    public static final int starvingRate = 0;


    public Chicken(Position position, Hunger hunger, char identifier) {
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
        return getHunger().getHungerCounter() == 0;
    }
}
