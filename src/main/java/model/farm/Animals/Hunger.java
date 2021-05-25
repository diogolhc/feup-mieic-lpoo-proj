package model.farm.Animals;

public class Hunger {

    //TODO HOW SHOULD ANIMALS DIE AND CHECK WHICH ALIMENTS THEY GIVE (MILK, CORN, EGGS...)
    private int hunger;
    private int upperBound;
    // TODO THIS SHOULD REPRESENT THE FRAME THAT ANIMALS STARVE: 0-20 its a cow, 0-100 its a chicken
    // so chicken takes more time to die if at each time cow loses 1 of hunger

    public Hunger(int hunger) {
        this.hunger = hunger;
    }

    public void decreaseHunger(int decrease) {
        this.hunger = this.hunger > decrease ? this.hunger - decrease : 0;
    }

    public void increaseHunger(int hunger) {
        this.hunger += hunger;
    }

    public void setHunger(int hunger) { this.hunger += hunger; }

    public int getHungerCounter() { return hunger; }

    public int getUpperBound() { return upperBound; }

    public void setUpperBound(int upperBound) { this.upperBound = upperBound; }
}
