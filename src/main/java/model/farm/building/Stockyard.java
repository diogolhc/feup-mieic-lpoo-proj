package model.farm.building;

import model.Position;
import model.farm.Animals.Animal;
import model.region.EdificeUntraversableRegion;
import model.region.PositionRegion;
import model.region.RectangleRegion;
import model.region.Region;

import java.util.ArrayList;
import java.util.List;

public class Stockyard<T extends Animal> extends Building {
    private List<T> animals;
    private int width;
    private int height;

    public Stockyard(Position topLeft) {
        this(topLeft, 7, 7);
    }

    public Stockyard(Position topLeft, int width, int height) {
        super(topLeft);
        this.animals = new ArrayList<T>();
        this.width = width;
        this.height = height;
    }

    public Stockyard(Position topleft, List<T> animals) {
        this(topleft, 7, 7);
        this.animals = animals;
    }

    public void addAnimal(T animal) {
        this.animals.add(animal);
    }

    public List<T> getAnimals() {
        return animals;
    }


    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public Region getUntraversableRegion() {
        return new RectangleRegion(this.getTopLeftPosition(), this.getWidth(), this.getHeight());
    }

    @Override
    public Region getInteractiveRegion() {
        return new PositionRegion(this.getTopLeftPosition().getTranslated(new Position(-1, 3)));
    }
}
