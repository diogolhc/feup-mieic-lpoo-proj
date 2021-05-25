package viewer.farm;

import gui.Color;
import gui.GUI;
import model.Position;
import model.farm.DemolishMarker;
import model.farm.Farm;

public class FarmSleepingViewer extends FarmViewer {
    private final static char SLEEPING_MARKER = 'Z';
    private final static Color SLEEPING_MARKER_COLOR = new Color("#00008b");

    public FarmSleepingViewer(Farm farm) {
        super(farm);
    }

    @Override
    public void draw(GUI gui) {
        super.draw(gui);
        this.drawSleepingMarker(gui);
    }

    private void drawSleepingMarker(GUI gui) {
        Position position = this.farm
                .getBuildings()
                .getHouse()
                .getTopLeftPosition()
                .getTranslated(new Position(2, 2));

        Color backgroundColor = gui.getBackgroundColor(position.getX(), position.getY());
        gui.setBackgroundColor(backgroundColor);
        gui.setForegroundColor(SLEEPING_MARKER_COLOR);
        gui.drawChar(position.getX(), position.getY(), SLEEPING_MARKER);

        position = position.getTranslated(new Position(1, 1));

        gui.drawChar(position.getX(), position.getY(), SLEEPING_MARKER);
    }
}
