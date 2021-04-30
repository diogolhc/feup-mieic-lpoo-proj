package gui.drawer;

import gui.GUI;
import model.Position;

public class RectangleDrawer {
    private final GUI gui;
    private final String backgroundColor;
    private final String foregroundColor;
    private final char character;

    public RectangleDrawer(GUI gui, String backgroundColor, String foregroundColor, char character) {
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
