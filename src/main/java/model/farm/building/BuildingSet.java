package model.farm.building;

import model.Position;
import model.region.RectangleRegion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuildingSet implements Serializable {
    private final Set<CropField> cropFields; // TODO because of hashset, we probably need to implement hash for cropfields
    private final Set<Stockyard> stockyards; //      same goes for stockyards
    private House house;
    private Market market;
    private Warehouse warehouse;

    public BuildingSet() {
        this.cropFields = new HashSet<>();
        this.stockyards = new HashSet<>();
    }

    // TODO This smells bad :(
    public BuildingSet(House house, Market market, Warehouse warehouse, Set<CropField> cropFields, Set<Stockyard> stockyards) {
        this.market = market;
        this.warehouse = warehouse;
        this.cropFields = cropFields;
        this.house = house;
        this.stockyards = stockyards;
    }

    public BuildingSet addCropField(CropField cropField) {
        this.cropFields.add(cropField);
        return this;
    }

    public BuildingSet addStockyard(Stockyard stockyard) {
        this.stockyards.add(stockyard);
        return this;
    }

    public Set<CropField> getCropFields() {
        return this.cropFields;
    }

    public Set<Stockyard> getStockyards() { return this.stockyards; }

    public House getHouse() {
        return this.house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Market getMarket() {
        return this.market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<Building> getDemolishableBuildings() {
        List<Building> buildings = new ArrayList<>();
        buildings.addAll(this.cropFields);
        buildings.addAll(this.stockyards);
        return buildings;
    }

    public List<Building> getAllBuildings() {
        List<Building> buildings = new ArrayList<>();
        buildings.add(this.house);
        buildings.add(this.market);
        buildings.add(this.warehouse);
        buildings.addAll(this.cropFields);
        buildings.addAll(this.stockyards);
        return buildings;
    }

    public boolean isTraversable(Position position) {
        for (Building building: this.getAllBuildings()) {
            if (building.getUntraversableRegion().contains(position)) return false;
        }

        return true;
    }

    public boolean isOccupied(RectangleRegion region) {
        for (Building building: this.getAllBuildings()) {
            if (building.getOccupiedRegion().intersects(region)) return true;
        }

        return false;
    }

    public void removeCropField(CropField cropField) {
        this.cropFields.remove(cropField);
    }

    public void removeStockyard(Stockyard stockyard) {
        this.stockyards.remove(stockyard);
    }
}
