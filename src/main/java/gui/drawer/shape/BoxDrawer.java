package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.HorizontalLineDrawer;
import gui.drawer.shape.VerticalLineDrawer;
import model.Position;

public class BoxDrawer {
    private final GUI gui;
    private final Color backgroundColor;
    private final Color foregroundColor;
    public static final char HORIZONTAL_LINE = '-';
    public static final char VERTICAL_LINE = '|';
    public static final char CORNER_LINE = '+';

    public BoxDrawer(GUI gui, Color backgroundColor, Color foregroundColor) {
        this.gui = gui;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public void draw(Position position, int width, int height) {
        this.drawHorizontalEdges(position, width, height);
        this.drawVerticalEdges(position, width, height);
        this.drawCorner(position, width, height);
    }

    private void drawHorizontalEdges(Position position, int width, int height) {
        if (width > 2) {
            HorizontalLineDrawer hLineDrawer = new HorizontalLineDrawer(
                    this.gui, this.backgroundColor, this.foregroundColor, HORIZONTAL_LINE);
            Position top = position.getRight();
            hLineDrawer.draw(top, width - 2);
            Position bottom = position.getTranslated(new Position(1, height - 1));
            hLineDrawer.draw(bottom, width - 2);
        }
    }

    private void drawVerticalEdges(Position position, int width, int height) {
        if (height > 2) {
            VerticalLineDrawer vLineDrawer = new VerticalLineDrawer(
                    this.gui, this.backgroundColor, this.foregroundColor, VERTICAL_LINE);
            Position left = position.getDown();
            vLineDrawer.draw(left, height - 2);
            Position right = position.getTranslated(new Position(width - 1, 1));
            vLineDrawer.draw(right, height - 2);
        }
    }

    private void drawCorner(Position position, int width, int height) {
        int x = position.getX();
        int y = position.getY();

        this.gui.setBackgroundColor(this.backgroundColor);
        this.gui.setForegroundColor(this.foregroundColor);
        this.gui.drawChar(x, y, CORNER_LINE);
        this.gui.drawChar(x + width - 1, y, CORNER_LINE);
        this.gui.drawChar(x, y + height - 1, CORNER_LINE);
        this.gui.drawChar(x + width - 1, y + height - 1, CORNER_LINE);
    }
}
