package model.farm.building.crop_field.state;

import model.InGameTime;
import model.farm.building.crop_field.CropField;
import model.farm.data.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlantedTest {
    private CropField field;
    private Crop crop;
    private Planted state;

    @BeforeEach
    void setUp() {
        field = Mockito.mock(CropField.class);
        crop = Mockito.mock(Crop.class);
        Mockito.when(crop.getBaseHarvestAmount()).thenReturn(10);
        Mockito.when(crop.getGrowTime()).thenReturn(new InGameTime(5));
        state = new Planted(field, crop);
    }

    @Test
    void startWithCropBaseValues() {
        Assertions.assertSame(crop, state.getCrop());
        Assertions.assertEquals(10, state.getHarvestAmount());
        Assertions.assertEquals(new InGameTime(5), state.getRemainingTime());
    }

    @Test
    void setRemainingTime() {
        state.setRemainingTime(new InGameTime(5));
        Assertions.assertEquals(new InGameTime(5), state.getRemainingTime());
        Mockito.verify(field, Mockito.never()).setState(Mockito.any());

        state.setRemainingTime(new InGameTime(8));
        Assertions.assertEquals(new InGameTime(8), state.getRemainingTime());
        Mockito.verify(field, Mockito.never()).setState(Mockito.any());

        state.setRemainingTime(new InGameTime(1));
        Assertions.assertEquals(new InGameTime(1), state.getRemainingTime());
        Mockito.verify(field, Mockito.never()).setState(Mockito.any());
    }

    @Test
    void setRemainingTimeZero() {
        state.setRemainingTime(new InGameTime(0));
        Mockito.verify(field, Mockito.times(1))
                .setState(Mockito.argThat((ReadyToHarvest fieldState) -> fieldState.getCrop() == state.getCrop()));
    }

    @Test
    void setRemainingTimeNegative() {
        state.setRemainingTime(new InGameTime(-5));
        Mockito.verify(field, Mockito.times(1))
                .setState(Mockito.argThat((ReadyToHarvest fieldState) -> fieldState.getCrop() == state.getCrop()));
    }

    @Test
    void changeHarvestAmount() {
        state.changeHarvestAmount(0.1);
        Assertions.assertEquals(10, state.getHarvestAmount());
        state.changeHarvestAmount(0.7);
        Assertions.assertEquals(10, state.getHarvestAmount());
        state.changeHarvestAmount(0.3);
        Assertions.assertEquals(11, state.getHarvestAmount());
        state.changeHarvestAmount(2.5);
        Assertions.assertEquals(13, state.getHarvestAmount());
        state.changeHarvestAmount(-3.9);
        Assertions.assertEquals(9, state.getHarvestAmount());
        Mockito.verify(field, Mockito.never()).setState(Mockito.any());
    }

    @Test
    void harvestAmountZero() {
        state.changeHarvestAmount(-10);
        Mockito.verify(field, Mockito.times(1))
                .setState(Mockito.any(NotPlanted.class));
    }

    @Test
    void harvestAmountNegative() {
        state.changeHarvestAmount(-20);
        Mockito.verify(field, Mockito.times(1))
                .setState(Mockito.any(NotPlanted.class));
    }
}
