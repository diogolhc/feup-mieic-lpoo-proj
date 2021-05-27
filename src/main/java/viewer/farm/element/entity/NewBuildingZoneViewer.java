package viewer.farm.element.entity;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import model.Position;
import model.farm.building.Building;
import model.farm.building.BuildingSet;

public class NewBuildingZoneViewer {
    public static final Color AVAILABLE_ZONE_COLOR = new Color("#03ac13");
    public static final Color UNAVAILABLE_ZONE_COLOR = new Color("#e3242b");

    public void draw(BuildingSet buildings, Building newBuilding, GUI gui) {
        Position position = newBuilding.getTopLeftPosition();
        int width = newBuilding.getWidth();
        int height = newBuilding.getHeight();

        Color zoneColor;
        if (buildings.isOccupied(newBuilding.getOccupiedRegion())) {
            zoneColor = UNAVAILABLE_ZONE_COLOR;
        } else {
            zoneColor = AVAILABLE_ZONE_COLOR;
        }

        FilledRectangleDrawer rectangleDrawer = new FilledRectangleDrawer(gui, zoneColor);
        rectangleDrawer.draw(position, width, height);
    }
}
