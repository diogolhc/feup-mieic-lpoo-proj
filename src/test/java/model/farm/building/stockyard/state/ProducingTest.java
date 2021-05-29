package model.farm.building.stockyard.state;

import model.InGameTime;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

class ProducingTest {
    Producing state;
    Stockyard stockyard;

    @BeforeEach
    void setUp() {
        this.stockyard = Mockito.mock(Stockyard.class);
        Mockito.when(this.stockyard.getBaseProducedAmount()).thenReturn(10);

        Livestock livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getProducedItem()).thenReturn(Mockito.mock(AnimalProduct.class));
        Mockito.when(livestock.getProducedItem().getProductionTime()).thenReturn(new InGameTime(10));
        Mockito.when(this.stockyard.getLivestockType()).thenReturn(livestock);

        this.state = new Producing(this.stockyard);
    }

    @Test
    void startWithStockyardBaseValues() {
        Assertions.assertEquals(10, state.getCollectAmount());
        Assertions.assertEquals(new InGameTime(10), state.getRemainingTime());
    }

    @Test
    void setRemainingTime() {
        state.setRemainingTime(new InGameTime(5));
        Assertions.assertEquals(new InGameTime(5), state.getRemainingTime());
        Mockito.verify(stockyard, Mockito.never()).setState(Mockito.any());

        state.setRemainingTime(new InGameTime(8));
        Assertions.assertEquals(new InGameTime(8), state.getRemainingTime());
        Mockito.verify(stockyard, Mockito.never()).setState(Mockito.any());

        state.setRemainingTime(new InGameTime(1));
        Assertions.assertEquals(new InGameTime(1), state.getRemainingTime());
        Mockito.verify(stockyard, Mockito.never()).setState(Mockito.any());
    }

    @Test
    void setRemainingTimeZero() {
        state.setRemainingTime(new InGameTime(0));
        Mockito.verify(stockyard, Mockito.times(1)).setState(
                Mockito.argThat((ReadyToCollect newState) -> newState.getCollectAmount() == state.getCollectAmount()));
    }

    @Test
    void setRemainingTimeNegative() {
        state.setRemainingTime(new InGameTime(-5));
        Mockito.verify(stockyard, Mockito.times(1)).setState(
                Mockito.argThat((ReadyToCollect newState) -> newState.getCollectAmount() == state.getCollectAmount()));
    }

    @Test
    void changeCollectAmount() {
        state.changeCollectAmount(0.1);
        Assertions.assertEquals(10, state.getCollectAmount());
        state.changeCollectAmount(0.7);
        Assertions.assertEquals(10, state.getCollectAmount());
        state.changeCollectAmount(0.3);
        Assertions.assertEquals(11, state.getCollectAmount());
        state.changeCollectAmount(2.5);
        Assertions.assertEquals(13, state.getCollectAmount());
        state.changeCollectAmount(-3.9);
        Assertions.assertEquals(9, state.getCollectAmount());
        Mockito.verify(stockyard, Mockito.never()).setState(Mockito.any());
    }

    @Test
    void harvestAmountZero() {
        state.changeCollectAmount(-10);
        Mockito.verify(stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }

    @Test
    void harvestAmountNegative() {
        state.changeCollectAmount(-20);
        Mockito.verify(stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }
}
