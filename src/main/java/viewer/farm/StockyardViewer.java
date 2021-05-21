package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.BoxDrawer;
import model.Position;
import model.farm.building.Stockyard;

public class StockyardViewer {
    private static final Color FENCES_COLOR = new Color("#695836");
    private static final Color FENCES_BACKGROUND = new Color("#7EC850");

    public void draw(Stockyard stockyard, GUI gui) {
        BoxDrawer drawer = new BoxDrawer(gui, FENCES_BACKGROUND, FENCES_COLOR);
        drawer.draw(stockyard.getTopLeftPosition(), Stockyard.STOCKYARD_SIZE, Stockyard.STOCKYARD_SIZE);
    }
}
