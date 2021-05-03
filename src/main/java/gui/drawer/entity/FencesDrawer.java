package gui.drawer.entity;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.BoxDrawer;
import model.Position;

public class FencesDrawer {
    private final GUI gui;
    private static final Color FENCES_COLOR = new Color("#846f46");
    private static final Color FENCES_BACKGROUND = new Color("#7EC850");

    public FencesDrawer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Position position, int width, int height) {
        BoxDrawer drawer = new BoxDrawer(this.gui, FENCES_BACKGROUND, FENCES_COLOR);
        drawer.draw(position, width, height);
    }
}
