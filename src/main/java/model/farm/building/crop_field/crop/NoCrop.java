package model.farm.building.crop_field.crop;

import gui.Color;
import model.IngameTime;

import java.util.Arrays;
import java.util.List;

public class NoCrop implements Crop {
    private static final List<GrowthStage> GROWTH_STAGES = Arrays.asList(
            new GrowthStage(new IngameTime(0), ' ', new Color("#000000"))
    );

    @Override
    public IngameTime getGrowTime() {
        return new IngameTime(0);
    }

    @Override
    public List<GrowthStage> getGrowthStages() {
        return GROWTH_STAGES;
    }
}
