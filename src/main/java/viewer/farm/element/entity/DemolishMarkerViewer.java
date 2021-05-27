package viewer.farm.element.entity;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import model.farm.DemolishMarker;
import model.farm.building.Building;
import model.farm.building.BuildingSet;

public class DemolishMarkerViewer {
    public static final Color MARKER_COLOR = new Color("#e3242b");
    public static final Color TARGETED_COLOR = new Color("#f83a22");

    public void draw(BuildingSet buildings, DemolishMarker marker, GUI gui) {
        drawBuildingHighlight(buildings, marker, gui);
        drawMarker(marker, gui);
    }

    private void drawBuildingHighlight(BuildingSet buildings, DemolishMarker marker, GUI gui) {
        FilledRectangleDrawer rectangleDrawer = new FilledRectangleDrawer(gui, TARGETED_COLOR);

        for (Building building: buildings.getDemolishableBuildings()) {
            if (building.getOccupiedRegion().contains(marker.getPosition())) {
                rectangleDrawer.draw(building.getTopLeftPosition(), building.getWidth(), building.getHeight());
            }
        }
    }

    private void drawMarker(DemolishMarker marker, GUI gui) {
        gui.setBackgroundColor(MARKER_COLOR);
        gui.setForegroundColor(Color.WHITE);
        gui.drawChar(marker.getPosition().getX(), marker.getPosition().getY(), 'D');
    }
}
