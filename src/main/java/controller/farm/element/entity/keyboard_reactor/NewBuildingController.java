package controller.farm.element.entity.keyboard_reactor;

import gui.GUI;
import model.Position;
import model.farm.Farm;
import model.farm.building.Building;

public class NewBuildingController extends KeyboardReactorEntityController {
    private final Farm farm;
    private final Building newBuilding;

    public NewBuildingController(Farm farm, Building newBuilding) {
        this.farm = farm;
        this.newBuilding = newBuilding;
    }

    @Override
    protected Position getEntityPosition() {
        return this.newBuilding.getTopLeftPosition();
    }

    @Override
    protected void moveEntity(Position position) {
        if (farm.getInsideRegion().contains(newBuilding.getOccupiedRegion().getAt(position))) {
            newBuilding.setTopLeftPosition(position);
        }
    }
}
