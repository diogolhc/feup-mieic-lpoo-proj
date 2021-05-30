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
        this.state = new NotPlanted();
    }

    @Test
    void setRemainingTimeIsNoOp() {
        Assertions.assertEquals(this.zero, this.state.getRemainingTime());
        this.state.setRemainingTime(new InGameTime(20));
        Assertions.assertEquals(this.zero, this.state.getRemainingTime());
        this.state.setRemainingTime(new InGameTime(0));
        Assertions.assertEquals(this.zero, this.state.getRemainingTime());
    }

    @Test
    void changeHarvestAmountIsNoOp() {
        Assertions.assertEquals(0, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(90);
        Assertions.assertEquals(0, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(-15);
        Assertions.assertEquals(0, this.state.getHarvestAmount());
    }
}
