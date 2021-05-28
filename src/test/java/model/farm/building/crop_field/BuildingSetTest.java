package model.farm.building.crop_field;

import model.Position;
import model.farm.building.BuildingSet;
import model.farm.building.CropField;
import model.farm.building.edifice.House;
import model.farm.building.edifice.Market;
import model.farm.building.edifice.Warehouse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuildingSetTest {
    private BuildingSet buildingSet;

    @BeforeEach
    public void setUp() {
        buildingSet = new BuildingSet();
        buildingSet.setHouse(new House(new Position(5, 5)));
        buildingSet.setMarket(new Market(new Position(11, 5)));
        buildingSet.setWarehouse(new Warehouse(new Position(17, 5)));
        buildingSet.addCropField(new CropField(new Position(0, 0)));
        buildingSet.addCropField(new CropField(new Position(1 ,4)));
    }

    @Test
    public void isTraversable() {
        Assertions.assertTrue(buildingSet.isTraversable(new Position(0, 0)));
        Assertions.assertTrue(buildingSet.isTraversable(new Position(20, 12)));
        Assertions.assertTrue(buildingSet.isTraversable(new Position(1, 4)));
        Assertions.assertTrue(buildingSet.isTraversable(new Position(5, 5)));
        Assertions.assertFalse(buildingSet.isTraversable(new Position(6, 5)));
        Assertions.assertFalse(buildingSet.isTraversable(new Position(1, 1)));
        Assertions.assertFalse(buildingSet.isTraversable(new Position(3, 6)));
        Assertions.assertFalse(buildingSet.isTraversable(new Position(9, 10)));
    }
}
