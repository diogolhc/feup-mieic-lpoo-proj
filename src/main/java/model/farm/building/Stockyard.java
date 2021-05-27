package model.farm.building;

import gui.Color;
import model.Position;
import model.farm.Animal;
import model.farm.Currency;
import model.farm.Livestock;
import model.farm.building.stockyard_state.NotProducing;
import model.farm.building.stockyard_state.StockyardState;
import model.region.PositionRegion;
import model.region.RectangleRegion;
import model.region.Region;

import java.util.ArrayList;
import java.util.List;

public class Stockyard extends Buildable {
    private final Livestock livestockType;
    private final List<Animal> animals;
    private StockyardState state;

    public Stockyard(Position topLeft, Livestock livestockType) {
        super(topLeft);
        this.livestockType = livestockType;
        this.animals = new ArrayList<>();
        this.state = new NotProducing();
    }

    public void addAnimal() {
        Position animalPosition;

        do {
            int x = (int) (Math.random() * this.getAnimalsRegion().getWidth());
            int y = (int) (Math.random() * this.getAnimalsRegion().getHeight());

            animalPosition = this.getAnimalsRegion().getTopLeftPosition().getTranslated(new Position(x, y));
        } while (isAnimalAt(animalPosition));

        addAnimal(animalPosition);
    }

    public void addAnimal(Position animalPosition) {
        this.animals.add(new Animal(animalPosition));
    }

    public boolean isFull() {
        return this.animals.size() >= livestockType.getMaxNumAnimals();
    }

    public boolean isEmpty() { return this.animals.size() <= 0; }

    public void removeAnimal() {
        if (this.animals.size() > 0) {
            this.animals.remove(0);
        }
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public boolean isTraversableForAnimals(Position position) {
        return this.getAnimalsRegion().contains(position)
                && !this.isAnimalAt(position);
    }

    public boolean isAnimalAt(Position position) {
        for (Animal animal: animals) {
            if (animal.getPosition().equals(position)) return true;
        }
        return false;
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
        Position position = new Position(this.getTopLeftPosition().getX() + 1, this.getTopLeftPosition().getY());
        return new RectangleRegion(position, this.getWidth() - 1, this.getHeight());
    }

    @Override
    public Region getInteractiveRegion() {
        return new PositionRegion(this.getTopLeftPosition().getTranslated(new Position(0, 3)));
    }

    @Override
    public String getName() {
        return livestockType.getAnimalName() + " S.Y.";
    }

    private RectangleRegion getAnimalsRegion() {
        return new RectangleRegion(
                this.getTopLeftPosition().getTranslated(new Position(2, 1)),
                this.getWidth() - 3,
                this.getHeight() - 2);
    }

    public Livestock getLivestockType() {
        return this.livestockType;
    }

    public int getFeedQuantity() {
        return this.animals.size() * this.livestockType.getRequiredFood();
    }

    public void setState(StockyardState state) {
        this.state = state;
    }

    public StockyardState getState() {
        return state;
    }

    @Override
    public Currency getBuildPrice() {
        return this.livestockType.getBuildPrice();
    }

    public void changeProductAmount(double quantity) {
        this.state.changeCollectAmount(quantity);
    }

    public char getAnimalChar() {
        return this.getLivestockType().getAnimalChar();
    }

    public Color getAnimalColor() {
        return this.state.getAnimalColor();
    }
}
