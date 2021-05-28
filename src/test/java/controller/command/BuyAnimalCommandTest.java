package controller.command;

import controller.command.farm.stockyard.BuyAnimalCommand;
import model.Position;
import model.farm.Currency;
import model.farm.Farm;

import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BuyAnimalCommandTest {
    private Farm farm;
    private Stockyard stockyard;
    private NotProducing stateNotProducing;
    private Command command;
    private AnimalProduct product;
    private Livestock livestock;

    @BeforeEach
    public void setUp() {
        stateNotProducing = Mockito.mock(NotProducing.class);
        farm = new Farm(10, 10);
        farm.setCurrency(new Currency(100));
        livestock = Mockito.mock(Livestock.class);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        Mockito.when(stockyard.getLivestockType().getAnimalBuyPrice()).thenReturn(new Currency(10));
        Mockito.when(stockyard.getLivestockType().getProducedItem()).thenReturn(new AnimalProduct("MILK"));

        command = new BuyAnimalCommand(farm, stockyard.getAnimals(), new Currency(10));
    }


    @Test
    public void execute() {
        stockyard.setState(stateNotProducing);
        Assertions.assertEquals(0, stockyard.getAnimals().getSize());
        command.execute();

        Assertions.assertSame(stateNotProducing, stockyard.getState());
        Assertions.assertEquals(90, farm.getCurrency().getCoins());
        Assertions.assertEquals(1, stockyard.getAnimals().getSize());
    }

}
