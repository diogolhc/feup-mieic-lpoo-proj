package model.farm.builder;

import model.InGameTime;
import model.farm.*;
import model.farm.Farm;
import model.farm.data.Livestock;
import model.farm.entity.Entity;
import model.farm.Inventory;
import model.farm.building.BuildingSet;
import model.farm.building.edifice.House;
import model.farm.building.edifice.Market;
import model.farm.building.edifice.Warehouse;
import model.farm.building.CropField;
import model.farm.data.item.Crop;
import model.farm.data.Weather;

import java.util.List;
import java.util.Set;

public abstract class FarmBuilder {

    public Farm buildFarm() {
        BuildingSet buildingSet = new BuildingSet();
        buildingSet.setHouse(this.getHouse());
        buildingSet.setMarket(this.getMarket());
        buildingSet.setWarehouse(this.getWarehouse());

        for (CropField cropField: this.getCropFields()) {
            buildingSet.addCropField(cropField);
        }

        Farm farm = new Farm(this.getWidth(), this.getHeight(), buildingSet);
        farm.setFarmer(this.getFarmer());
        farm.setTime(this.getTime());
        farm.addWeatherTypes(this.getWeatherStates());
        farm.setCurrentWeather(farm.getWeatherTypes().get(0)); // TODO this will not be like this
        farm.addCropTypes(this.getCrops());
        farm.setInventory(this.getInventory());
        farm.setCurrency(this.getCurrency());
        farm.addLivestockTypes(this.getLivestockTypes());

        for (Livestock livestock: farm.getLivestockTypes()) {
            livestock.setFoodCrop(farm.getCropTypes().get(farm.getCropTypes().indexOf(livestock.getFoodCrop())));
        }

        return farm;
    }

    protected abstract List<Livestock> getLivestockTypes();

    protected abstract Currency getCurrency();

    protected abstract Inventory getInventory();

    protected abstract Warehouse getWarehouse();

    protected abstract Market getMarket();

    protected abstract InGameTime getTime();

    protected abstract List<Weather> getWeatherStates();

    protected abstract Entity getFarmer();

    protected abstract House getHouse();

    protected abstract Set<CropField> getCropFields();

    protected abstract List<Crop> getCrops();

    protected abstract int getWidth();

    protected abstract int getHeight();
}
