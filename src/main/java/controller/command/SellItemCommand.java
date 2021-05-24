package controller.command;

import model.farm.Currency;
import model.farm.Farm;
import model.farm.item.Item;

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
        int soldAmount = farm.getInventory().removeItem(item, amount);
        Currency soldValue = item.getSellPrice().multiply(soldAmount);
        farm.setCurrency(farm.getCurrency().add(soldValue));
    }
}
