package controller;

import gui.GUI;
import model.Farm;
import model.Position;

public class FarmerController {
    private final Farm farm;

    public FarmerController(Farm farm) {
        this.farm = farm;
    }

    public void doAction(GUI.ACTION action) {
        if (action == GUI.ACTION.UP) moveUp();
        if (action == GUI.ACTION.RIGHT) moveRight();
        if (action == GUI.ACTION.DOWN) moveDown();
        if (action == GUI.ACTION.LEFT) moveLeft();
    }

    public void moveLeft() {
        move(farm.getFarmer().getPosition().getLeft());
    }

    public void moveRight() {
        move(farm.getFarmer().getPosition().getRight());
    }

    public void moveUp() {
        move(farm.getFarmer().getPosition().getUp());
    }

    public void moveDown() {
        move(farm.getFarmer().getPosition().getDown());
    }

    private void move(Position position) {
        if (farm.isTraversable(position)) {
            farm.getFarmer().setPosition(position);
        }
    }
}
