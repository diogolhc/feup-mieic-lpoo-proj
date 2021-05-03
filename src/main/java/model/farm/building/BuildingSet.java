package model.farm.building;

import model.Position;
import model.farm.building.crop_field.CropField;

import java.util.HashSet;
import java.util.Set;

public class BuildingSet {
    private final Set<CropField> cropFields;
    private final House house;

    public BuildingSet(House house) {
        this.cropFields = new HashSet<>();
        this.house = house;
    }

    public BuildingSet addCropField(CropField cropField) {
        this.cropFields.add(cropField);
        return this;
    }

    public Set<CropField> getCropFields() {
        return this.cropFields;
    }

    public House getHouse() {
        return this.house;
    }

    public boolean isTraversable(Position position) {
        if (!this.house.isTraversable(position)) return false;
        for (CropField cropField: this.cropFields) {
            if (!cropField.isTraversable(position)) return false;
        }
        return true;
    }
}
