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
        stateReadyToCollect = Mockito.mock(ReadyToCollect.class);
        stateNotProducing = Mockito.mock(NotProducing.class);
        stateProducing = Mockito.mock(Producing.class);

        animalProduct = Mockito.mock(AnimalProduct.class);
        crop = Mockito.mock(Crop.class);
        inventory = Mockito.mock(Inventory.class);

        Livestock livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(1);
        Mockito.when(livestock.getFoodCrop()).thenReturn(crop);
        Mockito.when(livestock.getRequiredFood()).thenReturn(10);
        Mockito.when(livestock.getProducedItem()).thenReturn(animalProduct);

        stockyard = new Stockyard(new Position(0, 0), livestock);

        stockyard.getAnimals().addAnimal(new Position(0, 0));

        command = new FeedAnimalsCommand(stockyard, inventory);
    }

    @Test
    public void executeReady() {
        stockyard.setState(stateReadyToCollect);
        command.execute();
        Assertions.assertSame(stateReadyToCollect, stockyard.getState());
        Mockito.verifyNoInteractions(inventory);
    }

    @Test
    public void executeNotProducing() {
        stockyard.setState(stateNotProducing);

        command.execute();

        Assertions.assertTrue(stockyard.getState() instanceof Producing);
        Mockito.verify(inventory, Mockito.times(1)).removeItem(crop, 10);
        Mockito.verify(inventory, Mockito.never()).removeItem(Mockito.eq(animalProduct), Mockito.anyInt());
        Mockito.verify(inventory, Mockito.never()).storeItem(Mockito.eq(animalProduct), Mockito.anyInt());
    }

    @Test
    public void executeProducing() {
        stockyard.setState(stateProducing);
        command.execute();
        Assertions.assertSame(stateProducing, stockyard.getState());
        Mockito.verifyNoInteractions(inventory);
    }
}
