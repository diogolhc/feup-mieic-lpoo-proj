package controller.farm.element.entity;

import gui.GUI;
import model.farm.Farm;
import model.Position;

public class FarmerController {
    private final Farm farm;

    public FarmerController(Farm farm) {
        this.farm = farm;
    }

    public void reactKeyboard(GUI.KEYBOARD_ACTION action) {
        if (action == GUI.KEYBOARD_ACTION.MOVE_UP) moveUp();
        if (action == GUI.KEYBOARD_ACTION.MOVE_RIGHT) moveRight();
        if (action == GUI.KEYBOARD_ACTION.MOVE_DOWN) moveDown();
        if (action == GUI.KEYBOARD_ACTION.MOVE_LEFT) moveLeft();
    }

    private void moveLeft() {
        move(farm.getFarmer().getPosition().getLeft());
    }

    private void moveRight() {
        move(farm.getFarmer().getPosition().getRight());
    }

    private void moveUp() {
        move(farm.getFarmer().getPosition().getUp());
    }

    private void moveDown() {
        move(farm.getFarmer().getPosition().getDown());
    }

    private void move(Position position) {
        if (farm.isTraversable(position)) {
            farm.getFarmer().setPosition(position);
        }
    }
}
