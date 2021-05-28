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

public class sellItemCommandTest {
    private Item item;
    private Inventory inventory;
    private Wallet wallet;
    private Command command;

    @BeforeEach
    public void setUp() {
        item = Mockito.mock(Item.class);
        wallet = new Wallet(new Currency(100));
        inventory = new Inventory(100);

        Mockito.when(item.getSellPrice()).thenReturn(new Currency(10));
        command = new SellItemCommand(wallet, inventory, item, 5);
    }

    @Test
    public void execute() {
        inventory.storeItem(item, 10);
        Assertions.assertEquals(10, inventory.getAmount(item));

        command.execute();
        Assertions.assertEquals(150, wallet.getCurrency().getCoins());
        Assertions.assertEquals(5, inventory.getAmount(item));
    }

    @Test
    public void executeLessAmount() {
        inventory.storeItem(item, 3);
        command.execute();
        Assertions.assertEquals(130, wallet.getCurrency().getCoins());
        Assertions.assertEquals(0, inventory.getAmount(item));
    }
}
