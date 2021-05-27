package model.farm.building;

import gui.Color;
import model.InGameTime;
import model.Position;
import model.farm.entity.Animal;
import model.farm.Currency;
import model.farm.data.Livestock;
import model.farm.building.stockyard_state.NotProducing;
import model.farm.building.stockyard_state.StockyardState;
import model.region.PositionRegion;
import model.region.RectangleRegion;
import model.region.Region;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public Currency getBuildPrice() {
        return this.livestockType.getBuildPrice();
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
        return new RectangleRegion(
                this.getTopLeftPosition().getRight(),
                this.getWidth() - 1,
                this.getHeight());
    }

    @Override
    public Region getInteractiveRegion() {
        return new PositionRegion(this.getTopLeftPosition().getTranslated(new Position(0, 3)));
    }

    @Override
    public String getName() {
        return livestockType.getAnimalName() + " S.Y.";
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

    public void removeAnimal() {
        if (this.animals.size() > 0) {
            this.animals.remove(0);
        }
    }

    public boolean isFull() {
        return this.animals.size() >= livestockType.getMaxNumAnimals();
    }

    public boolean isEmpty() { return this.animals.size() <= 0; }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public boolean isAnimalAt(Position position) {
        for (Animal animal: animals) {
            if (animal.getPosition().equals(position)) return true;
        }
        return false;
    }

    private RectangleRegion getAnimalsRegion() {
        return new RectangleRegion(
                this.getTopLeftPosition().getTranslated(new Position(2, 1)),
                this.getWidth() - 3,
                this.getHeight() - 2);
    }

    public boolean isTraversableForAnimals(Position position) {
        return this.getAnimalsRegion().contains(position)
                && !this.isAnimalAt(position);
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

    public InGameTime getRemainingTime() {
        return this.state.getRemainingTime();
    }

    public void setRemainingTime(InGameTime time) {
        this.state.setRemainingTime(time);
    }

    public void changeCollectAmount(double quantity) {
        this.state.changeCollectAmount(quantity);
    }

    public char getAnimalChar() {
        return this.getLivestockType().getAnimalChar();
    }

    public Color getAnimalColor() {
        return this.state.getAnimalColor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Stockyard stockyard = (Stockyard) o;
        return Objects.equals(this.getTopLeftPosition(), stockyard.getTopLeftPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTopLeftPosition());
    }
}
