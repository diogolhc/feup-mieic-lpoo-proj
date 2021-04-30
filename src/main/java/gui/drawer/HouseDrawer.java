package gui.drawer;

import gui.GUI;
import model.House;
import model.Position;

import java.io.File;

public class HouseDrawer {
    private GUI gui;
    private final static String PATH_COLOR = "#be9b7b";
    private final static String SOIL_COLOR = "#5c5c5c";
    private final static String HOUSE_COLOR = "#c20000";
    private final static char HOUSE_CHAR = 'H';


    public HouseDrawer(GUI gui) { this.gui = gui; }

    public void draw(Position position) {
        int width = 7, height = 7;
        RectangleDrawer pathDrawer = new RectangleDrawer(gui, PATH_COLOR, PATH_COLOR, ' ');
        FilledRectangleDrawer houseDrawer = new FilledRectangleDrawer(gui, SOIL_COLOR, HOUSE_COLOR, HOUSE_CHAR);
        pathDrawer.draw(position, width, height);
        // houseDrawer.draw(position.getRight().getDown(), 5 ,5);

        FilledRectangleDrawer backgroundDrawer = new FilledRectangleDrawer(gui, SOIL_COLOR, SOIL_COLOR, ' ');
        int y = position.getY() + 1, x = position.getX() + 1;
        backgroundDrawer.draw(new Position(x, y), width - 2, height - 2);

        this.gui.setForegroundColor(HOUSE_COLOR);
        this.gui.drawChar(x + 2, y, '_');
        this.gui.drawChar(x + 1, y + 1, '/'); this.gui.drawChar(x + 3, y + 1, (char) 92);
        this.gui.drawChar(x, y + 2, '/'); this.gui.drawChar(x + 4, y + 2, (char) 92);
        this.gui.drawChar(x, y + 3, '|'); this.gui.drawChar(x + 4, y + 3, '|');
        this.gui.drawChar(x, y + 4, '|'); this.gui.drawChar(x + 4, y + 4, '|');
        this.gui.drawChar(x + 2, y + 3, '.'); this.gui.drawChar(x + 3, y + 3, '.');
        this.gui.drawChar(x + 2, y + 4, '|'); this.gui.drawChar(x + 3, y + 4, '|');
    }
}
