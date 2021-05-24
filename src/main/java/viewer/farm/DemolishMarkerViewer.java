package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import model.Position;
import model.farm.DemolishMarker;
import model.farm.building.Building;
import model.farm.building.BuildingSet;

public class DemolishMarkerViewer {
    private static final Color MARKER_COLOR = new Color("#e3242b");
    private static final Color TARGETED_COLOR = new Color("#f83a22");

    public void draw(BuildingSet buildings, DemolishMarker marker, GUI gui) {
        for (Building building: buildings.getDemolishableBuildings()) {
            if (building.getOccupiedRegion().contains(marker.getPosition())) {
                FilledRectangleDrawer rectangleDrawer = new FilledRectangleDrawer(gui,
                        TARGETED_COLOR, TARGETED_COLOR, ' ');
                rectangleDrawer.draw(building.getTopLeftPosition(), building.getWidth(), building.getHeight());
            }
        }

        gui.setBackgroundColor(MARKER_COLOR);
        gui.setForegroundColor(new Color("#ffffff"));
        gui.drawChar(marker.getPosition().getX(), marker.getPosition().getY(), 'D');
    }
}
