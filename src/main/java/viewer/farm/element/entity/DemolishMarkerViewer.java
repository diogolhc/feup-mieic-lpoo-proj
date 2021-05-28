package viewer.farm.element.entity;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import model.farm.entity.Entity;
import model.farm.building.Building;
import model.farm.building.BuildingSet;

public class DemolishMarkerViewer {
    public static final Color MARKER_COLOR = new Color("#e3242b");
    public static final Color TARGETED_COLOR = new Color("#f83a22");
    public static final char MARKER_CHAR = 'D';

    public void draw(BuildingSet buildings, Entity marker, GUI gui) {
        drawBuildingHighlight(buildings, marker, gui);
        drawMarker(marker, gui);
    }

    private void drawBuildingHighlight(BuildingSet buildings, Entity marker, GUI gui) {
        FilledRectangleDrawer rectangleDrawer = new FilledRectangleDrawer(gui, TARGETED_COLOR);

        for (Building building: buildings.getDemolishableBuildings()) {
            if (building.getOccupiedRegion().contains(marker.getPosition())) {
                rectangleDrawer.draw(building.getTopLeftPosition(), building.getWidth(), building.getHeight());
            }
        }
    }

    private void drawMarker(Entity marker, GUI gui) {
        gui.setBackgroundColor(MARKER_COLOR);
        gui.setForegroundColor(Color.WHITE);
        gui.drawChar(marker.getPosition().getX(), marker.getPosition().getY(), MARKER_CHAR);
    }
}
