package viewer.farm;

import gui.Color;
import gui.GUI;
import gui.drawer.entity.BuildingDrawer;
import model.farm.building.House;
import model.farm.building.Warehouse;

public class WarehouseViewer {
    private final static Color WAREHOUSE_WALL_COLOR = new Color("#eeeeef");
    private final static Color WAREHOUSE_ROOF_COLOR = new Color("#0099c2");

    public void draw(Warehouse warehouse, GUI gui) {
        BuildingDrawer drawer = new BuildingDrawer(gui, WAREHOUSE_WALL_COLOR, WAREHOUSE_ROOF_COLOR);
        drawer.draw(warehouse.getTopLeftPosition());
    }
}
