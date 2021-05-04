package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.string.StringDrawer;
import model.Position;
import model.Weather;
import model.IngameTime;


public class HUDViewer {

    public void draw(IngameTime time, Weather weather, GUI gui) {
        /* TODO these hardcoded values are just for debug purposes
        *   This is temporary */
        StringDrawer drawer = new StringDrawer(gui, new Color("#222222"), new Color("#ffffff"));
        drawer.draw(new Position(0, 20), time.toString());
        drawer.draw(new Position(25, 20), weather.getType().name());
    }
}
