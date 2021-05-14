package model.farm.crop;

import gui.Color;
import model.InGameTime;

public class GrowthStage implements Comparable<GrowthStage> {
    private InGameTime stageStartTime;
    private char stageChar;
    private Color stageColor;

    public GrowthStage(InGameTime stageStartTime, char stageChar, Color stageColor) {
        this.stageStartTime = stageStartTime;
        this.stageChar = stageChar;
        this.stageColor = stageColor;
    }

    public GrowthStage() {
        this(new InGameTime(0), ' ', new Color("#000000"));
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
    public int compareTo(GrowthStage growthStage) {
        return this.stageStartTime.compareTo(growthStage.stageStartTime);
    }
}
