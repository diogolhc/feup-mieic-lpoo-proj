package viewer.farm.element.building;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import gui.drawer.shape.HorizontalLineDrawer;
import model.Position;

public class EdificeViewer {
    private static final Color WALL_COLOR = new Color("#eeeeef");
    private static final Color FLOOR_COLOR = new Color("#777777");
    private static final Color DOOR_COLOR = new Color("#82490b");
    private final Color roofColor;

    public EdificeViewer(Color roofColor) {
        this.roofColor = roofColor;
    }

    public void draw(Position position, GUI gui) {
        drawRoof(position, this.roofColor, gui);
        drawWall(position, gui);
        drawFloor(position, gui);
        drawDoor(position, gui);
    }

    private void drawRoof(Position position, Color roofColor, GUI gui) {
        HorizontalLineDrawer roofDrawer = new HorizontalLineDrawer(gui, roofColor);
        roofDrawer.draw(position.getTranslated(new Position(1, 0)), 5);
        roofDrawer.draw(position.getTranslated(new Position(0, 1)), 7);
    }

    private void drawWall(Position position, GUI gui) {
        FilledRectangleDrawer wallDrawer = new FilledRectangleDrawer(gui, WALL_COLOR);
        wallDrawer.draw(position.getTranslated(new Position(0, 2)), 7, 4);
    }

    private void drawDoor(Position position, GUI gui) {
        gui.setBackgroundColor(DOOR_COLOR);
        gui.setForegroundColor(Color.BLACK);
        gui.drawChar(position.getX()+4, position.getY()+4, ' ');
        gui.drawChar(position.getX()+4, position.getY()+5, '\'');
    }

    private void drawFloor(Position position, GUI gui) {
        HorizontalLineDrawer floorDrawer = new HorizontalLineDrawer(gui, FLOOR_COLOR);
        floorDrawer.draw(position.getTranslated(new Position(0, 6)), 7);

        drawInteractiveFloor(position, gui);
    }

    private void drawInteractiveFloor(Position position, GUI gui) {
        gui.setBackgroundColor(Color.HIGHLIGHTED_FLOOR);
        gui.drawChar(position.getX()+4, position.getY()+6, ' ');
    }
}
