package model.farm.builder;

import model.InGameTime;
import model.farm.Farm;
import model.farm.Farmer;
import model.farm.building.BuildingSet;
import model.farm.building.House;
import model.farm.building.crop_field.CropField;
import model.weather.Weather;

import java.util.Set;

public abstract class FarmBuilder {

    public Farm buildFarm() {
        BuildingSet buildingSet = new BuildingSet(this.getHouse(), this.getCropFields());
        Farm farm = new Farm(this.getWidth(), this.getHeight(), buildingSet);
        farm.setFarmer(this.getFarmer());
        farm.setTime(this.getTime());
        farm.setWeather(this.getWeather());
        return farm;
    }

    protected abstract InGameTime getTime();

    protected abstract Weather getWeather();

    protected abstract Farmer getFarmer();

    protected abstract House getHouse();

    protected abstract Set<CropField> getCropFields();

    protected abstract int getWidth();

    protected abstract int getHeight();
}
