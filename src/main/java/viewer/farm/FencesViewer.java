package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.BoxDrawer;
import model.Position;

public class FencesViewer {
    private static final Color FENCES_COLOR = new Color("#846f46");
    private static final Color FENCES_BACKGROUND = new Color("#7EC850");

    public void draw(Position position, int width, int height, GUI gui) {
        BoxDrawer drawer = new BoxDrawer(gui, FENCES_BACKGROUND, FENCES_COLOR);
        drawer.draw(position, width, height);
    }
}
