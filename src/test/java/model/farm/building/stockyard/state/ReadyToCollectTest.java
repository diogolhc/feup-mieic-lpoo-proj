package model.farm.building.stockyard.state;

import model.InGameTime;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

class ReadyToCollectTest {
    Stockyard stockyard;
    ReadyToCollect state;

    @BeforeEach
    void setUp() {
        this.stockyard = Mockito.mock(Stockyard.class);
        this.state = new ReadyToCollect(stockyard, 10);
    }

    @Test
    void setRemainingTimeIsNoOp() {
        Assertions.assertEquals(new InGameTime(0), state.getRemainingTime());
        state.setRemainingTime(new InGameTime(20));
        Assertions.assertEquals(new InGameTime(0), state.getRemainingTime());
        state.setRemainingTime(new InGameTime(0));
        Assertions.assertEquals(new InGameTime(0), state.getRemainingTime());
    }

    @Test
    void changeCollectAmount() {
        state.changeCollectAmount(-0.1);
        Assertions.assertEquals(9, state.getCollectAmount());
        state.changeCollectAmount(-0.7);
        Assertions.assertEquals(9, state.getCollectAmount());
        state.changeCollectAmount(-0.2);
        Assertions.assertEquals(9, state.getCollectAmount());
        state.changeCollectAmount(-2.5);
        Assertions.assertEquals(6, state.getCollectAmount());
        state.changeCollectAmount(-3.6);
        Assertions.assertEquals(2, state.getCollectAmount());
    }

    @Test
    void changeCollectAmountEffectsAreAlwaysNegative() {
        state.changeCollectAmount(0.1);
        Assertions.assertEquals(9, state.getCollectAmount());
        state.changeCollectAmount(-0.7);
        Assertions.assertEquals(9, state.getCollectAmount());
        state.changeCollectAmount(0.2);
        Assertions.assertEquals(9, state.getCollectAmount());
        state.changeCollectAmount(-2.5);
        Assertions.assertEquals(6, state.getCollectAmount());
        state.changeCollectAmount(3.6);
        Assertions.assertEquals(2, state.getCollectAmount());
    }

    @Test
    void collectAmountZero() {
        state.changeCollectAmount(-10);
        Mockito.verify(stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }

    @Test
    void collectAmountNegative() {
        state.changeCollectAmount(-20);
        Mockito.verify(stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }

    @Test
    void collectAmountZeroEffectsAreAlwaysNegative() {
        state.changeCollectAmount(10);
        Mockito.verify(stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }

    @Test
    void collectAmountNegativeEffectsAreAlwaysNegative() {
        state.changeCollectAmount(20);
        Mockito.verify(stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }
}
