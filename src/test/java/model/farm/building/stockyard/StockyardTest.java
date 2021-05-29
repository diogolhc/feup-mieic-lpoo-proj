package model.farm.building.stockyard;


import model.Position;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.data.Livestock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StockyardTest {
    private Stockyard stockyard;

    @BeforeEach
    void setUp() {
        Position position = new Position(0,0);
        Livestock livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getStockyardHeight()).thenReturn(4);
        Mockito.when(livestock.getStockyardWidth()).thenReturn(4);
        stockyard = new Stockyard(position, livestock);
    }

    @Test
    void getDefaultState() {
        Assertions.assertTrue(stockyard.getState() instanceof NotProducing);
    }

}
