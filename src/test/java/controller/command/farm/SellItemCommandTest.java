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
    private Currency price;

    @BeforeEach
    public void setUp() {
        item = Mockito.mock(Item.class);
        wallet = Mockito.mock(Wallet.class);
        inventory = new Inventory(100);
        price = Mockito.mock(Currency.class);

        Mockito.when(item.getSellPrice()).thenReturn(new Currency(10));
        command = new SellItemCommand(wallet, inventory, item, 5);
    }

    @Test
    public void execute() {
        inventory.storeItem(item, 10);
        Assertions.assertEquals(10, inventory.getAmount(item));

        Mockito.verify(wallet, Mockito.never()).receive(Mockito.any());
        command.execute();

        Mockito.verify(wallet, Mockito.times(1)).receive(new Currency(50));

        Assertions.assertEquals(5, inventory.getAmount(item));
    }

    @Test
    public void executeLessAmount() {
        inventory.storeItem(item, 3);

        Mockito.verify(wallet, Mockito.never()).receive(Mockito.any());
        command.execute();
        Mockito.verify(wallet, Mockito.times(1)).receive(new Currency(30));

        Assertions.assertEquals(0, inventory.getAmount(item));
    }
}
