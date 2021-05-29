package model.farm.building.crop_field.state;

import model.InGameTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotPlantedTest {
    private NotPlanted state;
    private final InGameTime zero = new InGameTime(0);

    @BeforeEach
    void setUp() {
        state = new NotPlanted();
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
    void changeHarvestAmountIsNoOp() {
        Assertions.assertEquals(0, state.getHarvestAmount());
        state.changeHarvestAmount(90);
        Assertions.assertEquals(0, state.getHarvestAmount());
        state.changeHarvestAmount(-15);
        Assertions.assertEquals(0, state.getHarvestAmount());
    }
}
