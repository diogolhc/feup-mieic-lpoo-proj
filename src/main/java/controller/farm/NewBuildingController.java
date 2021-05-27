package controller.farm;

import gui.GUI;
import model.Position;
import model.farm.Farm;
import model.farm.building.Building;

public class NewBuildingController {
    private final Farm farm;
    private final Building newBuilding;

    public NewBuildingController(Farm farm, Building newBuilding) {
        this.farm = farm;
        this.newBuilding = newBuilding;
    }

    public void doAction(GUI.KEYBOARD_ACTION action) {
        if (action == GUI.KEYBOARD_ACTION.MOVE_UP) moveUp();
        if (action == GUI.KEYBOARD_ACTION.MOVE_RIGHT) moveRight();
        if (action == GUI.KEYBOARD_ACTION.MOVE_DOWN) moveDown();
        if (action == GUI.KEYBOARD_ACTION.MOVE_LEFT) moveLeft();
    }

    private void moveLeft() {
        move(newBuilding.getTopLeftPosition().getLeft());
    }

    private void moveRight() {
        move(newBuilding.getTopLeftPosition().getRight());
    }

    private void moveUp() {
        move(newBuilding.getTopLeftPosition().getUp());
    }

    private void moveDown() {
        move(newBuilding.getTopLeftPosition().getDown());
    }

    private void move(Position position) {
        Position oldPosition = newBuilding.getTopLeftPosition();
        newBuilding.setTopLeftPosition(position);
        if (!farm.getInsideRegion().contains(newBuilding.getOccupiedRegion())) {
            newBuilding.setTopLeftPosition(oldPosition);
        }
    }
}
