package model.farm;

import model.Position;
import model.InGameTime;
import model.farm.data.Livestock;
import model.farm.data.Weather;
import model.farm.entity.Entity;
import model.farm.data.item.Crop;
import model.farm.building.BuildingSet;
import model.farm.data.item.Item;
import model.region.RectangleRegion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Farm implements Serializable {
    private Entity farmer;
    private final BuildingSet buildings;
    private InGameTime time;
    private Inventory inventory;
    private Wallet wallet;
    private Weather currentWeather;

    private final List<Weather> weatherTypes;
    private final List<Crop> cropTypes;
    private final List<Livestock> livestockTypes;

    private final int width;
    private final int height;

    public Farm(int width, int height) {
        this(width, height, new BuildingSet());
    }

    public Farm(int width, int height, BuildingSet buildings) {
        this.width = width;
        this.height = height;
        this.buildings = buildings;
        this.cropTypes = new ArrayList<>();
        this.livestockTypes = new ArrayList<>();
        this.weatherTypes = new ArrayList<>();
        this.inventory = new Inventory();
    }

    public RectangleRegion getInsideRegion() {
        return new RectangleRegion(new Position(1, 1), this.width - 2, this.height - 2);
    }

    public boolean isTraversable(Position position) {
        return getInsideRegion().contains(position) && this.buildings.isTraversable(position);
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

    public void setCurrentWeather(Weather weather) {
        this.currentWeather = weather;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public void setFarmer(Entity farmer) {
        this.farmer = farmer;
    }

    public Entity getFarmer() {
        return this.farmer;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setTime(InGameTime time) {
        this.time = time;
    }

    public InGameTime getTime() {
        return time;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    public void addCropTypes(List<Crop> crops) {
        this.cropTypes.addAll(crops);
    }

    public List<Crop> getCropTypes() {
        return this.cropTypes;
    }

    public void addLivestockTypes(List<Livestock> livestockTypes) {
        this.livestockTypes.addAll(livestockTypes);
    }

    public List<Livestock> getLivestockTypes() {
        return livestockTypes;
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        items.addAll(this.cropTypes);
        for (Livestock livestock: livestockTypes) {
            items.add(livestock.getProducedItem());
        }
        return items;
    }

    public void addWeatherTypes(List<Weather> weathers) {
        this.weatherTypes.addAll(weathers);
    }

    public List<Weather> getWeatherTypes() {
        return this.weatherTypes;
    }
}
