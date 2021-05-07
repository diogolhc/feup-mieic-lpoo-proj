package model.farm.builder;

import model.farm.Farm;

public abstract class FarmBuilder {

    public Farm buildFarm() {
        Farm farm = new Farm(this.getWidth(), this.getHeight());
        setBuildings(farm);
        setCrops(farm);
        setHouse(farm);
        setFarmer(farm);
        setWeather(farm);
        setTime(farm);
        return farm;
    }

    protected abstract void setTime(Farm farm);

    protected abstract void setWeather(Farm farm);

    protected abstract void setBuildings(Farm farm);

    protected abstract void setFarmer(Farm farm);

    protected abstract void setHouse(Farm farm);

    protected abstract void setCrops(Farm farm);

    protected abstract int getWidth();

    protected abstract int getHeight();
}
