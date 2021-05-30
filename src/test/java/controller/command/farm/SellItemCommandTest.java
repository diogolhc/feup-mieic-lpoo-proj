package controller.command.farm;

import controller.command.Command;
import controller.command.farm.SellItemCommand;
import model.farm.Currency;
import model.farm.Inventory;
import model.farm.Wallet;
import model.farm.data.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SellItemCommandTest {
    private Item item;
    private Inventory inventory;
    private Wallet wallet;
    private Command command;

    @BeforeEach
    public void setUp() {
        this.item = Mockito.mock(Item.class);
        this.wallet = Mockito.mock(Wallet.class);
        this.inventory = new Inventory(100);

        Mockito.when(this.item.getSellPrice()).thenReturn(new Currency(10));
        this.command = new SellItemCommand(this.wallet, this.inventory, this.item, 5);
    }

    @Test
    public void execute() {
        this.inventory.storeItem(this.item, 10);
        Assertions.assertEquals(10, this.inventory.getAmount(this.item));

        Mockito.verify(this.wallet, Mockito.never()).receive(Mockito.any());
        this.command.execute();

        Mockito.verify(this.wallet, Mockito.times(1)).receive(new Currency(50));

        Assertions.assertEquals(5, this.inventory.getAmount(this.item));
    }

    @Test
    public void executeLessAmount() {
        this.inventory.storeItem(this.item, 3);

        Mockito.verify(this.wallet, Mockito.never()).receive(Mockito.any());
        this.command.execute();
        Mockito.verify(this.wallet, Mockito.times(1)).receive(new Currency(30));

        Assertions.assertEquals(0, this.inventory.getAmount(this.item));
    }
}
