package model.farm.building.crop_field.crop;

import model.InGameTime;

import java.util.List;

// TODO which is better?
//      1) public interface Crop with many classes that just return constant values
//      2) public class Crop with many public static final Crop instances?
public abstract class Crop {
    public abstract InGameTime getGrowTime();
    public abstract List<GrowthStage> getGrowthStages();

    public GrowthStage getGrowthStage(InGameTime remainingTime) {
        for (GrowthStage stage: this.getGrowthStages()) {
            if (remainingTime.getMinute() <= stage.getStageStartTime().getMinute()) {
                return stage;
            }
        }

        return this.getGrowthStages().get(this.getGrowthStages().size() - 1);
    }
}
