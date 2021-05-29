package model.farm.building;

import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.stockyard.Stockyard;
import model.region.RectangleRegion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuildingSet implements Serializable {
    private final Set<CropField> cropFields;
    private final Set<Stockyard> stockyards;
    private final Edifice house;
    private final Edifice market;
    private final Edifice warehouse;

    public BuildingSet(Edifice house, Edifice market, Edifice warehouse) {
        this.house = house;
        this.market = market;
        this.warehouse = warehouse;
        this.cropFields = new HashSet<>();
        this.stockyards = new HashSet<>();
    }

    public BuildingSet() {
        this.house = new Edifice("HOUSE");
        this.market = new Edifice("MARKET");
        this.warehouse = new Edifice("WAREHOUSE");
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

    public Edifice getHouse() {
        return this.house;
    }

    public void setHousePosition(Position position) {
        this.house.setTopLeftPosition(position);
    }

    public Edifice getMarket() {
        return this.market;
    }

    public void setMarketPosition(Position position) {
        this.market.setTopLeftPosition(position);
    }

    public Edifice getWarehouse() {
        return this.warehouse;
    }

    public void setWarehousePosition(Position position) {
        this.warehouse.setTopLeftPosition(position);
    }
}
