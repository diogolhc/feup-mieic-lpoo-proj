package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.string.StringDrawer;
import model.Position;
import model.InGameTime;
import model.farm.Currency;
import model.farm.Weather;


public class HUDViewer {
    public void draw(InGameTime time, Weather weather, Currency currency, GUI gui) {
        StringDrawer drawer = new StringDrawer(gui, new Color("#1f1f1f"), new Color("#ffffff"));
        drawer.draw(new Position(0, 20), time.toString());
        drawer.draw(new Position(18, 20), weather.getName());
        StringDrawer coinsDrawer = new StringDrawer(gui, new Color("#1f1f1f"), new Color("#ffd700"));
        coinsDrawer.draw(new Position(33, 20), currency.toStringPadded());
    }
}
