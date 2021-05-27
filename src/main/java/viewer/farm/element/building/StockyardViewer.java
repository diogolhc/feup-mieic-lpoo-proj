package viewer.farm.element.building;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.BoxDrawer;
import model.Position;
import model.farm.entity.Animal;
import model.farm.building.Stockyard;
import viewer.farm.element.entity.AnimalViewer;

public class StockyardViewer {
    public static final Color FENCE_DOOR_COLOR = Color.HIGHLIGHTED_FLOOR;

    public void draw(Stockyard stockyard, GUI gui) {
        Position fencesPosition = stockyard.getTopLeftPosition().getTranslated(new Position(1, 0));
        drawFences(fencesPosition, stockyard.getWidth() - 1, stockyard.getHeight(), gui);
        drawAnimals(stockyard, gui);
        drawInteractiveFloor(stockyard.getTopLeftPosition().getTranslated(new Position(0, 3)), gui);
    }

    private void drawInteractiveFloor(Position position, GUI gui) {
        gui.setBackgroundColor(Color.HIGHLIGHTED_FLOOR);
        gui.drawChar(position.getX(), position.getY(), ' ');
    }

    private void drawFences(Position position, int width, int height, GUI gui) {
        BoxDrawer fencesDrawer = new BoxDrawer(gui, Color.GRASS, Color.WOOD);
        fencesDrawer.draw(position, width, height);

        drawFencesDoor(position.getTranslated(new Position(0, 3)), gui);
    }

    private void drawFencesDoor(Position position, GUI gui) {
        gui.setForegroundColor(FENCE_DOOR_COLOR);
        gui.drawChar(position.getX(), position.getY(), '|');
    }

    private void drawAnimals(Stockyard stockyard, GUI gui) {
        AnimalViewer animalViewer = new AnimalViewer();
        char animalChar = stockyard.getAnimalChar();
        Color animalColor = stockyard.getAnimalColor();

        for (Animal animal: stockyard.getAnimals()) {
            animalViewer.draw(animal, animalChar, animalColor, gui);
        }
    }
}
