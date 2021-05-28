package controller.command.farm.stockyard;

import controller.command.Command;
import model.Position;
import model.farm.Currency;
import model.farm.Wallet;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.StockyardAnimals;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.data.Livestock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SellAnimalCommandTest {
    private Wallet wallet;
    private Currency price;
    private StockyardAnimals stockyardAnimals;
    private Command command;


    @BeforeEach
    public void setUp() {
        wallet = Mockito.mock(Wallet.class);
        stockyardAnimals = Mockito.mock(StockyardAnimals.class);
        price = Mockito.mock(Currency.class);

        command = new SellAnimalCommand(wallet, stockyardAnimals, price);
    }

    @Test
    public void execute() {
        Mockito.when(stockyardAnimals.isEmpty()).thenReturn(false);

        command.execute();

        Mockito.verify(wallet, Mockito.times(1)).receive(price);
        Mockito.verify(stockyardAnimals, Mockito.times(1)).removeAnimal();
    }


    @Test
    public void executeEmpty() {
        Mockito.when(stockyardAnimals.isEmpty()).thenReturn(true);

        command.execute();

        Mockito.verifyNoInteractions(wallet);
        Mockito.verify(stockyardAnimals, Mockito.never()).removeAnimal();
    }
}
