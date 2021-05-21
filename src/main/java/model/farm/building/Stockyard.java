package model.farm.building;

import model.Position;
import model.farm.Animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class Stockyard<T extends Animal> extends Building {
    public static final int STOCKYARD_SIZE = 7;
    private List<T> animals;

    public Stockyard(Position topLeft) {
        super(topLeft);
        this.animals = new ArrayList<T>();
    }

    public Stockyard(Position topleft, List<T> animals) {
        super(topleft);
        this.animals = animals;
    }

    public void addAnimal(T animal) {
        this.animals.add(animal);
    }

    @Override
    public boolean isTraversable(Position position) {
        position = position.getRelativeTo(this.getTopLeftPosition());
        int x = position.getX();
        int y = position.getY();
        if (y == 0 && x >= 0 && x <= 6) return false;
        if (y >= 1 && y <= 6 && x >= 0 && x <= 6) return false;

        return true;
    }

    @Override
    public boolean isInInteractiveZone(Position position) {
        return position.equals(this.getTopLeftPosition().getTranslated(new Position(-1, 3)));
    }

}
