package model.farm.builder;

import model.InGameTime;
import model.farm.*;
import model.farm.Farm;
import model.farm.building.BuildingSet;
import model.farm.data.Livestock;
import model.farm.entity.Entity;
import model.farm.Inventory;
import model.farm.data.item.Crop;
import model.farm.data.Weather;

import java.util.List;

public abstract class FarmBuilder {
    public Farm buildFarm() {
        Farm farm = new Farm(this.getWidth(), this.getHeight(), this.getBuildings());
        farm.setFarmer(this.getFarmer());
        farm.setTime(this.getTime());
        farm.setCurrency(this.getCurrency());
        farm.setInventory(this.getInventory());

        List<Crop> cropTypes = this.getCropTypes();
        farm.addCropTypes(cropTypes);
        farm.addLivestockTypes(this.getLivestockTypes(cropTypes));
        farm.addWeatherTypes(this.getWeatherStates());
        farm.setCurrentWeather(farm.getWeatherTypes().get(0));

        return farm;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract BuildingSet getBuildings();

    protected abstract Entity getFarmer();

    protected abstract InGameTime getTime();

    protected abstract Currency getCurrency();

    protected abstract Inventory getInventory();

    protected abstract List<Crop> getCropTypes();

    protected abstract List<Livestock> getLivestockTypes(List<Crop> cropTypes);

    protected abstract List<Weather> getWeatherStates();
}
