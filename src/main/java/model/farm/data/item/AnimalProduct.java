package model.farm.data.item;

import model.InGameTime;
import model.farm.Currency;

public class AnimalProduct extends Item {
    private final String name;
    private InGameTime productionTime;
    private int baseProducedAmount;
    private Currency sellPrice;

    public AnimalProduct(String name) {
        this.name = name;
        this.productionTime = new InGameTime(0);
        this.baseProducedAmount = 0;
        this.sellPrice = new Currency();
    }

    public static AnimalProduct parseAnimalProduct(String s) {
        String[] tokens = s.split(" ");
        if (tokens.length != 4) {
            throw new IllegalArgumentException("First line must have exactly 4 values; got " + tokens.length);
        }
        AnimalProduct product = new AnimalProduct(tokens[0]);
        product.productionTime = InGameTime.parseTimerString(tokens[1]);
        product.baseProducedAmount = Integer.parseInt(tokens[2]);
        product.sellPrice = new Currency(Integer.parseInt(tokens[3]));
        return product;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Currency getSellPrice() {
        return this.sellPrice;
    }

    public InGameTime getProductionTime() {
        return this.productionTime;
    }

    public int getBaseProducedAmount() {
        return this.baseProducedAmount;
    }
}
