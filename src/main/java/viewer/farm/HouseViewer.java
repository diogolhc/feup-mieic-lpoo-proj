package viewer.farm;

import gui.GUI;
import gui.drawer.HouseDrawer;
import model.farm.House;

public class HouseViewer {
    public void draw(House house, GUI gui) {
        HouseDrawer drawer = new HouseDrawer(gui);
        drawer.draw(house.getPosition());

    }
}
