package controller.command.farm.stockyard;

import controller.command.Command;
import model.Position;
import model.farm.Inventory;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.Producing;
import model.farm.building.stockyard.state.ReadyToCollect;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StopProducingCommandTest {
    private Stockyard stockyard;
    private NotProducing stateNotProducing;
    private Producing stateProducing;
    private ReadyToCollect stateReadyToCollect;
    private Command command;
    private Livestock livestock;

    @BeforeEach
    public void setUp() {
        stateReadyToCollect = Mockito.mock(ReadyToCollect.class);
        stateNotProducing = Mockito.mock(NotProducing.class);
        stateProducing = Mockito.mock(Producing.class);
        livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(livestock.getStockyardHeight()).thenReturn(4);


        stockyard = new Stockyard(new Position(0, 0), livestock);

        Mockito.when(stateProducing.getCollectAmount()).thenReturn(stockyard.getAnimals().getSize() * 10);

        command = new StopProducingStockyardCommand(stockyard);
    }

    @Test
    public void executeReady() {
        stockyard.setState(stateReadyToCollect);

        command.execute();

        Assertions.assertTrue(stockyard.getState() instanceof NotProducing);
    }

    @Test
    public void executeNotProducing() {
        stockyard.setState(stateNotProducing);

        command.execute();

        Assertions.assertTrue(stockyard.getState() instanceof NotProducing);
    }

    @Test
    public void executeProducing() {
        stockyard.setState(stateProducing);

        command.execute();

        Assertions.assertTrue(stockyard.getState() instanceof NotProducing);
    }
}
