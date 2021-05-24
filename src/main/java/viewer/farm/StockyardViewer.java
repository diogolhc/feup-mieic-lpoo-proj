package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.BoxDrawer;
import gui.drawer.shape.FilledRectangleDrawer;
import gui.drawer.shape.VerticalLineDrawer;
import model.Position;
import model.farm.Animals.Animal;
import model.farm.building.Stockyard;

public class StockyardViewer {
    private static final Color FENCES_COLOR = new Color("#695836");
    private static final Color FENCES_BACKGROUND = new Color("#7EC850");
    private static final Color BACKGROUND_COLOR = new Color("#be9b7b");
    private final static Color PATH_COLOR = new Color("#be9b7b");

    public void draw(Stockyard stockyard, GUI gui) {

        BoxDrawer drawer = new BoxDrawer(gui, FENCES_BACKGROUND, FENCES_COLOR);
        drawer.draw(stockyard.getTopLeftPosition(), Stockyard.STOCKYARD_SIZE, Stockyard.STOCKYARD_SIZE);

        gui.setForegroundColor(PATH_COLOR);
        gui.drawChar(stockyard.getTopLeftPosition().getX(), stockyard.getTopLeftPosition().getY()+3, '|');

        gui.setBackgroundColor(PATH_COLOR);
        gui.drawChar(stockyard.getTopLeftPosition().getX() - 1, stockyard.getTopLeftPosition().getY() + 3, ' ');

        for (Object animal : stockyard.getAnimals()) {
            AnimalViewer animalViewer = new AnimalViewer();
            animalViewer.draw((Animal) animal, gui);
        }
    }

}
