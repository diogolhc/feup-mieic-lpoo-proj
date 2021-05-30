package controller.command.farm.stockyard;

import controller.command.Command;
import model.Position;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.Producing;
import model.farm.building.stockyard.state.ReadyToCollect;
import model.farm.data.Livestock;
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
        this.stateReadyToCollect = Mockito.mock(ReadyToCollect.class);
        this.stateNotProducing = Mockito.mock(NotProducing.class);
        this.stateProducing = Mockito.mock(Producing.class);
        this.livestock = Mockito.mock(Livestock.class);
        Mockito.when(this.livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(this.livestock.getStockyardHeight()).thenReturn(4);


        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);

        Mockito.when(this.stateProducing.getCollectAmount()).thenReturn(this.stockyard.getAnimals().getSize() * 10);

        this.command = new StopProducingStockyardCommand(this.stockyard);
    }

    @Test
    public void executeReady() {
        this.stockyard.setState(this.stateReadyToCollect);

        this.command.execute();

        Assertions.assertTrue(this.stockyard.getState() instanceof NotProducing);
    }

    @Test
    public void executeNotProducing() {
        this.stockyard.setState(this.stateNotProducing);

        this.command.execute();

        Assertions.assertTrue(this.stockyard.getState() instanceof NotProducing);
    }

    @Test
    public void executeProducing() {
        this.stockyard.setState(this.stateProducing);

        this.command.execute();

        Assertions.assertTrue(this.stockyard.getState() instanceof NotProducing);
    }
}
