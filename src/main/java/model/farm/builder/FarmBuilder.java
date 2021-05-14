package model.farm.builder;

import model.InGameTime;
import model.farm.Farm;
import model.farm.Farmer;
import model.farm.building.BuildingSet;
import model.farm.building.House;
import model.farm.building.crop_field.CropField;
import model.farm.crop.Crop;
import model.farm.Weather;

import java.util.List;
import java.util.Set;

public abstract class FarmBuilder {

    public Farm buildFarm() {
        BuildingSet buildingSet = new BuildingSet(this.getHouse(), this.getCropFields());
        Farm farm = new Farm(this.getWidth(), this.getHeight(), buildingSet);
        farm.setFarmer(this.getFarmer());
        farm.setTime(this.getTime());
        farm.setWeatherStates(this.getWeatherStates());
        farm.setWeather(farm.getWeatherStates().get(0)); // TODO this will not be like this
        farm.setCrops(this.getCrops());
        return farm;
    }

    protected abstract InGameTime getTime();

    protected abstract List<Weather> getWeatherStates();

    protected abstract Farmer getFarmer();

    protected abstract House getHouse();

    protected abstract Set<CropField> getCropFields();

    protected abstract List<Crop> getCrops();

    protected abstract int getWidth();

    protected abstract int getHeight();
}
