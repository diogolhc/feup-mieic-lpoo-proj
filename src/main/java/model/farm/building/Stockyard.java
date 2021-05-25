package model.farm.building;

import model.Position;
import model.farm.Animal;
import model.farm.Livestock;
import model.farm.item.Crop;
import model.farm.item.Item;
import model.region.PositionRegion;
import model.region.RectangleRegion;
import model.region.Region;

import java.util.ArrayList;
import java.util.List;

public class Stockyard extends Building {
    private final Livestock livestockType;
    private final List<Animal> animals;

    public Stockyard(Position topLeft, Livestock livestockType) {
        super(topLeft);
        this.livestockType = livestockType;
        this.animals = new ArrayList<>();
    }

    public void addAnimal() {
        // TODO make sure position is different from all other animals
        this.animals.add(new Animal(new Position(0, 0)));
    }

    public void removeAnimal() {
        if (this.animals.size() > 0) {
            this.animals.remove(0);
        }
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public boolean emptyPosition(Position position) {
        for (Animal animal: animals) {
            if (animal.getPosition().equals(position)) return false;
        }
        return true;
    }

    @Override
    public int getWidth() {
        return this.livestockType.getStockyardWidth();
    }

    @Override
    public int getHeight() {
        return this.livestockType.getStockyardHeight();
    }

    @Override
    public Region getUntraversableRegion() {
        return new RectangleRegion(this.getTopLeftPosition(), this.getWidth(), this.getHeight());
    }

    @Override
    public Region getInteractiveRegion() {
        return new PositionRegion(this.getTopLeftPosition().getTranslated(new Position(-1, 3)));
    }

    public Region getAnimalsRegion() {
        return new RectangleRegion(
                this.getTopLeftPosition().getTranslated(new Position(1, 1)),
                this.getWidth() - 2,
                this.getHeight() - 2);
    }

    public Livestock getLivestockType() {
        return this.livestockType;
    }
}
