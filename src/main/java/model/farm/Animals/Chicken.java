package model.farm.Animals;

import model.Position;

public class Chicken extends Animal {

    public Chicken(Position position, Hunger hunger, char identifier) {
        super(position, hunger);
        this.identifier = identifier;
    }

    @Override
    public void setChar(char identifier) {
        this.identifier = identifier;
    }

    @Override
    public char getChar() {
        return this.identifier;
    }

}
