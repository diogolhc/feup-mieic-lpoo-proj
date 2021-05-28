package model.farm.building.stockyard;

import model.Position;
import model.farm.entity.Animal;
import model.region.RectangleRegion;

import java.util.ArrayList;
import java.util.List;

public class StockyardAnimals {
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
        if (this.animals.size() < this.maxNumAnimals) {
            this.animals.add(new Animal(animalPosition));
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

    public void setAnimalsRegion(RectangleRegion region) {
        this.animalsRegion = region;
    }

    public boolean canAnimalMoveTo(Position position) {
        return this.animalsRegion.contains(position) && !this.isAnimalAt(position);
    }
}
