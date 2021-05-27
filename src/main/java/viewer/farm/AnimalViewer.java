package viewer.farm;

import gui.Color;
import gui.GUI;
import model.Position;
import model.farm.Animal;

public class AnimalViewer {

    public void draw(Animal animal, char animalChar, GUI gui, Color foregroundColor) {
        Position position = animal.getPosition();

        Color backgroundColor = gui.getBackgroundColor(position.getX(), position.getY());
        gui.setBackgroundColor(backgroundColor);
        gui.setForegroundColor(foregroundColor);
        gui.drawChar(position.getX(), position.getY(), animalChar);
    }
}
