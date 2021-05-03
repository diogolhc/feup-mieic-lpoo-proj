package model.farm;

import model.Position;
import model.farm.building.BuildingSet;
import model.farm.building.House;
import model.farm.building.crop_field.CropField;

public class Farm {
    private final Farmer farmer;
    private final BuildingSet buildings;
    private int width;
    private int height;

    public Farm(int width, int height) {
        // TODO width and height at least 5
        this.width = width;
        this.height = height;
        this.farmer = new Farmer(new Position(3, 3));

        // Hardcoded positions for testing
        this.buildings = new BuildingSet(new House(new Position(5, 10)));
        this.buildings.addCropField(new CropField(new Position(5, 1)));
    }

    public Farmer getFarmer() {
        return this.farmer;
    }

    public boolean isTraversable(Position position) {
        int x = position.getX();
        int y = position.getY();
        if (x <= 0 || x >= this.width-1) return false;
        if (y <= 0 || y >= this.height-1) return false;
        if (!this.buildings.isTraversable(position)) return false;
        return true;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public BuildingSet getBuildings() {
        return this.buildings;
    }
}