package model.farm.crop;

import model.InGameTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Crop {
    public static final Crop NO_CROP = new Crop("", new InGameTime(0), Arrays.asList(new GrowthStage()));

    private String name;
    private InGameTime growTime;
    private List<GrowthStage> growthStages;

    public Crop(String name, InGameTime growTime) {
        this(name, growTime, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Crop{" +
                "name='" + name + '\'' +
                ", growTime=" + growTime +
                ", growthStages=" + growthStages +
                '}';
    }

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
        return this.growTime;
    }

    public void addGrowStage(GrowthStage growthStage) {
        this.growthStages.add(growthStage);
        Collections.sort(this.growthStages);
    }

    public String getName() {
        return this.name;
    }
}
