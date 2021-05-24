package model.farm;

import model.Position;
import model.InGameTime;
import model.farm.item.Crop;
import model.farm.building.BuildingSet;

import java.util.ArrayList;
import java.util.List;


public class Farm {
    private Farmer farmer;
    private final BuildingSet buildings;
    private InGameTime time;
    private List<Weather> weathers;
    private Weather weather;
    private Inventory inventory;
    private List<Crop> crops;
    private int width;
    private int height;

    public Farm(int width, int height) {
        this(width, height, new BuildingSet());
    }

    public Farm(int width, int height, BuildingSet buildings) {
        this.width = width;
        this.height = height;
        this.buildings = buildings;
        this.crops = new ArrayList<>();
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

    public InGameTime getTime() {
        return time;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public void setTime(InGameTime time) {
        this.time = time;
    }

    public void setCrops(List<Crop> crops) {
        this.crops = crops;
    }

    public List<Crop> getCrops() {
        return this.crops;
    }

    public void setWeatherStates(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public List<Weather> getWeatherStates() {
        return this.weathers;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
