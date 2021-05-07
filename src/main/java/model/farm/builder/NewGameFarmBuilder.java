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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO after implementing loading save from file, this class
//      may be substituted with loading from a file in resources
public class NewGameFarmBuilder extends FarmBuilder {
    @Override
    protected int getHeight() {
        return 20;
    }

    @Override
    protected int getWidth() {
        return 40;
    }

    @Override
    protected Weather getWeather() {
        return new Weather(new Sunny());
    }

    @Override
    protected Farmer getFarmer() {
        return new Farmer(new Position(3, 7));
    }

    @Override
    protected InGameTime getTime() {
        return new InGameTime(1, 8, 0);
    }

    @Override
    protected House getHouse() {
        return new House(new Position(1, 1));
    }

    @Override
    protected Set<CropField> getCropFields() {
        Set<CropField> cropFields = new HashSet<>();
        cropFields.add(new CropField(new Position(10, 3)));
        return cropFields;
    }
}
