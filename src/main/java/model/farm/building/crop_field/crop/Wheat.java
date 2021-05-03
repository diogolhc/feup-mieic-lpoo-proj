package model.farm.building.crop_field.crop;

import model.ChronologicalTime;

public class Wheat implements Crop {
    @Override
    public ChronologicalTime getGrowTime() {
        return new ChronologicalTime(15);
    }

    @Override
    public String toString() {
        return "WHEAT";
    }
}
