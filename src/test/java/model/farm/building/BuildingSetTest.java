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
        this.house = Mockito.mock(Edifice.class);
        this.market = Mockito.mock(Edifice.class);
        this.warehouse = Mockito.mock(Edifice.class);
        this.buildingSet = new BuildingSet(this.house, this.market, this.warehouse);

        this.cropFields = new ArrayList<>();
        this.cropFields.add(Mockito.mock(CropField.class));
        this.cropFields.add(Mockito.mock(CropField.class));
        this.cropFields.add(Mockito.mock(CropField.class));

        this.stockyards = new ArrayList<>();
        this.stockyards.add(Mockito.mock(Stockyard.class));
        this.stockyards.add(Mockito.mock(Stockyard.class));

        for (CropField cropField: this.cropFields) {
            this.buildingSet.addCropField(cropField);
        }
        for (Stockyard stockyard: this.stockyards) {
            this.buildingSet.addStockyard(stockyard);
        }

        this.buildings = new ArrayList<>();
        this.buildings.add(this.house);
        this.buildings.add(this.market);
        this.buildings.add(this.warehouse);
        this.buildings.addAll(this.cropFields);
        this.buildings.addAll(this.stockyards);
    }

    @Property
    public void isTraversableWhenNoUntraversableRegionContainsPosition(
            @ForAll @Size(value = 8) List<Boolean> traversable) {
        for (int i = 0; i < 8; i++) {
            Building building = this.buildings.get(i);
            boolean b = traversable.get(i);
            Mockito.when(building.getUntraversableRegion()).thenReturn((Region) position -> b);
        }

        if (traversable.contains(true)) {
            Assertions.assertFalse(this.buildingSet.isTraversable(Mockito.mock(Position.class)));
        } else {
            Assertions.assertTrue(this.buildingSet.isTraversable(Mockito.mock(Position.class)));
        }
    }

    @Property
    public void isOccupiedWhenIntersectsAtLeastOneOccupiedRegion(
            @ForAll @Size(value = 8) List<Boolean> traversable) {
        for (int i = 0; i < 8; i++) {
            Building building = this.buildings.get(i);
            boolean b = traversable.get(i);
            RectangleRegion region = Mockito.mock(RectangleRegion.class);
            Mockito.when(region.intersects(Mockito.any(RectangleRegion.class))).thenReturn(b);
            Mockito.when(building.getOccupiedRegion()).thenReturn(region);
        }

        if (traversable.contains(true)) {
            Assertions.assertTrue(this.buildingSet.isOccupied(Mockito.mock(RectangleRegion.class)));
        } else {
            Assertions.assertFalse(this.buildingSet.isOccupied(Mockito.mock(RectangleRegion.class)));
        }
    }

    @Test
    public void getDemolishableBuildings() {
        List<Building> demolishable = this.buildingSet.getDemolishableBuildings();
        Assertions.assertEquals(5, demolishable.size());
        Assertions.assertTrue(demolishable.containsAll(this.cropFields));
        Assertions.assertTrue(demolishable.containsAll(this.stockyards));
        Assertions.assertFalse(demolishable.contains(this.house));
        Assertions.assertFalse(demolishable.contains(this.market));
        Assertions.assertFalse(demolishable.contains(this.warehouse));
    }

    @Test
    public void getAlBuildings() {
        List<Building> all = this.buildingSet.getAllBuildings();
        Assertions.assertEquals(8, all.size());
        Assertions.assertTrue(all.containsAll(this.cropFields));
        Assertions.assertTrue(all.containsAll(this.stockyards));
        Assertions.assertTrue(all.contains(this.house));
        Assertions.assertTrue(all.contains(this.market));
        Assertions.assertTrue(all.contains(this.warehouse));
    }
}
