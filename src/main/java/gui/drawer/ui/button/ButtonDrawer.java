package gui.drawer.ui.button;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.BoxDrawer;
import model.Position;

public class ButtonDrawer {
    private final GUI gui;
    private final String title;
    private final int width;
    private int height;
    private Color backgroundColor;
    private Color foregroundColor;

    public ButtonDrawer(GUI gui, String title, int width, int height, Color backgroundColor, Color foregroundColor) {
        this.gui = gui;
        this.title = title;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public void draw(Position position) {
        BoxDrawer boxDrawer = new BoxDrawer(this.gui, backgroundColor, foregroundColor);
        // TODO handle when title doesn't fit
        boxDrawer.draw(position, this.width, this.height);
        Position titlePosition = position.getRight().getDown();
        this.gui.drawString(titlePosition.getX(), titlePosition.getY(), this.title);
    }
}
