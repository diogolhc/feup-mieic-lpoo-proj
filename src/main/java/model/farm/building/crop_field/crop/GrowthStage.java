package model.farm.building.crop_field.crop;

import gui.Color;
import model.InGameTime;

public class GrowthStage {
    private InGameTime stageStartTime;
    private char stageChar;
    private Color stageColor;

    public GrowthStage(InGameTime stageStartTime, char stageChar, Color stageColor) {
        this.stageStartTime = stageStartTime;
        this.stageChar = stageChar;
        this.stageColor = stageColor;
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
}
