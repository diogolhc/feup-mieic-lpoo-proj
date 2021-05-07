package model.farm.builder;

import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.Farmer;
import model.farm.building.BuildingSet;
import model.farm.building.House;
import model.farm.building.crop_field.CropField;
import model.weather.Sunny;
import model.weather.Weather;

public class DefaultFarmBuilder extends FarmBuilder {
    @Override
    protected void setCrops(Farm farm) {
        farm.addCrop(new CropField(new Position(10, 3)));
    }

    @Override
    protected int getHeight() {
        return 20;
    }

    @Override
    protected int getWidth() {
        return 40;
    }

    @Override
    protected void setWeather(Farm farm) {
        farm.setWeather(new Weather(new Sunny()));
    }

    @Override
    protected void setHouse(Farm farm) {
        farm.setHouse(new House(new Position(1, 1)));
    }

    @Override
    protected void setFarmer(Farm farm) {
        farm.setFarmer(new Farmer(new Position(3, 7)));
    }

    @Override
    protected void setTime(Farm farm) {
        farm.setTime(new InGameTime(1, 8, 0));
    }

    @Override
    protected void setBuildings(Farm farm) {
        farm.setBuildings(new BuildingSet());
    }
}
