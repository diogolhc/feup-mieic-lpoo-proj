package model.farm;

import model.Position;
import model.InGameTime;
import model.farm.building.Stockyard;
import model.farm.item.Crop;
import model.farm.building.BuildingSet;
import model.farm.item.Item;
import model.region.RectangleRegion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Farm implements Serializable {
    private Farmer farmer;
    private final BuildingSet buildings;
    private InGameTime time;
    private Inventory inventory;
    private Currency currency;
    private Weather currentWeather;

    private List<Weather> weatherTypes;
    private List<Crop> cropTypes;
    private List<Livestock> livestockTypes;

    private int width;
    private int height;

    public Farm(int width, int height) {
        this(width, height, new BuildingSet());
    }

    public Farm(int width, int height, BuildingSet buildings) {
        this.width = width;
        this.height = height;
        this.buildings = buildings;
        this.cropTypes = new ArrayList<>();
        this.livestockTypes = new ArrayList<>();
        this.inventory = new Inventory();
    }

    public Farmer getFarmer() {
        return this.farmer;
    }

    public RectangleRegion getInsideRegion() {
        return new RectangleRegion(new Position(1, 1), this.width - 2, this.height - 2);
    }

    public boolean isTraversable(Position position) {
        if (!this.getInsideRegion().contains(position)) return false;
        if (!this.buildings.isTraversable(position)) return false;
        return true;
    }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.addAll(this.cropTypes);
        for (Livestock livestock: livestockTypes) {
            items.add(livestock.getProducedItem());
        }
        return items;
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

    public InGameTime getTime() {
        return time;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(Weather weather) {
        this.currentWeather = weather;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public void setTime(InGameTime time) {
        this.time = time;
    }

    public void setCropTypes(List<Crop> crops) {
        this.cropTypes = crops;
    }

    public List<Crop> getCropTypes() {
        return this.cropTypes;
    }

    public List<Livestock> getLivestockTypes() {
        return livestockTypes;
    }

    public void setLivestockTypes(List<Livestock> livestockTypes) {
        this.livestockTypes = livestockTypes;
    }

    public void setWeatherStates(List<Weather> weathers) {
        this.weatherTypes = weathers;
    }

    public List<Weather> getWeatherStates() {
        return this.weatherTypes;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setStockyards(List<Stockyard> stockyards){
        for (Stockyard stockyard : stockyards) {
            this.buildings.addStockyard(stockyard);
        }
    }
}
