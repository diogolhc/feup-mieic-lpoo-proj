package controller.command.farm;

import controller.command.Command;
import model.farm.Inventory;
import model.farm.Wallet;
import model.farm.data.item.Item;

public class SellItemCommand implements Command {
    private final Inventory inventory;
    private final Item item;
    private final int amount;
    private final Wallet wallet;

    public SellItemCommand(Wallet wallet, Inventory inventory, Item item, int amount) {
        this.wallet = wallet;
        this.inventory = inventory;
        this.item = item;
        this.amount = amount;
    }

    @Override
    public void execute() {
        int soldAmount = this.inventory.removeItem(this.item, this.amount);
        this.wallet.receive(this.item.getSellPrice().multiply(soldAmount));
    }
}
