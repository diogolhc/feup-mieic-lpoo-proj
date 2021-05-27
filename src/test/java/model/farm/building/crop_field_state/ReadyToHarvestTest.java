package model.farm.building.crop_field_state;

import model.InGameTime;
import model.farm.building.CropField;
import model.farm.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ReadyToHarvestTest {
    private ReadyToHarvest state;
    private InGameTime zero = new InGameTime(0);

    @BeforeEach
    void setUp() {
        state = new ReadyToHarvest(Mockito.mock(CropField.class), Mockito.mock(Crop.class), 1);
    }

    @Test
    void setRemainingTimeIsNoOp() {
        Assertions.assertEquals(zero, state.getRemainingTime());
        state.setRemainingTime(new InGameTime(20));
        Assertions.assertEquals(zero, state.getRemainingTime());
        state.setRemainingTime(new InGameTime(0));
        Assertions.assertEquals(zero, state.getRemainingTime());
    }
}
