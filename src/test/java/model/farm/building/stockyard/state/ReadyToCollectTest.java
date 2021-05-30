package model.farm.building.stockyard.state;

import model.InGameTime;
import model.farm.building.stockyard.Stockyard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ReadyToCollectTest {
    Stockyard stockyard;
    ReadyToCollect state;

    @BeforeEach
    void setUp() {
        this.stockyard = Mockito.mock(Stockyard.class);
        this.state = new ReadyToCollect(this.stockyard, 10);
    }

    @Test
    void setRemainingTimeIsNoOp() {
        Assertions.assertEquals(new InGameTime(0), this.state.getRemainingTime());
        this.state.setRemainingTime(new InGameTime(20));
        Assertions.assertEquals(new InGameTime(0), this.state.getRemainingTime());
        this.state.setRemainingTime(new InGameTime(0));
        Assertions.assertEquals(new InGameTime(0), this.state.getRemainingTime());
    }

    @Test
    void changeCollectAmount() {
        this.state.changeCollectAmount(-0.1);
        Assertions.assertEquals(9, this.state.getCollectAmount());
        this.state.changeCollectAmount(-0.7);
        Assertions.assertEquals(9, this.state.getCollectAmount());
        this.state.changeCollectAmount(-0.2);
        Assertions.assertEquals(9, this.state.getCollectAmount());
        this.state.changeCollectAmount(-2.5);
        Assertions.assertEquals(6, this.state.getCollectAmount());
        this.state.changeCollectAmount(-3.6);
        Assertions.assertEquals(2, this.state.getCollectAmount());
    }

    @Test
    void changeCollectAmountEffectsAreAlwaysNegative() {
        this.state.changeCollectAmount(0.1);
        Assertions.assertEquals(9, this.state.getCollectAmount());
        this.state.changeCollectAmount(-0.7);
        Assertions.assertEquals(9, this.state.getCollectAmount());
        this.state.changeCollectAmount(0.2);
        Assertions.assertEquals(9, this.state.getCollectAmount());
        this.state.changeCollectAmount(-2.5);
        Assertions.assertEquals(6, this.state.getCollectAmount());
        this.state.changeCollectAmount(3.6);
        Assertions.assertEquals(2, this.state.getCollectAmount());
    }

    @Test
    void collectAmountZero() {
        this.state.changeCollectAmount(-10);
        Mockito.verify(this.stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }

    @Test
    void collectAmountNegative() {
        this.state.changeCollectAmount(-20);
        Mockito.verify(this.stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }

    @Test
    void collectAmountZeroEffectsAreAlwaysNegative() {
        this.state.changeCollectAmount(10);
        Mockito.verify(this.stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }

    @Test
    void collectAmountNegativeEffectsAreAlwaysNegative() {
        this.state.changeCollectAmount(20);
        Mockito.verify(this.stockyard, Mockito.times(1))
                .setState(Mockito.any(NotProducing.class));
    }
}
