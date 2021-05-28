package controller.command.stockyard;

import controller.command.Command;
import controller.command.farm.stockyard.StopProducingStockyardCommand;
import model.Position;
import model.farm.Inventory;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.Producing;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CollectStockyardCommand {
    private Stockyard stockyard;
    private Producing stateProducing;
    private AnimalProduct animalProduct;
    private Command command;
    private Livestock livestock;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        stateProducing = Mockito.mock(Producing.class);

        livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);

        animalProduct = Mockito.mock(AnimalProduct.class);
        inventory = new Inventory(500);
        inventory.storeItem(animalProduct, 0);

        stockyard = new Stockyard(new Position(0, 0), livestock);

        Mockito.when(stockyard.getLivestockType().getProducedItem()).thenReturn(animalProduct);
        Mockito.when(stateProducing.getCollectAmount()).thenReturn(stockyard.getAnimals().getSize() * 10);
        Mockito.when(stockyard.getLivestockType().getProducedItem().getBaseProducedAmount()).thenReturn(10);
        command = new StopProducingStockyardCommand(stockyard);
    }

    @Test
    public void execute() {
        stockyard.setState(stateProducing);
        Assertions.assertSame(stateProducing, stockyard.getState());
        Assertions.assertEquals(0, inventory.getAmount(animalProduct));
        Assertions.assertEquals(0, stateProducing.getCollectAmount());

        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        Mockito.when(stateProducing.getCollectAmount()).thenReturn(stockyard.getAnimals().getSize() * 10);

        Assertions.assertEquals(3, stockyard.getAnimals().getSize());
        Assertions.assertEquals(30, stateProducing.getCollectAmount());

        command.execute();

        Assertions.assertNotEquals(stateProducing, stockyard.getState());
        Assertions.assertEquals(30, inventory.getAmount(animalProduct));
    }
}
