package model;

public class Farm {
    private final Farmer farmer;

    public Farm() {
        this.farmer = new Farmer(3, 3);
    }

    public Farmer getFarmer() {
        return this.farmer;
    }

    public boolean isTraversable(Position position) {
        return true;
    }
}
