package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import model.Position;

public class HorizontalLineDrawer {
    private final GUI gui;
    private final Color backgroundColor;
    private final Color foregroundColor;
    private final char character;

    public HorizontalLineDrawer(GUI gui, Color backgroundColor, Color foregroundColor, char character) {
        this.gui = gui;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.character = character;
    }

    public HorizontalLineDrawer(GUI gui, Color backgroundColor) {
        this(gui, backgroundColor, backgroundColor, ' ');
    }

    public void draw(Position position, int length) {
        this.gui.setForegroundColor(this.foregroundColor);
        this.gui.setBackgroundColor(this.backgroundColor);

        for (int i = 0; i < length; i++) {
            this.gui.drawChar(position.getX(), position.getY(), this.character);
            position = position.getRight();
        }
    }
}
