package model.farm.Animals;

import gui.Color;
import model.Position;

public class Cow extends Animal{

    public Cow(Position position, Hunger hunger, char identifier) {
        super(position, hunger, identifier);
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
        return false;
    }
}
