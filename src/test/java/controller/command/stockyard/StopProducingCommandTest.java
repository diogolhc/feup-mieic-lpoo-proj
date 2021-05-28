package controller.command.stockyard;

import controller.command.Command;
import controller.command.farm.stockyard.StopProducingStockyardCommand;
import model.Position;
import model.farm.Inventory;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.Producing;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StopProducingCommandTest {


    private Stockyard stockyard;
    private Producing stateProducing;
    private NotProducing stateNotProducing;
    private AnimalProduct animalProduct;
    private Command command;
    private Livestock livestock;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        stateNotProducing = Mockito.mock(NotProducing.class);
        stateProducing = Mockito.mock(Producing.class);

        livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);

        animalProduct = Mockito.mock(AnimalProduct.class);
        inventory = new Inventory(500);
        inventory.storeItem(animalProduct, 0);

        stockyard = new Stockyard(new Position(0, 0), livestock);

        Mockito.when(stateProducing.getCollectAmount()).thenReturn(stockyard.getAnimals().getSize() * 10);
        Mockito.when(stockyard.getLivestockType().getProducedItem()).thenReturn(animalProduct);

        command = new StopProducingStockyardCommand(stockyard);
    }

    @Test
    public void execute() {
        stockyard.setState(stateProducing);
        Assertions.assertTrue(stockyard.getState() instanceof Producing);

        Assertions.assertEquals(0, inventory.getAmount(animalProduct));
        Assertions.assertEquals(0, stateProducing.getCollectAmount());

        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        Mockito.when(stateProducing.getCollectAmount()).thenReturn(stockyard.getAnimals().getSize() * 10);

        Assertions.assertEquals(3, stockyard.getAnimals().getSize());
        Assertions.assertEquals(30, stockyard.getState().getCollectAmount());

        command.execute();

        Assertions.assertNotEquals(stateProducing, stockyard.getState());
        Assertions.assertTrue(stockyard.getState() instanceof NotProducing);
    }
}
