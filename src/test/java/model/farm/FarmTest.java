package model.farm;

import model.Position;
import model.farm.building.BuildingSet;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;
import model.region.RectangleRegion;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class FarmTest {
    private Farm farm;
    private BuildingSet buildingSet;

    @BeforeEach
    void setUp() {
        this.buildingSet = Mockito.mock(BuildingSet.class);
    }

    @Property
    void getInsideRegionMustBeInsideFarm(
            @ForAll @IntRange(min = 3) int width,
            @ForAll @IntRange(min = 3) int height) {
        this.farm = new Farm(width, height, this.buildingSet);
        RectangleRegion inside = this.farm.getInsideRegion();

        Assertions.assertTrue(new RectangleRegion(new Position(0, 0), width, height).contains(inside));
    }

    @Test
    void isTraversable() {
        this.farm = new Farm(40, 20, this.buildingSet);

        Mockito.when(this.buildingSet.isTraversable(Mockito.any())).thenReturn(false);
        for (int i = 1; i < 39; i++) {
            for (int j = 1; j < 19; j++) {
                Assertions.assertFalse(this.farm.isTraversable(new Position(i, j)));
            }
        }

        Assertions.assertFalse(this.farm.isTraversable(new Position(0, 0)));
        Assertions.assertFalse(this.farm.isTraversable(new Position(39, 0)));
        Assertions.assertFalse(this.farm.isTraversable(new Position(0, 19)));
        Assertions.assertFalse(this.farm.isTraversable(new Position(-39, 5)));
        Assertions.assertFalse(this.farm.isTraversable(new Position(45, 34)));

        Mockito.when(this.buildingSet.isTraversable(Mockito.any())).thenReturn(true);
        for (int i = 1; i < 39; i++) {
            for (int j = 1; j < 19; j++) {
                Assertions.assertTrue(this.farm.isTraversable(new Position(i, j)));
            }
        }

        Assertions.assertFalse(this.farm.isTraversable(new Position(0, 0)));
        Assertions.assertFalse(this.farm.isTraversable(new Position(39, 0)));
        Assertions.assertFalse(this.farm.isTraversable(new Position(0, 19)));
        Assertions.assertFalse(this.farm.isTraversable(new Position(-39, 5)));
        Assertions.assertFalse(this.farm.isTraversable(new Position(45, 34)));
    }

    @Test
    void getAllItems() {
        List<Crop> crops = new ArrayList<>();
        List<Livestock> livestocks = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            crops.add(new Crop("c" + i));

            Livestock livestock = Mockito.mock(Livestock.class);
            Mockito.when(livestock.getProducedItem()).thenReturn(new AnimalProduct("p" + i));
            livestocks.add(Mockito.mock(Livestock.class));
        }

        Farm farm;
        farm = new Farm(20, 20, this.buildingSet);
        Assertions.assertTrue(farm.getAllItems().isEmpty());
        farm.addCropTypes(crops);
        Assertions.assertEquals(3, farm.getAllItems().size());
        Assertions.assertTrue(farm.getAllItems().containsAll(crops));

        farm = new Farm(20, 20, this.buildingSet);
        farm.addLivestockTypes(livestocks);
        Assertions.assertEquals(3, farm.getAllItems().size());
        for (Livestock livestock: livestocks) {
            Assertions.assertTrue(farm.getAllItems().contains(livestock.getProducedItem()));
        }

        farm = new Farm(20, 20, this.buildingSet);
        farm.addCropTypes(crops);
        farm.addLivestockTypes(livestocks);
        Assertions.assertEquals(6, farm.getAllItems().size());
        Assertions.assertTrue(farm.getAllItems().containsAll(crops));
        for (Livestock livestock: livestocks) {
            Assertions.assertTrue(farm.getAllItems().contains(livestock.getProducedItem()));
        }
    }
}
