package model.farm.Animals;

public class Hunger {

    //TODO HOW SHOULD ANIMALS DIE AND CHECK WHICH ALIMENTS THEY GIVE (MILK, CORN, EGGS...)
    private int hunger;
    private int upperBounder;
    // TODO THIS SHOULD REPRESENT THE FRAME THAT ANIMALS STARVE: 0-20 its a cow, 0-100 its a chicken
    // so chicken takes more time to die if at each time cow loses 1 of hunger

    public Hunger(int hunger) {
        this.hunger = hunger;
    }

    public void decreaseHunger(int hunger) {
        this.hunger -= hunger;
    }

    public void increaseHunger(int hunger) {
        this.hunger += hunger;
    }
}
