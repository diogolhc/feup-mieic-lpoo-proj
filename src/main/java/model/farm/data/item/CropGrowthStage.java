package model.farm.data.item;

import gui.Color;
import model.InGameTime;

import java.io.Serializable;
import java.util.Objects;

public class CropGrowthStage implements Comparable<CropGrowthStage>, Serializable {
    private final InGameTime stageStartTime;
    private final char stageChar;
    private final Color stageColor;

    public CropGrowthStage(InGameTime stageStartTime, char stageChar, Color stageColor) {
        this.stageStartTime = stageStartTime;
        this.stageChar = stageChar;
        this.stageColor = stageColor;
    }

    public CropGrowthStage() {
        this(new InGameTime(0), ' ', Color.BLACK);
    }

    public static CropGrowthStage parseGrowthStage(String s) {
        String tokens[] = s.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("First line must have exactly 3 values; got " + tokens.length);
        }
        if (tokens[1].length() != 1) {
            throw new IllegalArgumentException("Expected char; got" + tokens[1]);
        }
        InGameTime stageTime = InGameTime.parseTimerString(tokens[0]);
        char stageChar = tokens[1].charAt(0);
        Color stageColor = new Color(tokens[2]);
        return new CropGrowthStage(stageTime, stageChar, stageColor);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        CropGrowthStage that = (CropGrowthStage) o;
        return this.stageChar == that.stageChar
                && Objects.equals(this.stageStartTime, that.stageStartTime)
                && Objects.equals(this.stageColor, that.stageColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.stageStartTime, this.stageChar, this.stageColor);
    }

    @Override
    public int compareTo(CropGrowthStage growthStage) {
        return this.stageStartTime.compareTo(growthStage.stageStartTime);
    }
}
