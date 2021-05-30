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
        this.field = Mockito.mock(CropField.class);
        this.crop = Mockito.mock(Crop.class);
        Mockito.when(this.crop.getBaseHarvestAmount()).thenReturn(10);
        Mockito.when(this.crop.getGrowTime()).thenReturn(new InGameTime(5));
        this.state = new Planted(this.field, this.crop);
    }

    @Test
    void startWithCropBaseValues() {
        Assertions.assertSame(this.crop, this.state.getCrop());
        Assertions.assertEquals(10, this.state.getHarvestAmount());
        Assertions.assertEquals(new InGameTime(5), this.state.getRemainingTime());
    }

    @Test
    void setRemainingTime() {
        this.state.setRemainingTime(new InGameTime(5));
        Assertions.assertEquals(new InGameTime(5), this.state.getRemainingTime());
        Mockito.verify(this.field, Mockito.never()).setState(Mockito.any());

        this.state.setRemainingTime(new InGameTime(8));
        Assertions.assertEquals(new InGameTime(8), this.state.getRemainingTime());
        Mockito.verify(this.field, Mockito.never()).setState(Mockito.any());

        this.state.setRemainingTime(new InGameTime(1));
        Assertions.assertEquals(new InGameTime(1), this.state.getRemainingTime());
        Mockito.verify(this.field, Mockito.never()).setState(Mockito.any());
    }

    @Test
    void setRemainingTimeZero() {
        this.state.setRemainingTime(new InGameTime(0));
        Mockito.verify(this.field, Mockito.times(1))
                .setState(Mockito.argThat((ReadyToHarvest fieldState) -> fieldState.getCrop() == this.state.getCrop()));
    }

    @Test
    void setRemainingTimeNegative() {
        this.state.setRemainingTime(new InGameTime(-5));
        Mockito.verify(this.field, Mockito.times(1))
                .setState(Mockito.argThat((ReadyToHarvest fieldState) -> fieldState.getCrop() == this.state.getCrop()));
    }

    @Test
    void changeHarvestAmount() {
        this.state.changeHarvestAmount(0.1);
        Assertions.assertEquals(10, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(0.7);
        Assertions.assertEquals(10, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(0.3);
        Assertions.assertEquals(11, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(2.5);
        Assertions.assertEquals(13, this.state.getHarvestAmount());
        this.state.changeHarvestAmount(-3.9);
        Assertions.assertEquals(9, this.state.getHarvestAmount());
        Mockito.verify(this.field, Mockito.never()).setState(Mockito.any());
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
}
