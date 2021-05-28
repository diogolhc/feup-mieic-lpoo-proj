package controller.farm.element.building;

import controller.command.Command;
import model.Position;
import model.farm.building.Building;

public abstract class BuildingController<T extends Building> {
    public abstract Command getInteractionCommand(T building);
    public abstract Command getDemolishCommand(T building);

    public void reactInteraction(T building, Position position) {
        if (building.getInteractiveRegion().contains(position)) {
            getInteractionCommand(building).execute();
        }
    }

    public void reactDemolish(T building, Position position) {
        if (building.getOccupiedRegion().contains(position)) {
            getDemolishCommand(building).execute();
        }
    }
}
