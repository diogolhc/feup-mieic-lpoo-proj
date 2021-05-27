package viewer.farm;

import gui.GUI;
import model.farm.Farm;
import model.farm.building.edifice.House;
import viewer.farm.element.RestingMarkerViewer;

public class FarmRestingViewer extends FarmViewer {
    public FarmRestingViewer(Farm farm) {
        super(farm);
    }

    @Override
    public void draw(GUI gui) {
        super.draw(gui);
        this.drawRestingMarker(this.farm.getBuildings().getHouse(), new RestingMarkerViewer(), gui);
    }

    private void drawRestingMarker(House house, RestingMarkerViewer markerViewer, GUI gui) {
        markerViewer.draw(house, gui);
    }
}
