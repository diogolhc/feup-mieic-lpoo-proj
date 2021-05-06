package model.farm.building.crop_field.crop;

import gui.Color;
import model.InGameTime;

import java.util.Arrays;
import java.util.List;

public class Wheat extends Crop {
    private static final List<GrowthStage> GROWTH_STAGES = Arrays.asList(
            new GrowthStage(new InGameTime(0), '#', new Color("#aabb01")),
            new GrowthStage(new InGameTime(8), '\\', new Color("#C5D534")),
            new GrowthStage(new InGameTime(15), ';', new Color("#9E9516")),
            new GrowthStage(new InGameTime(20), ':', new Color("#696606"))
    );

    @Override
    public InGameTime getGrowTime() {
        return new InGameTime(20);
    }

    @Override
    public List<GrowthStage> getGrowthStages() {
        return GROWTH_STAGES;
    }

    @Override
    public String toString() {
        return "WHEAT";
    }
}
