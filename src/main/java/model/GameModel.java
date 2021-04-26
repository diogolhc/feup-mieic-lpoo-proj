package model;

public class GameModel {
    private final Farm farm;

    public GameModel() {
        this.farm = new Farm(10, 10);
    }

    public Farm getFarm() {
        return this.farm;
    }
}
