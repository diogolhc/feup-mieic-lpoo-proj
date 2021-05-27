package model.farm.building.crop_field_state;

import model.InGameTime;
import model.farm.building.CropField;
import model.farm.data.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlantedTest {
    private CropField field;
    private Planted state;

    @BeforeEach
    void setUp() {
        field = Mockito.mock(CropField.class);
        state = new Planted(field, Mockito.mock(Crop.class));
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
}
