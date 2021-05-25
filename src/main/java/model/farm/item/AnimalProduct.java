package model.farm.item;

import model.InGameTime;
import model.farm.Currency;

public class AnimalProduct extends Item {
    private final String name;
    private final InGameTime productionTime;
    private final int baseProducedAmount;
    private final Currency sellPrice;

    public AnimalProduct(String name, InGameTime productionTime, int baseProducedAmount, Currency sellPrice) {
        this.name = name;
        this.productionTime = productionTime;
        this.baseProducedAmount = baseProducedAmount;
        this.sellPrice = sellPrice;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Currency getSellPrice() {
        return sellPrice;
    }

    public InGameTime getProductionTime() {
        return productionTime;
    }

    public int getBaseProducedAmount() {
        return baseProducedAmount;
    }
}
