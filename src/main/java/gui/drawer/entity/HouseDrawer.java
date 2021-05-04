package gui.drawer.entity;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import gui.drawer.shape.HorizontalLineDrawer;
import gui.drawer.shape.RectangleDrawer;
import gui.drawer.shape.VerticalLineDrawer;
import model.Position;

public class HouseDrawer {
    private GUI gui;
    private final static Color PATH_COLOR = new Color("#be9b7b");
    private final static Color FLOOR_COLOR = new Color("#777777");
    private final static Color DOOR_COLOR = new Color("#82490b");
    private final static Color HOUSE_WALL_COLOR = new Color("#eeeeef");
    private final static Color HOUSE_ROOF_COLOR = new Color("#c20000");

    public HouseDrawer(GUI gui) { this.gui = gui; }

    public void draw(Position position) {
        HorizontalLineDrawer roofDrawer = new HorizontalLineDrawer(
                gui, HOUSE_ROOF_COLOR, HOUSE_ROOF_COLOR, ' ');
        roofDrawer.draw(position.getTranslated(new Position(1, 0)), 5);
        roofDrawer.draw(position.getTranslated(new Position(0, 1)), 7);

        FilledRectangleDrawer wallDrawer = new FilledRectangleDrawer(
                gui, HOUSE_WALL_COLOR, HOUSE_WALL_COLOR, ' '
        );
        wallDrawer.draw(position.getTranslated(new Position(0, 2)), 7, 4);

        HorizontalLineDrawer floorDrawer = new HorizontalLineDrawer(
                gui, FLOOR_COLOR, FLOOR_COLOR, ' '
        );
        floorDrawer.draw(position.getTranslated(new Position(0, 6)), 7);

        // PATH
        gui.setBackgroundColor(PATH_COLOR);
        gui.drawChar(position.getX()+4, position.getY()+6, ' ');

        // DOOR
        gui.setBackgroundColor(DOOR_COLOR);
        gui.setForegroundColor(new Color("#000000"));
        gui.drawChar(position.getX()+4, position.getY()+4, ' ');
        gui.drawChar(position.getX()+4, position.getY()+5, '\'');




        /*
        // TODO reorganize this code
        int width = 7, height = 7;
        RectangleDrawer pathDrawer = new RectangleDrawer(gui, PATH_COLOR, PATH_COLOR, ' ');
        pathDrawer.draw(position, width, height);

        FilledRectangleDrawer backgroundDrawer = new FilledRectangleDrawer(gui, HOUSE_FILL_COLOR, HOUSE_FILL_COLOR, ' ');
        int x = position.getX() + 1;
        int y = position.getY() + 1;
        backgroundDrawer.draw(new Position(x, y), width - 2, height - 2);

        HorizontalLineDrawer roofFill = new HorizontalLineDrawer(gui, HOUSE_ROOF_COLOR, HOUSE_ROOF_COLOR, ' ');
        roofFill.draw(new Position(x+1, y+2), 3);
        roofFill.draw(new Position(x+2, y+1), 1);

        FilledRectangleDrawer rectangleDrawer = new FilledRectangleDrawer(gui, new Color("#FFFFFF"), new Color("#FFFFFF"), ' ');
        rectangleDrawer.draw(new Position(x+1, y+3), 3, 2);

        this.gui.setForegroundColor(HOUSE_ROOF_COLOR);
        this.drawTransparentOutline(x + 2, y, '_');
        this.drawTransparentOutline(x + 1, y + 1, '/');
        this.drawTransparentOutline(x + 3, y + 1, '\\');
        this.drawTransparentOutline(x, y + 2, '/');
        this.drawTransparentOutline(x + 4, y + 2, '\\');
        this.drawTransparentOutline(x, y + 3, '|');
        this.drawTransparentOutline(x + 4, y + 3, '|');
        this.drawTransparentOutline(x, y + 4, '|');
        this.drawTransparentOutline(x + 4, y + 4, '|');
        this.drawTransparentOutline(x + 2, y + 3, '.');
        this.drawTransparentOutline(x + 3, y + 3, '.');
        this.drawTransparentOutline(x + 2, y + 4, '|');
        this.drawTransparentOutline(x + 3, y + 4, '|');*/
    }

    private void drawTransparentOutline(int x, int y, char c) {
        this.gui.setBackgroundColor(this.gui.getBackGroundColor(x, y));
        this.gui.drawChar(x, y, c);
    }
}
