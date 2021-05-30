package viewer.farm;

import gui.GUI;
import model.farm.Farm;
import model.farm.building.Building;
import viewer.farm.element.entity.NewBuildingZoneViewer;

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
        zoneViewer.draw(this.farm.getBuildings(), newBuilding, gui);
    }
}
