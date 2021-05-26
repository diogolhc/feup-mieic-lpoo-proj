package model.farm.building;

import model.Position;
import model.farm.Animal;
import model.farm.Currency;
import model.farm.Livestock;
import model.farm.building.stockyard_state.NotProducing;
import model.farm.building.stockyard_state.StockyardState;
import model.farm.item.Crop;
import model.farm.item.Item;
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
        // TODO make sure position is different from all other animals
        // TODO THis is just here to debug
        Position animalPosition = new Position(getTopLeftPosition().getX() + 2, getTopLeftPosition().getY() + 1);
        RectangleRegion animalRegion = (RectangleRegion) this.getAnimalsRegion();

        for (int i = 0; i < animalRegion.getWidth() - 1; i++) {
            for (int j = 0; j < animalRegion.getHeight(); j++) {
                animalPosition = new Position(getTopLeftPosition().getX() + 2 + i,
                        getTopLeftPosition().getY() + 2 + j);

                boolean emptyPosition = true;
                for (Animal animal : animals) {
                    if (animalPosition.equals(animal.getPosition())) {
                        emptyPosition = false;
                        break;
                    }
                }
                if (emptyPosition) break;

            }
        }
        this.animals.add(new Animal(animalPosition));
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

    public Region getAnimalsRegion() {
        return new RectangleRegion(
                this.getTopLeftPosition().getTranslated(new Position(2, 1)),
                this.getWidth() - 3,
                this.getHeight() - 2);
    }

    public Livestock getLivestockType() {
        return this.livestockType;
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
        this.state.changeProductAmount(quantity);
    }
}
