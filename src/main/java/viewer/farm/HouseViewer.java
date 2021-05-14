package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.entity.BuildingDrawer;
import model.farm.building.House;

public class HouseViewer {
    private final static Color HOUSE_WALL_COLOR = new Color("#eeeeef");
    private final static Color HOUSE_ROOF_COLOR = new Color("#c20000");

    public void draw(House house, GUI gui) {
        BuildingDrawer drawer = new BuildingDrawer(gui, HOUSE_WALL_COLOR, HOUSE_ROOF_COLOR);
        drawer.draw(house.getTopLeftPosition());
    }
}
