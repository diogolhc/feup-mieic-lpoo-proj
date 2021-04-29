package gui.drawer;

import gui.GUI;
import model.Position;

public class HorizontalLineDrawer {
    private GUI gui;
    private String backgroundColor;
    private String foregroundColor;
    private char character;

    public HorizontalLineDrawer(GUI gui, String backgroundColor, String foregroundColor, char character) {
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
            position = position.getRight();
        }
    }
}
