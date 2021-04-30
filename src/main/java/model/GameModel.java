package model;

public class GameModel {
    private final Farm farm;

    public GameModel() {
        this.farm = new Farm(40, 20);
    }

    public Farm getFarm() {
        return this.farm;
    }
}
