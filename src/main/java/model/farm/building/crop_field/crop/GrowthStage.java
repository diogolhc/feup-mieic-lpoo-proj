package model.farm.building.crop_field.crop;

import gui.Color;
import model.IngameTime;

public class GrowthStage {
    private IngameTime stageStartTime;
    private char stageChar;
    private Color stageColor;

    public GrowthStage(IngameTime stageStartTime, char stageChar, Color stageColor) {
        this.stageStartTime = stageStartTime;
        this.stageChar = stageChar;
        this.stageColor = stageColor;
    }

    public IngameTime getStageStartTime() {
        return this.stageStartTime;
    }

    public char getStageChar() {
        return this.stageChar;
    }

    public Color getStageColor() {
        return this.stageColor;
    }
}
