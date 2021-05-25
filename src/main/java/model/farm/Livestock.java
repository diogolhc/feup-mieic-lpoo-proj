package model.farm;

import model.InGameTime;
import model.farm.item.AnimalProduct;
import model.farm.item.Crop;
import model.farm.item.Item;

public class Livestock {
    private String animalName;
    private char animalChar;
    private Crop foodCrop;
    private int requiredFood;
    private AnimalProduct producedItem;
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
}
