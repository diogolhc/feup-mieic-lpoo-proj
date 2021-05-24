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

public class FarmWithFarmerViewer extends FarmViewer {
    public FarmWithFarmerViewer(Farm farm) {
        super(farm);
    }

    @Override
    public void draw(GUI gui) {
        super.draw(gui);
        this.drawFarmer(this.farm.getFarmer(), new FarmerViewer(), gui);
    }

    private void drawFarmer(Farmer farmer, FarmerViewer farmerViewer, GUI gui) {
        farmerViewer.draw(farmer, gui);
    }
}
