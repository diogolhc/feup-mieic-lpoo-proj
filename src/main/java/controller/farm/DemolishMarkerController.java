package controller.farm;

import gui.GUI;
import model.Position;
import model.farm.DemolishMarker;
import model.farm.Farm;

// TODO very similar to FarmerController
public class DemolishMarkerController {
    private final Farm farm;
    private DemolishMarker marker;

    public DemolishMarkerController(Farm farm, DemolishMarker marker) {
        this.farm = farm;
        this.marker = marker;
    }

    public void doAction(GUI.ACTION action) {
        if (action == GUI.ACTION.MOVE_UP) moveUp();
        if (action == GUI.ACTION.MOVE_RIGHT) moveRight();
        if (action == GUI.ACTION.MOVE_DOWN) moveDown();
        if (action == GUI.ACTION.MOVE_LEFT) moveLeft();
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
