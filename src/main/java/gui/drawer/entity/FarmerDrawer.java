package gui.drawer.entity;

import gui.Color;
import gui.GUI;
import model.Position;

public class FarmerDrawer {
    private final GUI gui;
    private static final Color FARMER_COLOR = new Color("#223366");
    private static final char FARMER_CHAR = '@';

    public FarmerDrawer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Position position) {
        Color backgroundColor = gui.getBackgroundColor(position.getX(), position.getY());
        gui.setBackgroundColor(backgroundColor);
        gui.setForegroundColor(FARMER_COLOR);
        gui.drawChar(position.getX(), position.getY(), FARMER_CHAR);
    }
}
