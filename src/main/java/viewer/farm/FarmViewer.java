package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.BoxDrawer;
import gui.drawer.shape.FilledRectangleDrawer;
import model.InGameTime;
import model.Position;
import model.farm.Currency;
import model.farm.building.*;
import model.farm.Farm;
import model.farm.building.Edifice;
import model.farm.building.crop_field.CropField;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.Weather;
import viewer.GameViewer;
import viewer.farm.element.*;
import viewer.farm.element.building.*;

public abstract class FarmViewer extends GameViewer {
    protected final Farm farm;

    public FarmViewer(Farm farm) {
        this.farm = farm;
    }

    @Override
    public void draw(GUI gui) {
        this.drawBackground(this.farm, gui);
        this.drawBuildings(this.farm.getBuildings(), gui);
        this.drawFences(this.farm, gui);

        HUDViewer hudViewer = new HUDViewer(this.farm.getHeight(), this.farm.getWidth());
        this.drawHUD(this.farm.getTime(), this.farm.getCurrentWeather(), this.farm.getCurrency(), hudViewer, gui);
    }

    private void drawBackground(Farm farm, GUI gui) {
        FilledRectangleDrawer backgroundDrawer = new FilledRectangleDrawer(gui, Color.GRASS);
        backgroundDrawer.draw(new Position(0, 0), farm.getWidth(), farm.getHeight());
    }

    private void drawFences(Farm farm, GUI gui) {
        BoxDrawer fencesDrawer = new BoxDrawer(gui, Color.GRASS, Color.WOOD);
        fencesDrawer.draw(new Position(0, 0), farm.getWidth(), farm.getHeight());
    }

    private void drawHUD(InGameTime time, Weather weather, Currency currency, HUDViewer hudViewer, GUI gui) {
        hudViewer.draw(time, weather, currency, gui);
    }

    private void drawBuildings(BuildingSet buildings, GUI gui) {
        this.drawHouse(buildings.getHouse(), gui);
        this.drawMarket(buildings.getMarket(), gui);
        this.drawWarehouse(buildings.getWarehouse(), gui);

        CropFieldViewer cropFieldViewer = new CropFieldViewer();
        for (CropField cropField: buildings.getCropFields()) {
            this.drawCropField(cropField, cropFieldViewer, gui);
        }

        StockyardViewer stockyardViewer = new StockyardViewer();
        for (Stockyard stockyard : buildings.getStockyards()) {
            this.drawStockyard(stockyard, stockyardViewer, gui);
        }
    }

    private void drawHouse(Edifice house, GUI gui) {
        EdificeViewer houseViewer = new EdificeViewer(new Color("#c20000"));
        houseViewer.draw(house.getTopLeftPosition(), gui);
    }

    private void drawMarket(Edifice market, GUI gui) {
        EdificeViewer marketViewer = new EdificeViewer(new Color("#00c200"));
        marketViewer.draw(market.getTopLeftPosition(), gui);
    }

    private void drawWarehouse(Edifice warehouse, GUI gui) {
        EdificeViewer warehouseViewer = new EdificeViewer(new Color("#0099c2"));
        warehouseViewer.draw(warehouse.getTopLeftPosition(), gui);
    }

    private void drawCropField(CropField cropField, CropFieldViewer cropFieldViewer, GUI gui) {
        cropFieldViewer.draw(cropField, gui);
    }

    private void drawStockyard(Stockyard stockyard, StockyardViewer stockyardViewer, GUI gui) {
        stockyardViewer.draw(stockyard, gui);
    }
}
