package viewer.farm.element.entity;

import gui.Color;
import gui.GUI;
import model.Position;
import model.farm.Farmer;

public class FarmerViewer {
    public static final Color FARMER_COLOR = new Color("#223366");
    public static final char FARMER_CHAR = '@';

    public void draw(Farmer farmer, GUI gui) {
        Position position = farmer.getPosition();

        Color backgroundColor = gui.getBackgroundColor(position.getX(), position.getY());
        gui.setBackgroundColor(backgroundColor);
        gui.setForegroundColor(FARMER_COLOR);
        gui.drawChar(position.getX(), position.getY(), FARMER_CHAR);
    }
}
