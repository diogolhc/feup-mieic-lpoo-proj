package viewer.farm.element;

import gui.Color;
import gui.GUI;
import model.Position;
import model.farm.building.Edifice;

public class RestingMarkerViewer {
    public static final char RESTING_MARKER = 'Z';
    public static final Color RESTING_MARKER_COLOR = new Color("#00008b");

    public void draw(Edifice house, GUI gui) {
        Position position = house
                .getTopLeftPosition()
                .getTranslated(new Position(2, 2));

        Color backgroundColor = gui.getBackgroundColor(position.getX(), position.getY());
        gui.setBackgroundColor(backgroundColor);
        gui.setForegroundColor(RESTING_MARKER_COLOR);
        gui.drawChar(position.getX(), position.getY(), RESTING_MARKER);

        position = position.getTranslated(new Position(1, 1));

        gui.drawChar(position.getX(), position.getY(), RESTING_MARKER);
    }
}
