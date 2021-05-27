package viewer.farm;

import gui.GUI;
import model.farm.Farm;
import model.farm.entity.Entity;
import viewer.farm.element.entity.FarmerViewer;

public class FarmWithFarmerViewer extends FarmViewer {
    public FarmWithFarmerViewer(Farm farm) {
        super(farm);
    }

    @Override
    public void draw(GUI gui) {
        super.draw(gui);
        this.drawFarmer(this.farm.getFarmer(), new FarmerViewer(), gui);
    }

    private void drawFarmer(Entity farmer, FarmerViewer farmerViewer, GUI gui) {
        farmerViewer.draw(farmer, gui);
    }
}
