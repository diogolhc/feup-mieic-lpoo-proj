package model.farm.building.crop_field.crop;

import gui.Color;
import model.IngameTime;

import java.util.Arrays;
import java.util.List;

public class Wheat implements Crop {
    private static final List<GrowthStage> GROWTH_STAGES = Arrays.asList(
            new GrowthStage(new IngameTime(0), '#', new Color("#aabb01")),
            new GrowthStage(new IngameTime(8), '\\', new Color("#C5D534")),
            new GrowthStage(new IngameTime(12), ';', new Color("#9E9516")),
            new GrowthStage(new IngameTime(15), ':', new Color("#696606"))
    );

    @Override
    public IngameTime getGrowTime() {
        return new IngameTime(15);
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
