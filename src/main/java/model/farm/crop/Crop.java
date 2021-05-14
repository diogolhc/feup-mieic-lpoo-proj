package model.farm.crop;

import model.InGameTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Crop {
    public static final Crop NO_CROP = new Crop("", new InGameTime(0), Arrays.asList(new GrowthStage()));

    private final String name;
    private final InGameTime growTime;
    private final List<GrowthStage> growthStages;

    public Crop(String name, InGameTime growTime, List<GrowthStage> growthStages) {
        this.name = name;
        this.growTime = growTime;
        this.growthStages = growthStages;
        Collections.sort(this.growthStages);
    }

    public GrowthStage getCurrentGrowthStage(InGameTime remainingTime) {
        for (GrowthStage stage: this.growthStages) {
            if (remainingTime.getMinute() <= stage.getStageStartTime().getMinute()) {
                return stage;
            }
        }

        return this.growthStages.get(this.growthStages.size() - 1);
    }

    public InGameTime getGrowTime() {
        return growTime;
    }

    public String getName() {
        return this.name;
    }
}
