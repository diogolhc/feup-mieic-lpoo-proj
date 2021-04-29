package gui.drawer;

import com.googlecode.lanterna.TextColor;
import gui.GUI;
import model.Farm;
import model.Position;

public class FencesDrawer {
    private GUI gui;
    private static final String FENCES_COLOR = "#846f46";
    private static final String FENCES_BACKGROUND = "#7EC850";
    private static final char HORIZONTAL_FENCE = '-';
    private static final char VERTICAL_FENCE = '|';
    private static final char CORNER_FENCE = '+';

    public FencesDrawer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Position position, int width, int height) {
        if (width > 2) {
            HorizontalLineDrawer hLineDrawer = new HorizontalLineDrawer(
                    this.gui, FENCES_BACKGROUND, FENCES_COLOR, HORIZONTAL_FENCE);
            hLineDrawer.draw(position.getRight(), width - 2);
        }

        if (height > 2) {
            VerticalLineDrawer vLineDrawer = new VerticalLineDrawer(
                    this.gui, FENCES_BACKGROUND, FENCES_COLOR, VERTICAL_FENCE);
            vLineDrawer.draw(position.getDown(), height - 2);
        }

        int x = position.getX();
        int y = position.getY();

        gui.drawChar(x, y, CORNER_FENCE);
        gui.drawChar(x + width - 1, y, CORNER_FENCE);
        gui.drawChar(x, y + height - 1, CORNER_FENCE);
        gui.drawChar(x + width - 1, y + height - 1, CORNER_FENCE);
    }
}
