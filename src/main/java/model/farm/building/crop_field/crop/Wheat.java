package model.farm.building.crop_field.crop;

import gui.Color;
import model.ChronologicalTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wheat implements Crop {
    private static final List<GrowthStage> GROWTH_STAGES = Arrays.asList(
            new GrowthStage(new ChronologicalTime(0), '#', new Color("#aabb01")),
            new GrowthStage(new ChronologicalTime(8), '\\', new Color("#C5D534")),
            new GrowthStage(new ChronologicalTime(12), ';', new Color("#9E9516")),
            new GrowthStage(new ChronologicalTime(15), ':', new Color("#696606"))
    );

    @Override
    public ChronologicalTime getGrowTime() {
        return new ChronologicalTime(15);
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
