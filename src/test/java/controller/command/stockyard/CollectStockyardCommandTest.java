package controller.command.stockyard;

import controller.command.Command;
import controller.command.farm.stockyard.CollectStockyardCommand;
import controller.command.farm.stockyard.StopProducingStockyardCommand;
import model.Position;
import model.farm.Inventory;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.Producing;
import model.farm.building.stockyard.state.ReadyToCollect;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CollectStockyardCommandTest {
    private Stockyard stockyard;
    private NotProducing stateNotProducing;
    private Producing stateProducing;
    private ReadyToCollect stateReadyToCollect;
    private AnimalProduct animalProduct;
    private Command command;
    private Livestock livestock;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        stateReadyToCollect = Mockito.mock(ReadyToCollect.class);
        stateNotProducing = Mockito.mock(NotProducing.class);
        stateProducing = Mockito.mock(Producing.class);

        livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);

        animalProduct = Mockito.mock(AnimalProduct.class);
        inventory = new Inventory(500);
        inventory.storeItem(animalProduct, 0);

        stockyard = new Stockyard(new Position(0, 0), livestock);

        Mockito.when(stockyard.getLivestockType().getProducedItem()).thenReturn(animalProduct);
        Mockito.when(stateReadyToCollect.getCollectAmount()).thenReturn(stockyard.getAnimals().getSize() * 10);
        command = new CollectStockyardCommand(inventory, stockyard);
    }

    @Test
    public void executeReady() {
        stockyard.setState(stateReadyToCollect);
        Assertions.assertTrue(stockyard.getState() instanceof ReadyToCollect);

        Assertions.assertEquals(0, inventory.getAmount(animalProduct));
        Assertions.assertEquals(0, stateReadyToCollect.getCollectAmount());

        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        Mockito.when(stateReadyToCollect.getCollectAmount()).thenReturn(stockyard.getAnimals().getSize() * 10);

        Assertions.assertEquals(3, stockyard.getAnimals().getSize());
        Assertions.assertEquals(30, stateReadyToCollect.getCollectAmount());

        command.execute();

        Assertions.assertNotEquals(stateReadyToCollect, stockyard.getState());
        Assertions.assertTrue(stockyard.getState() instanceof NotProducing);

        Assertions.assertEquals(30, inventory.getAmount(animalProduct));
    }

    @Test
    public void executeNotProducing() {
        stockyard.setState(stateNotProducing);
        command.execute();
        Assertions.assertSame(stateNotProducing, stockyard.getState());
    }

    @Test
    public void executeProducing() {
        stockyard.setState(stateProducing);
        command.execute();
        Assertions.assertSame(stateProducing, stockyard.getState());
    }
}
