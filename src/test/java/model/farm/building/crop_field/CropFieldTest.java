package model.farm.building.crop_field;

import gui.Color;
import model.InGameTime;
import model.Position;
import model.farm.building.CropField;
import model.farm.data.item.Crop;
import model.farm.data.item.CropGrowthStage;
import model.farm.building.crop_field_state.CropFieldState;
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
    void isTraversable() {
        Assertions.assertTrue(this.field.getTraversableRegion(new Position(3, 3)));
        Assertions.assertTrue(this.field.getTraversableRegion(new Position(4, 5)));
        Assertions.assertTrue(this.field.getTraversableRegion(new Position(5, 5)));
        Assertions.assertTrue(this.field.getTraversableRegion(new Position(4, 6)));
        Assertions.assertFalse(this.field.getTraversableRegion(new Position(5, 6)));
        Assertions.assertFalse(this.field.getTraversableRegion(new Position(6, 6)));
        Assertions.assertFalse(this.field.getTraversableRegion(new Position(5, 7)));
        Assertions.assertFalse(this.field.getTraversableRegion(new Position(6, 7)));

    }

    @Test
    void isInInteractiveZone() {
        Assertions.assertFalse(this.field.getInteractiveRegion(new Position(3, 3)));
        Assertions.assertFalse(this.field.getInteractiveRegion(new Position(3, 5)));
        Assertions.assertFalse(this.field.getInteractiveRegion(new Position(8, 8)));
        Assertions.assertFalse(this.field.getInteractiveRegion(new Position(5, 9)));

        Assertions.assertTrue(this.field.getInteractiveRegion(new Position(4, 5)));
        Assertions.assertTrue(this.field.getInteractiveRegion(new Position(5, 5)));
        Assertions.assertTrue(this.field.getInteractiveRegion(new Position(6, 5)));
        Assertions.assertTrue(this.field.getInteractiveRegion(new Position(7, 5)));

        Assertions.assertTrue(this.field.getInteractiveRegion(new Position(4, 6)));
        Assertions.assertTrue(this.field.getInteractiveRegion(new Position(4, 7)));
        Assertions.assertTrue(this.field.getInteractiveRegion(new Position(4, 8)));

        Assertions.assertTrue(this.field.getInteractiveRegion(new Position(5, 6)));
        Assertions.assertTrue(this.field.getInteractiveRegion(new Position(7, 8)));

    }

    @Test
    void getGrowthStageDefault() {
        Crop crop = Crop.NO_CROP;
        Assertions.assertSame(crop.getGrowthStages().get(0), field.getCropGrowthStage());
    }

    @Test
    void getGrowthStage() {
        CropFieldState state = Mockito.mock(CropFieldState.class);
        Crop crop = Mockito.mock(Crop.class);
        CropGrowthStage growthStage = new CropGrowthStage(new InGameTime(7), 'c', Color.WHITE);
        InGameTime time = new InGameTime(5);

        field.setState(state);

        Mockito.when(state.getRemainingTime()).thenReturn(new InGameTime(5));
        Mockito.when(state.getCrop()).thenReturn(crop);

        Mockito.when(crop.getCurrentGrowthStage(time)).thenReturn(growthStage);


        Assertions.assertSame(growthStage, field.getCropGrowthStage());
    }
}
