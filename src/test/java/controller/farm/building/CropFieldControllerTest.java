package controller.farm.building;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.PopupMenuController;
import model.InGameTime;
import model.Position;
import model.farm.building.CropField;
import model.farm.item.Crop;
import model.farm.building.crop_field_state.CropFieldState;
import model.farm.building.crop_field_state.NotPlanted;
import model.farm.building.crop_field_state.Planted;
import model.farm.building.crop_field_state.ReadyToHarvest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class CropFieldControllerTest {
    private CropField cropField;
    private CropFieldController cropFieldController;
    private GameController gameController;

    @BeforeEach
    public void setUp() {
        cropField = new CropField(new Position(10, 10));
        cropField.setState(new NotPlanted());
        gameController = Mockito.mock(GameController.class);

        cropFieldController = new CropFieldController(gameController);
    }

    @Test
    public void reactInteractionInZoneNotPlanted() {
        cropFieldController.reactInteraction(cropField, new Position(10, 11));
        ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
        Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
        Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
        PopupMenuController newState = (PopupMenuController) captor.getValue();
        Assertions.assertEquals("PLANT", newState.getMenu().getTitle());
    }

    @Test
    public void reactInteractionInZonePlanted() {
        cropField.setState(new Planted(cropField, Mockito.mock(Crop.class)));
        cropFieldController.reactInteraction(cropField, new Position(12, 10));
        ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
        Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
        Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
        PopupMenuController newState = (PopupMenuController) captor.getValue();
        Assertions.assertEquals("GROWING", newState.getMenu().getTitle());
    }

    @Test
    public void reactInteractionInZoneReadyToHarvest() {
        cropField.setState(new ReadyToHarvest(Mockito.mock(Crop.class)));
        cropFieldController.reactInteraction(cropField, new Position(13, 12));
        ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
        Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
        Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
        PopupMenuController newState = (PopupMenuController) captor.getValue();
        Assertions.assertEquals("READY TO HARVEST", newState.getMenu().getTitle());
    }

    @Test
    public void reactInteractionOutOfZone() {
        cropFieldController.reactInteraction(cropField, new Position(9, 10));
        Mockito.verify(gameController, Mockito.never()).setGameControllerState(Mockito.any());
    }

    @Test
    public void reactInteractionInZoneUnknownState() {
        cropField.setState(Mockito.mock(CropFieldState.class));
        Assertions.assertThrows(RuntimeException.class,
                () -> cropFieldController.reactInteraction(cropField, new Position(10, 11)));
    }

    @Test
    public void reactTimePassed() {
        Crop crop = Mockito.mock(Crop.class);
        Mockito.when(crop.getGrowTime()).thenReturn(new InGameTime(0, 2, 0));
        cropField.setState(new Planted(cropField, crop));
        cropFieldController.reactTimePassed(cropField, new InGameTime(0, 1, 30));
        Assertions.assertEquals(new InGameTime(0, 0, 30), cropField.getRemainingTime());
        Assertions.assertTrue(cropField.getState() instanceof Planted);
        cropFieldController.reactTimePassed(cropField, new InGameTime(0, 0, 40));
        Assertions.assertTrue(cropField.getState() instanceof ReadyToHarvest);
    }

    @Test
    public void reactTimePassedNotPlanted() {
        cropFieldController.reactTimePassed(cropField, new InGameTime(0, 1, 30));
        Assertions.assertEquals(new InGameTime(0, 0, 0), cropField.getRemainingTime());
        Assertions.assertTrue(cropField.getState() instanceof NotPlanted);
        cropFieldController.reactTimePassed(cropField, new InGameTime(10, 0, 0));
        Assertions.assertEquals(new InGameTime(0, 0, 0), cropField.getRemainingTime());
        Assertions.assertTrue(cropField.getState() instanceof NotPlanted);
    }
}
