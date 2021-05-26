package model.farm.item;

import model.InGameTime;
import model.farm.Currency;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Crop extends Item {
    public static final Crop NO_CROP = new Crop("");

    private final String name;
    private final InGameTime growTime;
    private final List<CropGrowthStage> growthStages;
    private final int baseHarvestAmount;

    private final Currency plantPrice;
    private final Currency sellPrice;

    public Crop(String name) {
        this(name, new InGameTime(0), Arrays.asList(new CropGrowthStage()),
                0, new Currency(), new Currency());
    }

    public Crop(String name, InGameTime growTime, List<CropGrowthStage> growthStages, int baseHarvestAmount,
                Currency plantPrice, Currency sellPrice) {
        this.name = name;
        this.growTime = growTime;
        this.growthStages = growthStages;
        this.baseHarvestAmount = baseHarvestAmount;
        this.plantPrice = plantPrice;
        this.sellPrice = sellPrice;
        Collections.sort(this.growthStages);
    }

    public CropGrowthStage getCurrentGrowthStage(InGameTime remainingTime) {
        for (CropGrowthStage stage: this.growthStages) {
            if (remainingTime.getMinute() <= stage.getStageStartTime().getMinute()) {
                return stage;
            }
        }

        return this.growthStages.get(this.growthStages.size() - 1);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Currency getSellPrice() {
        return sellPrice;
    }

    public InGameTime getGrowTime() {
        return growTime;
    }

    public Currency getPlantPrice() {
        return plantPrice;
    }

    public int getBaseHarvestAmount() {
        return this.baseHarvestAmount;
    }
}
