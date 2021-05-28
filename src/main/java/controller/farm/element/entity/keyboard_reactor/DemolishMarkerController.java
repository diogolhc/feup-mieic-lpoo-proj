package controller.farm.element.entity.keyboard_reactor;

import gui.GUI;
import model.Position;
import model.farm.entity.Entity;
import model.farm.Farm;

public class DemolishMarkerController extends KeyboardReactorEntityController {
    private final Farm farm;
    private Entity marker;

    public DemolishMarkerController(Farm farm, Entity marker) {
        this.farm = farm;
        this.marker = marker;
    }

    @Override
    protected Position getEntityPosition() {
        return this.marker.getPosition();
    }

    @Override
    protected void moveEntity(Position position) {
        if (farm.getInsideRegion().contains(position)) {
            this.marker.setPosition(position);
        }
    }
}
