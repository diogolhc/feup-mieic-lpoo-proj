package model.farm.building.stockyard.state;

import model.InGameTime;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
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

        AnimalProduct animalProduct = Mockito.mock(AnimalProduct.class);
        Mockito.when(animalProduct.getProductionTime()).thenReturn(new InGameTime(10));

        Mockito.when(livestock.getProducedItem()).thenReturn(animalProduct);

        Mockito.when(this.stockyard.getLivestockType()).thenReturn(livestock);
        this.state = new Producing(this.stockyard);
    }

    @Test
    void setRemainingTimeZero() {
        this.state.setRemainingTime(new InGameTime(0));

        ArgumentCaptor<StockyardState> newState = ArgumentCaptor.forClass(StockyardState.class);
        Mockito.verify(this.stockyard, times(1)).setState(newState.capture());
        Assertions.assertTrue(newState.getValue() instanceof ReadyToCollect);
    }

    @Test
    void changeCollectAmount() {
        this.state.changeCollectAmount(5);
        Assertions.assertEquals(15, this.state.getCollectAmount());
    }

    @Test
    void changeCollectAmountBeyondZero() {
        this.state.changeCollectAmount(-10);

        ArgumentCaptor<StockyardState> newState = ArgumentCaptor.forClass(StockyardState.class);
        Mockito.verify(this.stockyard, times(1)).setState(newState.capture());
        Assertions.assertTrue(newState.getValue() instanceof NotProducing);
    }

}
