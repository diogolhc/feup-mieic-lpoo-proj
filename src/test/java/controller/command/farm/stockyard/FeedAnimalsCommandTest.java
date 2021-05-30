package controller.command.farm.stockyard;

import controller.command.Command;
import model.Position;
import model.farm.Inventory;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.Producing;
import model.farm.building.stockyard.state.ReadyToCollect;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FeedAnimalsCommandTest {
    private Stockyard stockyard;
    private NotProducing stateNotProducing;
    private Producing stateProducing;
    private ReadyToCollect stateReadyToCollect;
    private AnimalProduct animalProduct;
    private Command command;
    private Crop crop;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        this.stateReadyToCollect = Mockito.mock(ReadyToCollect.class);
        this.stateNotProducing = Mockito.mock(NotProducing.class);
        this.stateProducing = Mockito.mock(Producing.class);

        this.animalProduct = Mockito.mock(AnimalProduct.class);
        this.crop = Mockito.mock(Crop.class);
        this.inventory = Mockito.mock(Inventory.class);

        Livestock livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(livestock.getStockyardHeight()).thenReturn(4);
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(1);
        Mockito.when(livestock.getFoodCrop()).thenReturn(this.crop);
        Mockito.when(livestock.getRequiredFood()).thenReturn(10);
        Mockito.when(livestock.getProducedItem()).thenReturn(this.animalProduct);

        this.stockyard = new Stockyard(new Position(0, 0), livestock);

        this.stockyard.getAnimals().addAnimal();

        this.command = new FeedAnimalsCommand(this.stockyard, this.inventory);
    }

    @Test
    public void executeReady() {
        this.stockyard.setState(this.stateReadyToCollect);
        this.command.execute();
        Assertions.assertSame(this.stateReadyToCollect, this.stockyard.getState());
        Mockito.verifyNoInteractions(this.inventory);
    }

    @Test
    public void executeNotProducing() {
        this.stockyard.setState(this.stateNotProducing);

        this.command.execute();

        Assertions.assertTrue(this.stockyard.getState() instanceof Producing);
        Mockito.verify(this.inventory, Mockito.times(1)).removeItem(this.crop, 10);
        Mockito.verify(this.inventory, Mockito.never()).removeItem(Mockito.eq(this.animalProduct), Mockito.anyInt());
        Mockito.verify(this.inventory, Mockito.never()).storeItem(Mockito.eq(this.animalProduct), Mockito.anyInt());
    }

    @Test
    public void executeProducing() {
        this.stockyard.setState(this.stateProducing);
        this.command.execute();
        Assertions.assertSame(this.stateProducing, this.stockyard.getState());
        Mockito.verifyNoInteractions(this.inventory);
    }
}
