package viewer.farm.element.entity;

import gui.Color;
import gui.GUI;
import model.Position;
import model.farm.Animal;
import model.farm.Livestock;

public class AnimalViewer {
    public void draw(Animal animal, char animalChar, Color animalColor, GUI gui) {
        Position position = animal.getPosition();

        Color backgroundColor = gui.getBackgroundColor(position.getX(), position.getY());
        gui.setBackgroundColor(backgroundColor);
        gui.setForegroundColor(animalColor);
        gui.drawChar(position.getX(), position.getY(), animalChar);
    }
}
