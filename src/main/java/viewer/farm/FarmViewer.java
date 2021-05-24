package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.FilledRectangleDrawer;
import model.*;
import model.farm.Currency;
import model.farm.building.BuildingSet;
import model.farm.building.Market;
import model.farm.building.Warehouse;
import model.farm.building.CropField;
import model.farm.Farm;
import model.farm.Farmer;
import model.farm.building.House;
import model.farm.Weather;
import viewer.GameViewer;

public class FarmViewer extends GameViewer {
    public static final Color GRASS_BACKGROUNG = new Color("#7EC850");
    private Farm farm;

    public FarmViewer(Farm farm) {
        this.farm = farm;
    }

    @Override
    public void draw(GUI gui) {
        this.drawBackground(this.farm, gui);
        this.drawBuildings(this.farm.getBuildings(), gui);
        this.drawFences(this.farm, gui);
        this.drawFarmer(this.farm.getFarmer(), new FarmerViewer(), gui);
        this.drawHUD(this.farm.getTime(), this.farm.getWeather(), this.farm.getCurrency(), new HUDViewer(), gui);
    }

    private void drawBackground(Farm farm, GUI gui) {
        FilledRectangleDrawer backgroundDrawer = new FilledRectangleDrawer(
                gui, GRASS_BACKGROUNG, GRASS_BACKGROUNG, ' ');
        backgroundDrawer.draw(new Position(0, 0), farm.getWidth(), farm.getHeight());
    }

    private void drawFarmer(Farmer farmer, FarmerViewer farmerViewer, GUI gui) {
        farmerViewer.draw(farmer, gui);
    }

    private void drawFences(Farm farm, GUI gui) {
        FencesViewer fencesViewer = new FencesViewer();
        fencesViewer.draw(new Position(0, 0), farm.getWidth(), farm.getHeight(), gui);
    }

    private void drawBuildings(BuildingSet buildings, GUI gui) {
        this.drawHouse(buildings.getHouse(), new HouseViewer(), gui);
        this.drawMarket(buildings.getMarket(), new MarketViewer(), gui);
        this.drawWarehouse(buildings.getWarehouse(), new WarehouseViewer(), gui);

        CropFieldViewer cropFieldViewer = new CropFieldViewer();
        for (CropField cropField: buildings.getCropFields()) {
            this.drawCropField(cropField, cropFieldViewer, gui);
        }
    }

    private void drawWarehouse(Warehouse warehouse, WarehouseViewer warehouseViewer, GUI gui) {
        warehouseViewer.draw(warehouse, gui);
    }

    private void drawMarket(Market market, MarketViewer marketViewer, GUI gui) {
        marketViewer.draw(market, gui);
    }

    private void drawHouse(House house, HouseViewer houseViewer, GUI gui) {
        houseViewer.draw(house, gui);
    }

    private void drawCropField(CropField cropField, CropFieldViewer cropFieldViewer, GUI gui) {
        cropFieldViewer.draw(cropField, gui);
    }

    private void drawHUD(InGameTime time, Weather weather, Currency currency, HUDViewer hudViewer, GUI gui) {
        // TODO background drawer inside HUDViewer
        FilledRectangleDrawer backgroundDrawer = new FilledRectangleDrawer(
                gui, new Color("#222222"), new Color("#222222"), ' ');
        backgroundDrawer.draw(new Position(0, farm.getHeight()), farm.getWidth(), 1);
        hudViewer.draw(time, weather, currency, gui);
    }

}
