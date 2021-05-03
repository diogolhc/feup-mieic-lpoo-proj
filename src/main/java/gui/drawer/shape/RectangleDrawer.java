package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import model.Position;

public class RectangleDrawer {
    private final GUI gui;
    private final Color backgroundColor;
    private final Color foregroundColor;
    private final char character;

    public RectangleDrawer(GUI gui, Color backgroundColor, Color foregroundColor, char character) {
        this.gui = gui;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.character = character;
    }

    public void draw(Position position, int width, int height) {
        HorizontalLineDrawer hLineDrawer = new HorizontalLineDrawer(
                this.gui, this.backgroundColor, this.foregroundColor, this.character);
        VerticalLineDrawer vLineDrawer = new VerticalLineDrawer(
                this.gui, this.backgroundColor, this.foregroundColor, this.character);

        hLineDrawer.draw(position, width);
        vLineDrawer.draw(position, height);

        Position topRight = new Position(position.getX() + width - 1, position.getY());
        Position bottomLeft = new Position(position.getX(), position.getY() + height - 1);

        hLineDrawer.draw(bottomLeft, width);
        vLineDrawer.draw(topRight, height);
    }
}
