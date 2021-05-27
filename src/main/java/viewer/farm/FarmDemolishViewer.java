package viewer.farm;

import gui.GUI;
import model.farm.DemolishMarker;
import model.farm.Farm;
import viewer.farm.element.entity.DemolishMarkerViewer;

public class FarmDemolishViewer extends FarmViewer {
    private final DemolishMarker marker;

    public FarmDemolishViewer(Farm farm, DemolishMarker marker) {
        super(farm);
        this.marker = marker;
    }

    @Override
    public void draw(GUI gui) {
        super.draw(gui);
        this.drawDemolishMarker(this.marker, new DemolishMarkerViewer(), gui);
    }

    private void drawDemolishMarker(DemolishMarker marker, DemolishMarkerViewer markerViewer, GUI gui) {
        markerViewer.draw(this.farm.getBuildings(), marker, gui);
    }
}
