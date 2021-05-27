package model.farm;

import model.InGameTime;
import model.farm.item.AnimalProduct;
import model.farm.item.Crop;
import model.farm.item.Item;

import java.io.Serializable;

public class Livestock implements Serializable {
    private String animalName;
    private char animalChar;
    private Crop foodCrop;
    private int requiredFood;
    private AnimalProduct producedItem;
    private Currency buildPrice;
    private Currency animalBuyPrice;
    private Currency animalSellPrice;
    private int stockyardWidth;
    private int stockyardHeight;

    public String getAnimalName() {
        return animalName;
    }

    public char getAnimalChar() {
        return animalChar;
    }

    public Crop getFoodCrop() {
        return foodCrop;
    }

    public AnimalProduct getProducedItem() {
        return producedItem;
    }

    public int getStockyardWidth() {
        return stockyardWidth;
    }

    public int getStockyardHeight() {
        return stockyardHeight;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalChar(char animalChar) {
        this.animalChar = animalChar;
    }

    public void setFoodCrop(Crop foodCrop) {
        this.foodCrop = foodCrop;
    }

    public void setProducedItem(AnimalProduct produced) {
        this.producedItem = produced;
    }

    public void setStockyardWidth(int stockyardWidth) {
        this.stockyardWidth = stockyardWidth;
    }

    public void setStockyardHeight(int stockyardHeight) {
        this.stockyardHeight = stockyardHeight;
    }

    public int getRequiredFood() {
        return requiredFood;
    }

    public void setRequiredFood(int requiredFood) {
        this.requiredFood = requiredFood;
    }

    public Currency getBuildPrice() {
        return this.buildPrice;
    }

    public void setBuildPrice(Currency buildPrice) {
        this.buildPrice = buildPrice;
    }

    public Currency getAnimalBuyPrice() {
        return animalBuyPrice;
    }

    public void setAnimalBuyPrice(Currency animalBuyPrice) {
        this.animalBuyPrice = animalBuyPrice;
    }

    public Currency getAnimalSellPrice() {
        return animalSellPrice;
    }

    public void setAnimalSellPrice(Currency animalSellPrice) {
        this.animalSellPrice = animalSellPrice;
    }
}
