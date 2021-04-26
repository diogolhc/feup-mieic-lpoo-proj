package model;

public class GameModel {
    private final Farm farm;

    public GameModel() {
        this.farm = new Farm();
    }

    public Farm getFarm() {
        return this.farm;
    }
}
