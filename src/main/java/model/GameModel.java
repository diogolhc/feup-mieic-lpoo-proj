package model;

import model.farm.Farm;
import model.menu.Menu;

public class GameModel {
    private final Farm farm;
    private Menu menu;

    public GameModel() {
        this.farm = new Farm(40, 20);
        this.menu = null; // TODO this is obviously not gonna be like this in the final version
        // Options to get rid of null:
        //  1- Use of  a NULL OBJECT
        //  2- Extend game model with GameModelWithMenu or something similar (i prefer this one)
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
}
