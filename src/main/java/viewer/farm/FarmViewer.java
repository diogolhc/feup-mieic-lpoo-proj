package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.entity.FencesDrawer;
import gui.drawer.shape.FilledRectangleDrawer;
import model.*;
import model.farm.building.BuildingSet;
import model.farm.building.crop_field.CropField;
import model.farm.Farm;
import model.farm.Farmer;
import model.farm.building.House;
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
        this.drawHUD(this.farm.getTime(), this.farm.getWeather(), new HUDViewer(), gui);
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
        FencesDrawer fencesDrawer = new FencesDrawer(gui);
        fencesDrawer.draw(new Position(0, 0), farm.getWidth(), farm.getHeight());
    }

    private void drawBuildings(BuildingSet buildings, GUI gui) {
        this.drawHouse(buildings.getHouse(), new HouseViewer(), gui);

        CropFieldViewer cropFieldViewer = new CropFieldViewer();
        for (CropField cropField: buildings.getCropFields()) {
            this.drawCropField(cropField, cropFieldViewer, gui);
        }
    }

    private void drawHouse(House house, HouseViewer houseViewer, GUI gui) {
        houseViewer.draw(house, gui);
    }

    private void drawCropField(CropField cropField, CropFieldViewer cropFieldViewer, GUI gui) {
        cropFieldViewer.draw(cropField, gui);
    }

    private void drawHUD(IngameTime time, Weather weather, HUDViewer hudViewer, GUI gui) {
        // TODO background drawer inside HUDViewer
        FilledRectangleDrawer backgroundDrawer = new FilledRectangleDrawer(
                gui, new Color("#222222"), new Color("#222222"), ' ');
        backgroundDrawer.draw(new Position(0, farm.getHeight()), farm.getWidth(), 1);
        hudViewer.draw(time, weather, gui);
    }

}
