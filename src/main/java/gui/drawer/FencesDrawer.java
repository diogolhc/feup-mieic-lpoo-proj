package gui.drawer;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Farm;
import model.Position;

public class FencesDrawer {
    private final GUI gui;
    private static final String FENCES_COLOR = "#846f46";
    private static final String FENCES_BACKGROUND = "#7EC850";

    public FencesDrawer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Position position, int width, int height) {
        BoxDrawer drawer = new BoxDrawer(this.gui, FENCES_BACKGROUND, FENCES_COLOR);
        drawer.draw(position, width, height);
    }
}
