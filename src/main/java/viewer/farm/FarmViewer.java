package viewer.farm;

import gui.GUI;
import gui.drawer.FencesDrawer;
import model.Farm;
import model.Farmer;
import model.GameModel;
import model.Position;
import viewer.GameViewerState;

public class FarmViewer implements GameViewerState {
    private void drawFarmer(Farmer farmer, FarmerViewer farmerViewer, GUI gui) {
        farmerViewer.draw(farmer, gui);
    }

    private void drawFences(Farm farm, GUI gui) {
        FencesDrawer fencesDrawer = new FencesDrawer(gui);
        fencesDrawer.draw(new Position(0, 0), farm.getWidth(), farm.getHeight());
    }

    @Override
    public void draw(GameModel model, GUI gui) {
        drawFarmer(model.getFarm().getFarmer(), new FarmerViewer(), gui);
        drawFences(model.getFarm(), gui);
    }
}
