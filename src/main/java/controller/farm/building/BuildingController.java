package controller.farm.building;

import controller.command.Command;
import model.Position;
import model.farm.building.Building;

public abstract class BuildingController<T extends Building> {
    public abstract Command getInteractionCommand(T building);

    public void reactInteraction(T building, Position position) {
        if (building.isInInteractiveZone(position)) {
            getInteractionCommand(building).execute();
        }
    }
}
