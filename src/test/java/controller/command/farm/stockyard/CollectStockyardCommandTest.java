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
    private Inventory inventory;
    private Command command;

    @BeforeEach
    public void setUp() {
        stateReadyToCollect = Mockito.mock(ReadyToCollect.class);
        stateNotProducing = Mockito.mock(NotProducing.class);
        stateProducing = Mockito.mock(Producing.class);

        inventory = Mockito.mock(Inventory.class);

        stockyard = new Stockyard(new Position(0, 0), Mockito.mock(Livestock.class));

        animalProduct = Mockito.mock(AnimalProduct.class);
        Mockito.when(stockyard.getLivestockType().getProducedItem()).thenReturn(animalProduct);
        Mockito.when(stateReadyToCollect.getCollectAmount()).thenReturn(10);
        command = new CollectStockyardCommand(inventory, stockyard);
    }

    @Test
    public void executeReady() {
        stockyard.setState(stateReadyToCollect);

        command.execute();

        Assertions.assertTrue(stockyard.getState() instanceof NotProducing);
        Mockito.verify(inventory, Mockito.times(1)).storeItem(animalProduct, 10);
    }

    @Test
    public void executeNotProducing() {
        stockyard.setState(stateNotProducing);
        command.execute();
        Assertions.assertSame(stateNotProducing, stockyard.getState());
        Mockito.verifyNoInteractions(inventory);
    }

    @Test
    public void executeProducing() {
        stockyard.setState(stateProducing);
        command.execute();
        Assertions.assertSame(stateProducing, stockyard.getState());
        Mockito.verifyNoInteractions(inventory);
    }
}
