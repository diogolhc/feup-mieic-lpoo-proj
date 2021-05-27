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

    public void setProductionTime(InGameTime productionTime) {
        this.productionTime = productionTime;
    }

    public void setBaseProducedAmount(int baseProducedAmount) {
        this.baseProducedAmount = baseProducedAmount;
    }

    public void setSellPrice(Currency sellPrice) {
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
