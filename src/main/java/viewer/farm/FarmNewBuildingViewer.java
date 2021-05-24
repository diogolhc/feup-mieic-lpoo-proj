package viewer.farm;

import gui.GUI;
import model.farm.Farm;
import model.farm.Farmer;
import model.farm.building.Building;

public class FarmNewBuildingViewer extends FarmViewer {
    private final Building newBuilding;

    public FarmNewBuildingViewer(Farm farm, Building newBuilding) {
        super(farm);
        this.newBuilding = newBuilding;
    }

    @Override
    public void draw(GUI gui) {
        super.draw(gui);
        this.drawNewBuildingZone(this.newBuilding, new NewBuildingZoneViewer(), gui);
    }

    private void drawNewBuildingZone(Building newBuilding, NewBuildingZoneViewer zoneViewer, GUI gui) {
        zoneViewer.draw(newBuilding, gui);
    }
}
