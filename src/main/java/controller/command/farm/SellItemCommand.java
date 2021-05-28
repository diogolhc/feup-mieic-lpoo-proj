package controller.command.farm;

import controller.command.Command;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.data.item.Item;

public class SellItemCommand implements Command {
    private final Farm farm;
    private final Item item;
    private final int amount;

    public SellItemCommand(Farm farm, Item item, int amount) {
        this.farm = farm;
        this.item = item;
        this.amount = amount;
    }

    @Override
    public void execute() {
        int soldAmount = this.farm.getInventory().removeItem(this.item, this.amount);
        Currency soldValue = this.item.getSellPrice().multiply(soldAmount);
        this.farm.setCurrency(this.farm.getCurrency().add(soldValue));
    }
}
