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

    public static Livestock parseLivestockType(List<Crop> cropTypes, List<String> lines) {
        Livestock livestock = new Livestock();
        if (lines.size() != 3) {
            throw new IllegalArgumentException("Parsing Livestock requires exactly 3 lines; got " + lines.size());
        }

        String[] tokens = lines.get(0).split(" ");
        if (tokens.length != 8) {
            throw new IllegalArgumentException("First line must have exactly 8 values; got " + tokens.length);
        }
        if (tokens[1].length() != 1) {
            throw new IllegalArgumentException("Expected char; got" + tokens[1]);
        }
        livestock.animalName = tokens[0];
        livestock.animalChar = tokens[1].charAt(0);
        livestock.stockyardWidth = Integer.parseInt(tokens[2]);
        livestock.stockyardHeight = Integer.parseInt(tokens[3]);
        if (livestock.stockyardWidth < 4 || livestock.stockyardHeight < 5) {
            throw new RuntimeException("Stockyard size must be at least 4x5");
        }

        livestock.maxNumAnimals = Integer.parseInt(tokens[4]);
        livestock.buildPrice = new Currency(Integer.parseInt(tokens[5]));
        livestock.animalBuyPrice = new Currency(Integer.parseInt(tokens[6]));
        livestock.animalSellPrice = new Currency(Integer.parseInt(tokens[7]));

        tokens = lines.get(1).split(" ");
        if (tokens.length != 2) {
            throw new IllegalArgumentException("First line must have exactly 2 values; got " + tokens.length);
        }
        int foodIndex = cropTypes.indexOf(new Crop(tokens[0]));
        if (foodIndex == -1) {
            throw new IllegalArgumentException("Crop not recognized: " + tokens[0]);
        }
        livestock.foodCrop = cropTypes.get(foodIndex);
        livestock.requiredFood = Integer.parseInt(tokens[1]);

        livestock.producedItem = AnimalProduct.parseAnimalProduct(lines.get(2));
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
