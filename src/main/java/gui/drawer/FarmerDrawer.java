package gui.drawer;

import gui.GUI;
import model.Position;

public class FarmerDrawer {
    private GUI gui;
    private static final String FARMER_COLOR = "#999999";
    private static final char FARMER_CHAR = '@';

    public FarmerDrawer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Position position) {
        String backgroundColor = gui.getBackGroundColor(position.getX(), position.getY());
        gui.setBackgroundColor(backgroundColor);
        gui.setForegroundColor(FARMER_COLOR);
        gui.drawChar(position.getX(), position.getY(), FARMER_CHAR);
    }
}
