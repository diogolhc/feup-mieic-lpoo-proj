package model.farm.building;

import model.Position;
import model.farm.building.edifice.House;
import model.farm.building.edifice.Market;
import model.farm.building.edifice.Warehouse;
import model.region.RectangleRegion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuildingSet implements Serializable {
    private final Set<CropField> cropFields;
    private final Set<Stockyard> stockyards;
    private House house;
    private Market market;
    private Warehouse warehouse;

    public BuildingSet() {
        this.cropFields = new HashSet<>();
        this.stockyards = new HashSet<>();
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

    public void addCropField(CropField cropField) {
        this.cropFields.add(cropField);
    }

    public void removeCropField(CropField cropField) {
        this.cropFields.remove(cropField);
    }

    public Set<CropField> getCropFields() {
        return this.cropFields;
    }

    public void addStockyard(Stockyard stockyard) {
        this.stockyards.add(stockyard);
    }

    public void removeStockyard(Stockyard stockyard) {
        this.stockyards.remove(stockyard);
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
}
