package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.string.StringDrawer;
import model.Position;
import model.InGameTime;
import model.farm.Weather;


public class HUDViewer {
    public void draw(InGameTime time, Weather weather, GUI gui) {
        StringDrawer drawer = new StringDrawer(gui, new Color("#222222"), new Color("#ffffff"));
        drawer.draw(new Position(0, 20), time.toString());
        drawer.draw(new Position(25, 20), weather.getName());
    }
}
