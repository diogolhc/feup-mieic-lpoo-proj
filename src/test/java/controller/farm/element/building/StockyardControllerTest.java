package controller.farm.element.building;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.PopupMenuController;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.Producing;
import model.farm.building.stockyard.state.ReadyToCollect;
import model.farm.building.stockyard.state.StockyardState;
import model.farm.data.Livestock;
import model.farm.data.Weather;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class StockyardControllerTest {
    private Farm farm;
    private GameController gameController;
    private StockyardController stockyardController;
    private Stockyard stockyard;
    private Livestock livestock;

    @BeforeEach
    public void setUp() {
        livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getFoodCrop()).thenReturn(new Crop("WHEAT"));
        AnimalProduct product = Mockito.mock(AnimalProduct.class);
        Mockito.when(livestock.getProducedItem()).thenReturn(product);
        Mockito.when(product.getProductionTime()).thenReturn(new InGameTime(0, 1, 0));
        Mockito.when(product.getBaseProducedAmount()).thenReturn(10);

        stockyard = new Stockyard(new Position(10, 10), livestock);
        stockyard.setState(new NotProducing());

        gameController = Mockito.mock(GameController.class);
        farm = new Farm(10, 10);
        farm.setCurrentWeather(new Weather("WEATHER", 1.0));
        stockyardController = new StockyardController(gameController, farm);
    }

    @Test
    public void reactInteractionInZoneNotProducing() {
        stockyardController.reactInteraction(stockyard, new Position(10, 13));
        ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
        Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
        Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
        PopupMenuController newState = (PopupMenuController) captor.getValue();
        Assertions.assertEquals("STOCKYARD", newState.getMenu().getTitle());
    }

    @Test
    public void reactInteractionInZoneProducing() {
        stockyard.setState(new Producing(stockyard));
        stockyardController.reactInteraction(stockyard, new Position(10, 13));
        ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
        Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
        Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
        PopupMenuController newState = (PopupMenuController) captor.getValue();
        Assertions.assertEquals("PRODUCING", newState.getMenu().getTitle());
    }

    @Test
    public void reactInteractionInZoneReadyToCollect() {
        stockyard.setState(new ReadyToCollect(stockyard, 20));
        stockyardController.reactInteraction(stockyard, new Position(10, 13));
        ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
        Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
        Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
        PopupMenuController newState = (PopupMenuController) captor.getValue();
        Assertions.assertEquals("READY TO COLLECT", newState.getMenu().getTitle());
    }

    @Test
    public void reactInteractionOutOfZone() {
        stockyardController.reactInteraction(stockyard, new Position(10, 12));
        Mockito.verify(gameController, Mockito.never()).setGameControllerState(Mockito.any());
    }

    @Test
    public void reactInteractionInZoneUnknownState() {
        stockyard.setState(Mockito.mock(StockyardState.class));
        Assertions.assertThrows(RuntimeException.class,
                () -> stockyardController.reactInteraction(stockyard, new Position(10, 13)));
    }

    @Test
    public void reactTimePassed() {
        stockyard.setState(new Producing(stockyard));
        stockyardController.reactTimePassed(stockyard, new InGameTime(0, 0, 20));
        stockyardController.reactTimePassed(stockyard, new InGameTime(0, 0, 30));
        Assertions.assertEquals(new InGameTime(0, 0, 10), stockyard.getRemainingTime());
        Assertions.assertTrue(stockyard.getState() instanceof Producing);
        stockyardController.reactTimePassed(stockyard, new InGameTime(0, 0, 30));
        Assertions.assertEquals(new InGameTime(0, 0, 0), stockyard.getRemainingTime());
        Assertions.assertTrue(stockyard.getState() instanceof ReadyToCollect);
    }

    @Test
    public void reactTimePassedNotProducing() {
        Assertions.assertEquals(new InGameTime(0, 0, 0), stockyard.getRemainingTime());
        Assertions.assertTrue(stockyard.getState() instanceof NotProducing);
        stockyardController.reactTimePassed(stockyard, new InGameTime(10, 0, 0));
        Assertions.assertEquals(new InGameTime(0, 0, 0), stockyard.getRemainingTime());
        Assertions.assertTrue(stockyard.getState() instanceof NotProducing);
    }
}
