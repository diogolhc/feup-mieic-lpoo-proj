package model.farm.building.stockyard.state;

import model.InGameTime;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        Assertions.assertEquals(10, this.state.getCollectAmount());
        Assertions.assertEquals(new InGameTime(10), this.state.getRemainingTime());
    }

    @Test
    void setRemainingTime() {
        this.state.setRemainingTime(new InGameTime(5));
        Assertions.assertEquals(new InGameTime(5), this.state.getRemainingTime());
        Mockito.verify(this.stockyard, Mockito.never()).setState(Mockito.any());

        this.state.setRemainingTime(new InGameTime(8));
        Assertions.assertEquals(new InGameTime(8), this.state.getRemainingTime());
        Mockito.verify(this.stockyard, Mockito.never()).setState(Mockito.any());

        this.state.setRemainingTime(new InGameTime(1));
        Assertions.assertEquals(new InGameTime(1), this.state.getRemainingTime());
        Mockito.verify(this.stockyard, Mockito.never()).setState(Mockito.any());
    }

    @Test
    void setRemainingTimeZero() {
        this.state.setRemainingTime(new InGameTime(0));
        Mockito.verify(this.stockyard, Mockito.times(1)).setState(
                Mockito.argThat((ReadyToCollect newState) -> newState.getCollectAmount() == this.state.getCollectAmount()));
    }

    @Test
    void setRemainingTimeNegative() {
        this.state.setRemainingTime(new InGameTime(-5));
        Mockito.verify(this.stockyard, Mockito.times(1)).setState(
                Mockito.argThat((ReadyToCollect newState) -> newState.getCollectAmount() == this.state.getCollectAmount()));
    }

    @Test
    void changeCollectAmount() {
        this.state.changeCollectAmount(0.1);
        Assertions.assertEquals(10, this.state.getCollectAmount());
        this.state.changeCollectAmount(0.7);
        Assertions.assertEquals(10, this.state.getCollectAmount());
        this.state.changeCollectAmount(0.3);
        Assertions.assertEquals(11, this.state.getCollectAmount());
        this.state.changeCollectAmount(2.5);
        Assertions.assertEquals(13, this.state.getCollectAmount());
        this.state.changeCollectAmount(-3.9);
        Assertions.assertEquals(9, this.state.getCollectAmount());
        Mockito.verify(this.stockyard, Mockito.never()).setState(Mockito.any());
    }

    @Test
    void harvestAmountZero() {
        this.state.changeCollectAmount(-10);
        Mockito.verify(this.stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }

    @Test
    void harvestAmountNegative() {
        this.state.changeCollectAmount(-20);
        Mockito.verify(this.stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }
}
