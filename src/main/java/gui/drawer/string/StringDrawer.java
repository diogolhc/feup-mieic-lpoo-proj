package gui.drawer.string;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.HorizontalLineDrawer;
import model.Position;

public class StringDrawer {
    private final GUI gui;
    private final Color backgroundColor;
    private final Color foregroundColor;

    public StringDrawer(GUI gui, Color backgroundColor, Color foregroundColor) {
        this.gui = gui;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public void draw(Position position, String s) {
        this.gui.setBackgroundColor(this.backgroundColor);
        this.gui.setForegroundColor(this.foregroundColor);
        this.gui.drawString(position.getX(), position.getY(), s);
    }
}
