package model.farm.building;

import model.Position;
import model.farm.building.crop_field.CropField;

import java.util.HashSet;
import java.util.Set;

public class BuildingSet {
    private final Set<CropField> cropFields;
    private House house;

    public BuildingSet() {
        this.cropFields = new HashSet<>();
    }

    public BuildingSet(House house, Set<CropField> cropFields) {
        this.cropFields = cropFields;
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

    public void setHouse(House house) {
        this.house = house;
    }

    public boolean isTraversable(Position position) {
        if (!this.house.isTraversable(position)) return false;
        for (CropField cropField: this.cropFields) {
            if (!cropField.isTraversable(position)) return false;
        }
        return true;
    }
}
