package model.farm.data.item;

import model.InGameTime;
import model.farm.Currency;

import java.util.*;

public class Crop extends Item {
    public static final Crop NO_CROP = new Crop("", new CropGrowthStage());

    private final String name;
    private final SortedSet<CropGrowthStage> growthStages;
    private int baseHarvestAmount;
    private Currency plantPrice;
    private Currency sellPrice;

    public Crop(String name) {
        this.name = name;
        this.baseHarvestAmount = 0;
        this.growthStages = new TreeSet<>();
        this.plantPrice = new Currency();
        this.sellPrice = new Currency();
    }

    public Crop(String name, CropGrowthStage growthStage) {
        this(name);
        this.growthStages.add(growthStage);
    }

    public CropGrowthStage getCurrentGrowthStage(InGameTime remainingTime) {
        for (CropGrowthStage stage: this.growthStages) {
            if (remainingTime.getMinute() <= stage.getStageStartTime().getMinute()) {
                return stage;
            }
        }

        return this.growthStages.last();
    }

    public void addGrowthStages(Collection<CropGrowthStage> growthStages) {
        this.growthStages.addAll(growthStages);
    }

    public void setPlantPrice(Currency plantPrice) {
        this.plantPrice = plantPrice;
    }

    public void setSellPrice(Currency sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setBaseHarvestAmount(int baseHarvestAmount) {
        this.baseHarvestAmount = baseHarvestAmount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Currency getSellPrice() {
        return this.sellPrice;
    }

    public Currency getPlantPrice() {
        return this.plantPrice;
    }

    public InGameTime getGrowTime() {
        return this.growthStages.last().getStageStartTime();
    }

    public int getBaseHarvestAmount() {
        return this.baseHarvestAmount;
    }
}
