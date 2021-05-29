package model.farm.building.crop_field.state;

import model.InGameTime;
import model.farm.data.item.Crop;
import model.farm.building.crop_field.CropField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ReadyToHarvestTest {
    private CropField field;
    private ReadyToHarvest state;
    private final InGameTime zero = new InGameTime(0);

    @BeforeEach
    void setUp() {
        field = Mockito.mock(CropField.class);
        state = new ReadyToHarvest(field, Mockito.mock(Crop.class), 10);
    }

    @Test
    void setRemainingTimeIsNoOp() {
        Assertions.assertEquals(zero, state.getRemainingTime());
        state.setRemainingTime(new InGameTime(20));
        Assertions.assertEquals(zero, state.getRemainingTime());
        state.setRemainingTime(new InGameTime(0));
        Assertions.assertEquals(zero, state.getRemainingTime());
    }

    @Test
    void changeHarvestAmount() {
        state.changeHarvestAmount(-0.1);
        Assertions.assertEquals(9, state.getHarvestAmount());
        state.changeHarvestAmount(-0.7);
        Assertions.assertEquals(9, state.getHarvestAmount());
        state.changeHarvestAmount(-0.2);
        Assertions.assertEquals(9, state.getHarvestAmount());
        state.changeHarvestAmount(-2.5);
        Assertions.assertEquals(6, state.getHarvestAmount());
        state.changeHarvestAmount(-3.6);
        Assertions.assertEquals(2, state.getHarvestAmount());
    }

    @Test
    void changeHarvestAmountEffectsAreAlwaysNegative() {
        state.changeHarvestAmount(0.1);
        Assertions.assertEquals(9, state.getHarvestAmount());
        state.changeHarvestAmount(-0.7);
        Assertions.assertEquals(9, state.getHarvestAmount());
        state.changeHarvestAmount(0.2);
        Assertions.assertEquals(9, state.getHarvestAmount());
        state.changeHarvestAmount(-2.5);
        Assertions.assertEquals(6, state.getHarvestAmount());
        state.changeHarvestAmount(3.6);
        Assertions.assertEquals(2, state.getHarvestAmount());
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

    @Test
    void harvestAmountZeroEffectsAreAlwaysNegative() {
        state.changeHarvestAmount(10);
        Mockito.verify(field, Mockito.times(1))
                .setState(Mockito.any(NotPlanted.class));
    }

    @Test
    void harvestAmountNegativeEffectsAreAlwaysNegative() {
        state.changeHarvestAmount(20);
        Mockito.verify(field, Mockito.times(1))
                .setState(Mockito.any(NotPlanted.class));
    }
}
