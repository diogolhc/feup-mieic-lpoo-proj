package model.farm.building.stockyard.state;

import model.farm.building.stockyard.Stockyard;
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
    void changeCollectAmountNegative() {
        state.changeCollectAmount(-5);
        Assertions.assertEquals(5, state.getCollectAmount());
    }

    @Test
    void changeCollectAmountPositive() {
        state.changeCollectAmount(5);
        Assertions.assertEquals(5, state.getCollectAmount());
    }

    @Test
    void changeCollectAmountZero() {
        state.changeCollectAmount(0);
        Assertions.assertEquals(10, state.getCollectAmount());
    }

    @Test
    void changeCollectAmountSeveral() {
        state.changeCollectAmount(-2);
        Assertions.assertEquals(8, state.getCollectAmount());
        state.changeCollectAmount(-3);
        Assertions.assertEquals(5, state.getCollectAmount());
    }

    @Test
    void changeCollectAmountBeyondZero() {
        state.changeCollectAmount(-10);

        ArgumentCaptor<StockyardState> newState = ArgumentCaptor.forClass(StockyardState.class);
        Mockito.verify(this.stockyard, times(1)).setState(newState.capture());
        Assertions.assertTrue(newState.getValue() instanceof NotProducing);
    }

}
