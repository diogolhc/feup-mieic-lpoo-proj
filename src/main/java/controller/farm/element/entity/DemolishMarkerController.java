package controller.farm.element.entity;

import gui.GUI;
import model.Position;
import model.farm.entity.Entity;
import model.farm.Farm;

// TODO very similar to FarmerController
public class DemolishMarkerController {
    private final Farm farm;
    private Entity marker;

    public DemolishMarkerController(Farm farm, Entity marker) {
        this.farm = farm;
        this.marker = marker;
    }

    public void doAction(GUI.KEYBOARD_ACTION action) {
        if (action == GUI.KEYBOARD_ACTION.MOVE_UP) moveUp();
        if (action == GUI.KEYBOARD_ACTION.MOVE_RIGHT) moveRight();
        if (action == GUI.KEYBOARD_ACTION.MOVE_DOWN) moveDown();
        if (action == GUI.KEYBOARD_ACTION.MOVE_LEFT) moveLeft();
    }

    private void moveLeft() {
        move(this.marker.getPosition().getLeft());
    }

    private void moveRight() {
        move(this.marker.getPosition().getRight());
    }

    private void moveUp() {
        move(this.marker.getPosition().getUp());
    }

    private void moveDown() {
        move(this.marker.getPosition().getDown());
    }

    private void move(Position position) {
        if (farm.getInsideRegion().contains(position)) {
            this.marker.setPosition(position);
        }
    }
}
