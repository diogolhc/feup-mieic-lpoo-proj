package controller.command.stockyard;

import controller.command.Command;
import controller.command.farm.stockyard.BuyAnimalCommand;
import model.Position;
import model.farm.Currency;
import model.farm.Farm;

import model.farm.Wallet;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BuyAnimalCommandTest {
    private Wallet wallet;
    private Stockyard stockyard;
    private NotProducing stateNotProducing;
    private Command command;
    private AnimalProduct product;
    private Livestock livestock;

    @BeforeEach
    public void setUp() {
        stateNotProducing = Mockito.mock(NotProducing.class);
        wallet = new Wallet(new Currency(100));
        livestock = Mockito.mock(Livestock.class);

        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);

        stockyard = new Stockyard(new Position(0, 0), livestock);
        stockyard.setState(stateNotProducing);

        Mockito.when(stockyard.getLivestockType().getAnimalBuyPrice()).thenReturn(new Currency(10));
        command = new BuyAnimalCommand(wallet, stockyard.getAnimals(), new Currency(10));
    }


    @Test
    public void execute() {
        Assertions.assertEquals(0, stockyard.getAnimals().getSize());
        Assertions.assertEquals(100, wallet.getCurrency().getCoins());

        command.execute();

        Assertions.assertSame(stateNotProducing, stockyard.getState());
        Assertions.assertEquals(90, wallet.getCurrency().getCoins());

        Assertions.assertEquals(1, stockyard.getAnimals().getSize());
    }


    @Test
    public void executeMaxAnimals() {
        Assertions.assertEquals(0, stockyard.getAnimals().getSize());

        command.execute();
        command.execute();
        command.execute();
        command.execute();
        command.execute();
        command.execute();

        Assertions.assertTrue(stockyard.getAnimals().isFull());
        Assertions.assertEquals(5, stockyard.getAnimals().getSize());
        Assertions.assertEquals(50, wallet.getCurrency().getCoins());
    }

}
