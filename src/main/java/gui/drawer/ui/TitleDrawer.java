package gui.drawer.ui;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.HorizontalLineDrawer;
import gui.drawer.string.UnderlinedStringDrawer;
import model.Position;

public class TitleDrawer {
    private final GUI gui;
    private static final Color BACKGROUND_COLOR = new Color("#222222");
    private static final Color FOREGROUND_COLOR = new Color("#aaaaaa");
    private static final char UNDERLINE_CHARACTER = '-';

    public TitleDrawer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Position position, String title) {
        UnderlinedStringDrawer drawer = new UnderlinedStringDrawer(
                this.gui, this.BACKGROUND_COLOR, this.FOREGROUND_COLOR, this.UNDERLINE_CHARACTER);
        drawer.draw(position, title);
    }
}
