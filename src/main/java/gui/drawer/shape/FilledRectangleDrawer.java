package gui.drawer.shape;

import gui.GUI;
import model.Position;

public class FilledRectangleDrawer {
    private final GUI gui;
    private final String backgroundColor;
    private final String foregroundColor;
    private final char character;

    public FilledRectangleDrawer(GUI gui, String backgroundColor, String foregroundColor, char character) {
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
