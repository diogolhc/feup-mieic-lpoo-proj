package viewer.farm;

import gui.Color;
import gui.GUI;
import model.Position;
import model.farm.Farm;

public class FarmRestingViewer extends FarmViewer {
    private final static char RESTING_MARKER = 'Z';
    private final static Color RESTING_MARKER_COLOR = new Color("#00008b");

    public FarmRestingViewer(Farm farm) {
        super(farm);
    }

    @Override
    public void draw(GUI gui) {
        super.draw(gui);
        this.drawRestingMarker(gui);
    }

    private void drawRestingMarker(GUI gui) {
        Position position = this.farm
                .getBuildings()
                .getHouse()
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
