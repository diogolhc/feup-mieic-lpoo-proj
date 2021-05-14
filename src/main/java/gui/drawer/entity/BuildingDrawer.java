package gui.drawer.entity;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import gui.drawer.shape.HorizontalLineDrawer;
import gui.drawer.shape.RectangleDrawer;
import gui.drawer.shape.VerticalLineDrawer;
import model.Position;

public class BuildingDrawer {
    private GUI gui;
    private Color wallColor;
    private Color roofColor;
    private final static Color PATH_COLOR = new Color("#be9b7b");
    private final static Color FLOOR_COLOR = new Color("#777777");
    private final static Color DOOR_COLOR = new Color("#82490b");


    public BuildingDrawer(GUI gui, Color wallColor, Color roofColor) { this.gui = gui;
        this.wallColor = wallColor;
        this.roofColor = roofColor;
    }

    public void draw(Position position) {
        HorizontalLineDrawer roofDrawer = new HorizontalLineDrawer(
                gui, this.roofColor, this.roofColor, ' ');
        roofDrawer.draw(position.getTranslated(new Position(1, 0)), 5);
        roofDrawer.draw(position.getTranslated(new Position(0, 1)), 7);

        FilledRectangleDrawer wallDrawer = new FilledRectangleDrawer(
                gui, this.wallColor, this.wallColor, ' '
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
    }
}
