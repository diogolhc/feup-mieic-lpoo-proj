package model.farm.data;

import model.InGameTime;
import model.farm.Currency;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;

import java.io.Serializable;
import java.util.List;

public class Livestock implements Serializable {
    private String animalName;
    private char animalChar;
    private int maxNumAnimals;
    private Crop foodCrop;
    private int requiredFood;
    private AnimalProduct producedItem;
    private Currency buildPrice;
    private Currency animalBuyPrice;
    private Currency animalSellPrice;
    private int stockyardWidth;
    private int stockyardHeight;

    private Livestock() {}

    public static Livestock parseLivestockType(List<String> lines) {
        Livestock livestock = new Livestock();

        String[] tokens = lines.get(0).split(" ");
        livestock.animalName = tokens[0];
        livestock.animalChar = tokens[1].charAt(0);
        livestock.stockyardWidth = Integer.parseInt(tokens[2]);
        livestock.stockyardHeight = Integer.parseInt(tokens[3]);
        livestock.maxNumAnimals = Integer.parseInt(tokens[4]);
        livestock.buildPrice = new Currency(Integer.parseInt(tokens[5]));
        livestock.animalBuyPrice = new Currency(Integer.parseInt(tokens[6]));
        livestock.animalSellPrice = new Currency(Integer.parseInt(tokens[7]));

        tokens = lines.get(1).split(" ");
        livestock.foodCrop = new Crop(tokens[0]); // TODO
        livestock.requiredFood = Integer.parseInt(tokens[1]);

        tokens = lines.get(2).split(" ");
        AnimalProduct product = new AnimalProduct(tokens[0]);
        product.setProductionTime(InGameTime.parseTimerString(tokens[1]));
        product.setBaseProducedAmount(Integer.parseInt(tokens[2]));
        product.setSellPrice(new Currency(Integer.parseInt(tokens[3])));

        livestock.producedItem = product;
        return livestock;
    }

    public String getAnimalName() {
        return this.animalName;
    }

    public char getAnimalChar() {
        return this.animalChar;
    }

    public int getMaxNumAnimals() {
        return this.maxNumAnimals;
    }

    public Crop getFoodCrop() {
        return this.foodCrop;
    }

    public int getRequiredFood() {
        return this.requiredFood;
    }

    public AnimalProduct getProducedItem() {
        return this.producedItem;
    }

    public Currency getBuildPrice() {
        return this.buildPrice;
    }

    public Currency getAnimalBuyPrice() {
        return this.animalBuyPrice;
    }

    public Currency getAnimalSellPrice() {
        return this.animalSellPrice;
    }

    public int getStockyardWidth() {
        return this.stockyardWidth;
    }

    public int getStockyardHeight() {
        return this.stockyardHeight;
    }
}
