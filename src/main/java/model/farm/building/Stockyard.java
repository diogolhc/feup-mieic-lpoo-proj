package model.farm.building;

import model.Position;
import model.farm.Animal;

import java.util.ArrayList;
import java.util.List;

public class Stockyard extends Building {
    public static final int STOCKYARD_SIZE = 6;
    private List<Animal> animals;

    public Stockyard(Position topLeft) {
        super(topLeft);
        this.animals = new ArrayList<Animal>();
    }

    public Stockyard(Position topleft, List<Animal> animals) {
        super(topleft);
        this.animals = animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    @Override
    public boolean isTraversable(Position position) {
        position = position.getRelativeTo(this.getTopLeftPosition());
        int x = position.getX();
        int y = position.getY();
        if (y == 0 && x >= 0 && x <= 5) return false;
        if (y >= 1 && y <= 5 && x >= 0 && x <= 5) return false;

        return true;
    }

    @Override
    public boolean isInInteractiveZone(Position position) {
        return position.equals(this.getTopLeftPosition().getTranslated(new Position(4, 5)));
    }

}
