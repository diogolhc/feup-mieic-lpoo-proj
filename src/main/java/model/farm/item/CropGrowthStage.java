package model.farm.item;

import gui.Color;
import model.InGameTime;

public class CropGrowthStage implements Comparable<CropGrowthStage> {
    private final InGameTime stageStartTime;
    private final char stageChar;
    private final Color stageColor;

    public CropGrowthStage(InGameTime stageStartTime, char stageChar, Color stageColor) {
        this.stageStartTime = stageStartTime;
        this.stageChar = stageChar;
        this.stageColor = stageColor;
    }

    public CropGrowthStage() {
        this(new InGameTime(0), ' ', new Color("#000000"));
    }

    public CropGrowthStage(CropGrowthStage stage) {
        this.stageStartTime = stage.stageStartTime;
        this.stageChar = stage.stageChar;
        this.stageColor = stage.stageColor;
    }

    public InGameTime getStageStartTime() {
        return this.stageStartTime;
    }

    public char getStageChar() {
        return this.stageChar;
    }

    public Color getStageColor() {
        return this.stageColor;
    }

    @Override
    public int compareTo(CropGrowthStage growthStage) {
        return this.stageStartTime.compareTo(growthStage.stageStartTime);
    }
}
