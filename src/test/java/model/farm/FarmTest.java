package model.farm;

import model.Position;
import model.farm.building.Building;
import model.farm.building.BuildingSet;
import model.farm.building.Edifice;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;
import model.region.RectangleRegion;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class FarmTest {
    private Farm farm;
    private BuildingSet buildingSet;

    @BeforeEach
    void setUp() {
        buildingSet = Mockito.mock(BuildingSet.class);
    }

    @Property
    void getInsideRegion(@ForAll @Positive int width, @ForAll @Positive int height) {
        farm = new Farm(width, height, buildingSet);
        RectangleRegion inside = farm.getInsideRegion();

        // The inside region must always be contained in the region that covers the whole farm
        Assertions.assertTrue(new RectangleRegion(new Position(0, 0), width, height).contains(inside));
        Assertions.assertEquals(width - 2, inside.getWidth());
        Assertions.assertEquals(height - 2, inside.getHeight());
    }

    @Test
    void isTraversable() {
        farm = new Farm(40, 20, buildingSet);

        Mockito.when(buildingSet.isTraversable(Mockito.any())).thenReturn(false);
        for (int i = 1; i < 39; i++) {
            for (int j = 1; j < 19; j++) {
                Assertions.assertFalse(farm.isTraversable(new Position(i, j)));
            }
        }

        Assertions.assertFalse(farm.isTraversable(new Position(0, 0)));
        Assertions.assertFalse(farm.isTraversable(new Position(39, 0)));
        Assertions.assertFalse(farm.isTraversable(new Position(0, 19)));
        Assertions.assertFalse(farm.isTraversable(new Position(-39, 5)));
        Assertions.assertFalse(farm.isTraversable(new Position(45, 34)));

        Mockito.when(buildingSet.isTraversable(Mockito.any())).thenReturn(true);
        for (int i = 1; i < 39; i++) {
            for (int j = 1; j < 19; j++) {
                Assertions.assertTrue(farm.isTraversable(new Position(i, j)));
            }
        }

        Assertions.assertFalse(farm.isTraversable(new Position(0, 0)));
        Assertions.assertFalse(farm.isTraversable(new Position(39, 0)));
        Assertions.assertFalse(farm.isTraversable(new Position(0, 19)));
        Assertions.assertFalse(farm.isTraversable(new Position(-39, 5)));
        Assertions.assertFalse(farm.isTraversable(new Position(45, 34)));
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
        farm = new Farm(20, 20, buildingSet);
        Assertions.assertTrue(farm.getAllItems().isEmpty());
        farm.addCropTypes(crops);
        Assertions.assertEquals(3, farm.getAllItems().size());
        Assertions.assertTrue(farm.getAllItems().containsAll(crops));

        farm = new Farm(20, 20, buildingSet);
        farm.addLivestockTypes(livestocks);
        Assertions.assertEquals(3, farm.getAllItems().size());
        for (Livestock livestock: livestocks) {
            Assertions.assertTrue(farm.getAllItems().contains(livestock.getProducedItem()));
        }

        farm = new Farm(20, 20, buildingSet);
        farm.addCropTypes(crops);
        farm.addLivestockTypes(livestocks);
        Assertions.assertEquals(6, farm.getAllItems().size());
        Assertions.assertTrue(farm.getAllItems().containsAll(crops));
        for (Livestock livestock: livestocks) {
            Assertions.assertTrue(farm.getAllItems().contains(livestock.getProducedItem()));
        }
    }
}
