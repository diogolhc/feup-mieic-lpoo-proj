package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.entity.BuildingDrawer;
import model.farm.building.House;
import model.farm.building.Market;

public class MarketViewer {
    private final static Color MARKET_WALL_COLOR = new Color("#eeeeef");
    private final static Color MARKET_ROOF_COLOR = new Color("#00c200");

    public void draw(Market market, GUI gui) {
        BuildingDrawer drawer = new BuildingDrawer(gui, MARKET_WALL_COLOR, MARKET_ROOF_COLOR);
        drawer.draw(market.getTopLeftPosition());
    }
}
