package gui.drawer.shape;

import gui.GUI;
import gui.drawer.shape.HorizontalLineDrawer;
import gui.drawer.shape.VerticalLineDrawer;
import model.Position;

public class BoxDrawer {
    private final GUI gui;
    private final String backgroundColor;
    private final String foregroundColor;
    private static final char HORIZONTAL_LINE = '-';
    private static final char VERTICAL_LINE = '|';
    private static final char CORNER_LINE = '+';

    public BoxDrawer(GUI gui, String backgroundColor, String foregroundColor) {
        this.gui = gui;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public void draw(Position position, int width, int height) {
        if (width > 2) {
            HorizontalLineDrawer hLineDrawer = new HorizontalLineDrawer(
                    this.gui, this.backgroundColor, this.foregroundColor, HORIZONTAL_LINE);
            hLineDrawer.draw(position.getRight(), width - 2);
            Position bottom = new Position(position.getX() + 1, position.getY() + height - 1);
            hLineDrawer.draw(bottom, width - 2);
        }

        if (height > 2) {
            VerticalLineDrawer vLineDrawer = new VerticalLineDrawer(
                    this.gui, this.backgroundColor, this.foregroundColor, VERTICAL_LINE);
            vLineDrawer.draw(position.getDown(), height - 2);
            Position right = new Position(position.getX() + width - 1, position.getY() + 1);
            vLineDrawer.draw(right, height - 2);
        }

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
