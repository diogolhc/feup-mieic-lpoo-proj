package model.farm.building.crop_field;

import gui.Color;
import model.InGameTime;
import model.Position;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.data.item.Crop;
import model.farm.data.item.CropGrowthStage;
import model.farm.building.crop_field.state.CropFieldState;
import model.region.RectangleRegion;
import model.region.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CropFieldTest {
    private CropField field;

    @BeforeEach
    void setUp() {
        field = new CropField(new Position(4, 5));
    }

    @Test
    void defaultState() {
        Assertions.assertTrue(field.getState() instanceof NotPlanted);
    }

    @Test
    void getUntraversableRegion() {
        Region region = field.getUntraversableRegion();
        Assertions.assertTrue(region instanceof RectangleRegion);
        Assertions.assertEquals(new RectangleRegion(new Position(5, 6), 2, 2), region);
        field = new CropField(new Position(6, 6));
        region = field.getUntraversableRegion();
        Assertions.assertEquals(new RectangleRegion(new Position(7, 7), 2, 2), region);
    }

    @Test
    void getGrowthStage() {
        CropFieldState state = Mockito.mock(CropFieldState.class);
        Crop crop = Mockito.mock(Crop.class);
        CropGrowthStage growthStage = Mockito.mock(CropGrowthStage.class);
        InGameTime time = Mockito.mock(InGameTime.class);

        field.setState(state);

        Mockito.when(state.getRemainingTime()).thenReturn(time);
        Mockito.when(state.getCrop()).thenReturn(crop);

        Mockito.when(crop.getCurrentGrowthStage(time)).thenReturn(growthStage);

        Assertions.assertSame(growthStage, field.getCropGrowthStage());
    }
}
