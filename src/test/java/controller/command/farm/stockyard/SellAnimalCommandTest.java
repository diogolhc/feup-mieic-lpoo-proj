package controller.command.farm.stockyard;

import controller.command.Command;
import model.farm.Currency;
import model.farm.Wallet;
import model.farm.building.stockyard.StockyardAnimals;
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
        this.wallet = Mockito.mock(Wallet.class);
        this.stockyardAnimals = Mockito.mock(StockyardAnimals.class);
        this.price = Mockito.mock(Currency.class);

        this.command = new SellAnimalCommand(this.wallet, this.stockyardAnimals, this.price);
    }

    @Test
    public void execute() {
        Mockito.when(this.stockyardAnimals.isEmpty()).thenReturn(false);

        this.command.execute();

        Mockito.verify(this.wallet, Mockito.times(1)).receive(this.price);
        Mockito.verify(this.stockyardAnimals, Mockito.times(1)).removeAnimal();
    }


    @Test
    public void executeEmpty() {
        Mockito.when(this.stockyardAnimals.isEmpty()).thenReturn(true);

        this.command.execute();

        Mockito.verifyNoInteractions(this.wallet);
        Mockito.verify(this.stockyardAnimals, Mockito.never()).removeAnimal();
    }
}
