package gui.drawer.string;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.HorizontalLineDrawer;
import model.Position;

public class UnderlinedStringDrawer {
    private final GUI gui;
    private final Color backgroundColor;
    private final Color foregroundColor;
    private final char underlineCharacter;

    public UnderlinedStringDrawer(GUI gui, Color backgroundColor, Color foregroundColor, char underlineCharacter) {
        this.gui = gui;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.underlineCharacter = underlineCharacter;
    }

    public void draw(Position position, String title) {
        drawTitle(position, title);
        drawUnderline(position, title);
    }

    private void drawTitle(Position position, String title) {
        this.gui.setBackgroundColor(this.backgroundColor);
        this.gui.setForegroundColor(this.foregroundColor);
        this.gui.drawString(position.getX(), position.getY(), title);
    }

    private void drawUnderline(Position position, String title) {
        HorizontalLineDrawer hLineDrawer = new HorizontalLineDrawer(
                this.gui, this.backgroundColor, this.foregroundColor, this.underlineCharacter);
        hLineDrawer.draw(position.getDown(), title.length());
    }
}
