package model.farm.building.crop_field.crop;

import gui.Color;
import model.ChronologicalTime;

public class GrowthStage {
    private ChronologicalTime stageStartTime;
    private char stageChar;
    private Color stageColor;

    public GrowthStage(ChronologicalTime stageStartTime, char stageChar, Color stageColor) {
        this.stageStartTime = stageStartTime;
        this.stageChar = stageChar;
        this.stageColor = stageColor;
    }

    public ChronologicalTime getStageStartTime() {
        return this.stageStartTime;
    }

    public char getStageChar() {
        return this.stageChar;
    }

    public Color getStageColor() {
        return this.stageColor;
    }
}
