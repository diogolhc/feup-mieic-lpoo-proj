package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import model.Position;
import model.farm.building.Building;

public class NewBuildingZoneViewer {
    private static final Color AVAILABLE_ZONE_COLOR = new Color("#03ac13");
    private static final Color UNAVAILABLE_ZONE_COLOR = new Color("#e3242b");

    public void draw(Building newBuilding, GUI gui) {
        Position position = newBuilding.getTopLeftPosition();
        int width = newBuilding.getWidth();
        int height = newBuilding.getHeight();

        Color zoneColor;
        if (true) { // TODO
            zoneColor = AVAILABLE_ZONE_COLOR;
        } else {
            zoneColor = UNAVAILABLE_ZONE_COLOR;
        }

        FilledRectangleDrawer rectangleDrawer = new FilledRectangleDrawer(gui, zoneColor, zoneColor, ' ');
        rectangleDrawer.draw(position, width, height);
    }
}
