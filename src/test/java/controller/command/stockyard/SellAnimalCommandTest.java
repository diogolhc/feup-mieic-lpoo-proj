package controller.command.stockyard;

import controller.command.Command;
import controller.command.farm.stockyard.BuyAnimalCommand;
import controller.command.farm.stockyard.SellAnimalCommand;
import model.Position;
import model.farm.Currency;
import model.farm.Wallet;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SellAnimalCommandTest {
    private Wallet wallet;
    private Stockyard stockyard;
    private NotProducing stateNotProducing;
    private Command command;
    private Livestock livestock;

    @BeforeEach
    public void setUp() {
        stateNotProducing = Mockito.mock(NotProducing.class);
        wallet = new Wallet(new Currency(100));
        livestock = Mockito.mock(Livestock.class);

        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);

        stockyard = new Stockyard(new Position(0, 0), livestock);
        stockyard.setState(stateNotProducing);
        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();

        Mockito.when(stockyard.getLivestockType().getAnimalSellPrice()).thenReturn(new Currency(10));
        command = new SellAnimalCommand(wallet, stockyard.getAnimals(), new Currency(10));
    }

    @Test
    public void execute() {
        Assertions.assertEquals(5, stockyard.getAnimals().getSize());
        Assertions.assertEquals(100, wallet.getCurrency().getCoins());

        command.execute();

        Assertions.assertSame(stateNotProducing, stockyard.getState());
        Assertions.assertEquals(110, wallet.getCurrency().getCoins());

        Assertions.assertEquals(4, stockyard.getAnimals().getSize());
    }


    @Test
    public void executeMaxAnimals() {
        Assertions.assertEquals(5, stockyard.getAnimals().getSize());

        command.execute();
        command.execute();
        command.execute();
        command.execute();
        command.execute();
        command.execute();

        Assertions.assertTrue(stockyard.getAnimals().isEmpty());
        Assertions.assertEquals(0, stockyard.getAnimals().getSize());
        Assertions.assertEquals(150, wallet.getCurrency().getCoins());
    }
}
