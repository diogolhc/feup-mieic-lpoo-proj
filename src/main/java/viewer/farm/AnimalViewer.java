package viewer.farm;

import gui.Color;
import gui.GUI;
import model.Position;
import model.farm.Animals.Animal;

public class AnimalViewer {

    public void draw(Animal animal, GUI gui) {
        Position position = animal.getPosition();

        Color backgroundColor = gui.getBackgroundColor(position.getX(), position.getY());
        gui.setBackgroundColor(backgroundColor);
        gui.setForegroundColor(new Color("#223366"));
        gui.drawChar(position.getX(), position.getY(), animal.getChar());
    }
}
