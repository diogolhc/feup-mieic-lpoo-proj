package controller.command.farm.stockyard;

import controller.command.Command;
import model.Position;
import model.farm.Inventory;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
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
    private AnimalProduct animalProduct;
    private Command command;
    private Crop crop;
    private Livestock livestock;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        stateNotProducing = Mockito.mock(NotProducing.class);

        livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);


        animalProduct = new AnimalProduct("MILK");
        crop = new Crop("WHEAT");
        inventory = new Inventory(500);
        inventory.storeItem(crop, 100);

        stockyard = new Stockyard(new Position(0, 0), livestock);

        Mockito.when(stockyard.getLivestockType().getFoodCrop()).thenReturn(crop);
        Mockito.when(stockyard.getLivestockType().getRequiredFood()).thenReturn(10);
        Mockito.when(stockyard.getLivestockType().getProducedItem()).thenReturn(animalProduct);

        command = new FeedAnimalsCommand(stockyard, inventory);
    }

    @Test
    public void execute() {
        stockyard.setState(stateNotProducing);
        Assertions.assertEquals(100, inventory.getAmount(crop));
        Assertions.assertEquals(0, stockyard.getRequiredFood());
        Assertions.assertSame(stateNotProducing, stockyard.getState());

        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();

        Assertions.assertEquals(3, stockyard.getAnimals().getSize());
        Assertions.assertEquals(30, stockyard.getRequiredFood());

        command.execute();

        Assertions.assertNotEquals(stateNotProducing, stockyard.getState());
        Assertions.assertEquals(70, inventory.getAmount(crop));
    }
}
