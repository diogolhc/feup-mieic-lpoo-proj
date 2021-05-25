package model.farm.Animals;

import model.Position;

public abstract class Animal {
    //TODO HOW SHOULD ANIMALS DIE AND CHECK WHICH ALIMENTS THEY GIVE (MILK, CORN, EGGS...)???
    private Position position;
    private Hunger hunger;

    protected int eatingRate;
    protected int starvingRate;
    protected char identifier;    //TODO SHOULD WE HAVE A CHAR TO REPRESENT EACH ANIMAL? OR STOCKYARD CONTROLLER SHOULD NOTICE IT?
    //private FoodItem typeOfFood; //TODO Animals eat this type of food

    public Animal(Position position, Hunger hunger, char identifier, int starvingRate, int eatingRate) {
        this.position = position;
        this.hunger = hunger;
        this.identifier = identifier;
        this.starvingRate = starvingRate;
        this.eatingRate = eatingRate;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setHunger(Hunger hunger) { this.hunger = hunger; }

    public void decreaseHunger() { this.hunger.decreaseHunger(starvingRate); }

    public Hunger getHunger() { return this.hunger; }

    public abstract void setChar(char identifier);

    public abstract char getChar();

    public abstract boolean isDead();

}
