package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.entity.HouseDrawer;
import gui.drawer.string.StringDrawer;
import model.Position;
import model.farm.building.House;

public class HouseViewer {
    public void draw(House house, GUI gui) {
        HouseDrawer drawer = new HouseDrawer(gui);
        drawer.draw(house.getTopLeftPosition());
    }
}
