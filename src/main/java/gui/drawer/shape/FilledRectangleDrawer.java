package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import model.Position;

public class FilledRectangleDrawer {
    private final GUI gui;
    private final Color backgroundColor;
    private final Color foregroundColor;
    private final char character;

    public FilledRectangleDrawer(GUI gui, Color backgroundColor, Color foregroundColor, char character) {
        this.gui = gui;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.character = character;
    }

    public void draw(Position position, int width, int height) {
        HorizontalLineDrawer hLineDrawer = new HorizontalLineDrawer(
                this.gui, this.backgroundColor, this.foregroundColor, this.character);

        for (int i = 0; i < height; i++) {
            hLineDrawer.draw(position, width);
            position = position.getDown();
        }
    }
}
