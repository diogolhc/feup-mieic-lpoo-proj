package model.farm.building.stockyard;

import model.Position;
import model.farm.entity.Animal;
import model.region.RectangleRegion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StockyardAnimals implements Serializable {
    private final List<Animal> animals;
    private final int maxNumAnimals;
    private RectangleRegion animalsRegion;

    public StockyardAnimals(RectangleRegion animalsRegion, int maxNumAnimals) {
        this.animalsRegion = animalsRegion;
        this.maxNumAnimals = maxNumAnimals;
        this.animals = new ArrayList<>();
    }

    public void addAnimal() {
        Position animalPosition;

        do {
            int x = (int) (Math.random() * this.animalsRegion.getWidth());
            int y = (int) (Math.random() * this.animalsRegion.getHeight());

            animalPosition = this.animalsRegion.getTopLeftPosition().getTranslated(new Position(x, y));
        } while (isAnimalAt(animalPosition));

        addAnimal(animalPosition);
    }

    public void addAnimal(Position animalPosition) {
        this.addAnimal(new Animal(animalPosition));
    }

    public void addAnimal(Animal animal) {
        if (this.animals.size() < this.maxNumAnimals) {
            this.animals.add(animal);
        }
    }

    public void removeAnimal() {
        if (this.animals.size() > 0) {
            this.animals.remove(0);
        }
    }

    public boolean isFull() {
        return this.animals.size() >= this.maxNumAnimals;
    }

    public boolean isEmpty() {
        return this.animals.size() <= 0;
    }

    public int getSize() {
        return this.animals.size();
    }

    public List<Animal> getList() {
        return this.animals;
    }

    public boolean isAnimalAt(Position position) {
        for (Animal animal: this.animals) {
            if (animal.getPosition().equals(position)) return true;
        }
        return false;
    }

    public boolean canAnimalMoveTo(Position position) {
        return this.animalsRegion.contains(position) && !this.isAnimalAt(position);
    }

    public void setAnimalsRegionPosition(Position position) {
        this.animalsRegion = this.animalsRegion.getAt(position);
    }

    public RectangleRegion getRegion() {
        return this.animalsRegion;
    }
}
