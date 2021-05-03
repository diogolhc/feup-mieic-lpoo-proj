package viewer.farm;

import gui.GUI;
import gui.drawer.entity.FencesDrawer;
import model.*;
import model.farm.building.BuildingSet;
import model.farm.building.crop_field.CropField;
import model.farm.Farm;
import model.farm.Farmer;
import model.farm.building.House;
import viewer.GameViewer;

public class FarmViewer extends GameViewer {
    private Farm farm;

    public FarmViewer(Farm farm) {
        this.farm = farm;
    }

    @Override
    public void draw(GUI gui) {
        this.drawBuildings(this.farm.getBuildings(), gui);
        this.drawFences(this.farm, gui);
        this.drawHUD(this.farm.getTime(), this.farm.getWeather(),new HUDViewer(), gui); // TODO temporary
        this.drawFarmer(this.farm.getFarmer(), new FarmerViewer(), gui);
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

    // TODO temporary
    private void drawHUD(Time time, Weather weather, HUDViewer hudViewer, GUI gui) {
        hudViewer.draw(time, weather, gui);
    }

}
