package gui.drawer.ui;

import gui.GUI;
import gui.drawer.shape.HorizontalLineDrawer;
import model.Position;

public class TitleDrawer {
    private final GUI gui;
    private final String backgroundColor;
    private final String foregroundColor;
    private static final char UNDERLINE_CHARACTER = '-';

    public TitleDrawer(GUI gui, String backgroundColor, String foregroundColor) {
        this.gui = gui;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public void draw(Position position, String title) {
        this.gui.setBackgroundColor(this.backgroundColor);
        this.gui.setForegroundColor(this.foregroundColor);
        this.gui.drawString(position.getX(), position.getY(), title);

        HorizontalLineDrawer hLineDrawer = new HorizontalLineDrawer(
                this.gui, this.backgroundColor, this.foregroundColor, UNDERLINE_CHARACTER);
        hLineDrawer.draw(position.getDown(), title.length());
    }
}
