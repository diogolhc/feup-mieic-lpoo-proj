package model;

import model.farm.Farm;
import model.menu.Menu;

import static model.Weather.TYPE.SUNNY;

public class GameModel {
    private final Farm farm;
    private Menu menu;
    // vv TODO maybe it will not be like this, it may be inside of a HUD stats or smt
    private Time time;
    private Weather weather;

    public GameModel() {
        this.farm = new Farm(40, 20);
        this.menu = null; // TODO this is obviously not gonna be like this in the final version
        // Options to get rid of null:
        //  1- Use of  a NULL OBJECT
        //  2- Extend game model with GameModelWithMenu or something similar (i prefer this one)
        this.time = new Time();
        this.weather = new Weather(SUNNY);
    }

    public Farm getFarm() {
        return this.farm;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Time getTime() {
        return time;
    }

    public Weather getWeather() {
        return weather;
    }

}
