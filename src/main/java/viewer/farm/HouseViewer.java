package viewer.farm;

import gui.GUI;
import gui.drawer.entity.HouseDrawer;
import model.farm.building.House;

public class HouseViewer {
    public void draw(House house, GUI gui) {
        HouseDrawer drawer = new HouseDrawer(gui);
        drawer.draw(house.getTopLeftPosition());
    }
}
