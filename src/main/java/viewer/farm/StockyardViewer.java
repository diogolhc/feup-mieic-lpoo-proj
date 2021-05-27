package viewer.farm;

import gui.Color;
import gui.GUI;
import model.Position;
import model.farm.Animal;
import model.farm.building.Stockyard;

public class StockyardViewer {
    private static final Color PATH_COLOR = new Color("#be9b7b");
    private static final Color FENCE_DOOR_COLOR = PATH_COLOR;

    public void draw(Stockyard stockyard, GUI gui) {

        FencesViewer fencesViewer = new FencesViewer();

        Position fencesPosition = new Position(stockyard.getTopLeftPosition().getX() + 1,
                    stockyard.getTopLeftPosition().getY());
        fencesViewer.draw(fencesPosition, stockyard.getWidth() - 1, stockyard.getHeight(), gui);

        gui.setForegroundColor(FENCE_DOOR_COLOR);
        gui.drawChar(stockyard.getTopLeftPosition().getX() + 1, stockyard.getTopLeftPosition().getY() + 3, '|');

        gui.setBackgroundColor(PATH_COLOR);
        gui.drawChar(stockyard.getTopLeftPosition().getX(), stockyard.getTopLeftPosition().getY() + 3, ' ');

        for (Animal animal: stockyard.getAnimals()) {
            AnimalViewer animalViewer = new AnimalViewer();
            animalViewer.draw(animal, stockyard.getLivestockType().getAnimalChar(), gui, stockyard.getState().getColor());
        }
    }
}
