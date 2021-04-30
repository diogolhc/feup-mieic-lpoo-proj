package gui.drawer;

import gui.GUI;
import model.Position;

public class VerticalLineDrawer {
    private final GUI gui;
    private final String backgroundColor;
    private final String foregroundColor;
    private final char character;

    public VerticalLineDrawer(GUI gui, String backgroundColor, String foregroundColor, char character) {
        this.gui = gui;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.character = character;
    }

    public void draw(Position position, int length) {
        this.gui.setForegroundColor(foregroundColor);
        this.gui.setBackgroundColor(backgroundColor);

        for (int i = 0; i < length; i++) {
            this.gui.drawChar(position.getX(), position.getY(), character);
            position = position.getDown();
        }
    }
}
