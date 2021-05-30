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
        this.field = Mockito.mock(CropField.class);
        this.state = new ReadyToHarvest(this.field, Mockito.mock(Crop.class), 10);
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
    void changeHarvestAmount() {
        this.state.changeHarvestAmount(-0.1);
        Assertions.assertEquals(9, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(-0.7);
        Assertions.assertEquals(9, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(-0.2);
        Assertions.assertEquals(9, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(-2.5);
        Assertions.assertEquals(6, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(-3.6);
        Assertions.assertEquals(2, this.state.getHarvestAmount());
    }

    @Test
    void changeHarvestAmountEffectsAreAlwaysNegative() {
        this.state.changeHarvestAmount(0.1);
        Assertions.assertEquals(9, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(-0.7);
        Assertions.assertEquals(9, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(0.2);
        Assertions.assertEquals(9, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(-2.5);
        Assertions.assertEquals(6, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(3.6);
        Assertions.assertEquals(2, this.state.getHarvestAmount());
    }

    @Test
    void harvestAmountZero() {
        this.state.changeHarvestAmount(-10);
        Mockito.verify(this.field, Mockito.times(1))
                .setState(Mockito.any(NotPlanted.class));
    }

    @Test
    void harvestAmountNegative() {
        this.state.changeHarvestAmount(-20);
        Mockito.verify(this.field, Mockito.times(1))
                .setState(Mockito.any(NotPlanted.class));
    }

    @Test
    void harvestAmountZeroEffectsAreAlwaysNegative() {
        this.state.changeHarvestAmount(10);
        Mockito.verify(this.field, Mockito.times(1))
                .setState(Mockito.any(NotPlanted.class));
    }

    @Test
    void harvestAmountNegativeEffectsAreAlwaysNegative() {
        this.state.changeHarvestAmount(20);
        Mockito.verify(this.field, Mockito.times(1))
                .setState(Mockito.any(NotPlanted.class));
    }
}
