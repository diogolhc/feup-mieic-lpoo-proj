package model.farm.building;

import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.stockyard.Stockyard;
import model.region.RectangleRegion;
import model.region.Region;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class BuildingSetTest {
    private BuildingSet buildingSet;
    private Edifice house;
    private Edifice market;
    private Edifice warehouse;
    private List<CropField> cropFields;
    private List<Stockyard> stockyards;
    private List<Building> buildings;

    @BeforeEach
    @BeforeProperty
    public void setUp() {
        house = Mockito.mock(Edifice.class);
        market = Mockito.mock(Edifice.class);
        warehouse = Mockito.mock(Edifice.class);
        buildingSet = new BuildingSet(house, market, warehouse);

        cropFields = new ArrayList<>();
        cropFields.add(Mockito.mock(CropField.class));
        cropFields.add(Mockito.mock(CropField.class));
        cropFields.add(Mockito.mock(CropField.class));

        stockyards = new ArrayList<>();
        stockyards.add(Mockito.mock(Stockyard.class));
        stockyards.add(Mockito.mock(Stockyard.class));

        for (CropField cropField: cropFields) {
            buildingSet.addCropField(cropField);
        }
        for (Stockyard stockyard: stockyards) {
            buildingSet.addStockyard(stockyard);
        }

        buildings = new ArrayList<>();
        buildings.add(house);
        buildings.add(market);
        buildings.add(warehouse);
        buildings.addAll(cropFields);
        buildings.addAll(stockyards);
    }

    @Property
    public void isTraversableWhenNoUntraversableRegionContainsPosition(
            @ForAll @Size(value = 8) List<Boolean> traversable) {
        for (int i = 0; i < 8; i++) {
            Building building = buildings.get(i);
            boolean b = traversable.get(i);
            Mockito.when(building.getUntraversableRegion()).thenReturn((Region) position -> b);
        }

        if (traversable.contains(true)) {
            Assertions.assertFalse(buildingSet.isTraversable(Mockito.mock(Position.class)));
        } else {
            Assertions.assertTrue(buildingSet.isTraversable(Mockito.mock(Position.class)));
        }
    }

    @Property
    public void isOccupiedWhenIntersectsAtLeastOneOccupiedRegion(
            @ForAll @Size(value = 8) List<Boolean> traversable) {
        for (int i = 0; i < 8; i++) {
            Building building = buildings.get(i);
            boolean b = traversable.get(i);
            RectangleRegion region = Mockito.mock(RectangleRegion.class);
            Mockito.when(region.intersects(Mockito.any(RectangleRegion.class))).thenReturn(b);
            Mockito.when(building.getOccupiedRegion()).thenReturn(region);
        }

        if (traversable.contains(true)) {
            Assertions.assertTrue(buildingSet.isOccupied(Mockito.mock(RectangleRegion.class)));
        } else {
            Assertions.assertFalse(buildingSet.isOccupied(Mockito.mock(RectangleRegion.class)));
        }
    }

    @Test
    public void getDemolishableBuildings() {
        List<Building> demolishable = buildingSet.getDemolishableBuildings();
        Assertions.assertEquals(5, demolishable.size());
        Assertions.assertTrue(demolishable.containsAll(cropFields));
        Assertions.assertTrue(demolishable.containsAll(stockyards));
        Assertions.assertFalse(demolishable.contains(house));
        Assertions.assertFalse(demolishable.contains(market));
        Assertions.assertFalse(demolishable.contains(warehouse));
    }

    @Test
    public void getAlBuildings() {
        List<Building> all = buildingSet.getAllBuildings();
        Assertions.assertEquals(8, all.size());
        Assertions.assertTrue(all.containsAll(cropFields));
        Assertions.assertTrue(all.containsAll(stockyards));
        Assertions.assertTrue(all.contains(house));
        Assertions.assertTrue(all.contains(market));
        Assertions.assertTrue(all.contains(warehouse));
    }
}
