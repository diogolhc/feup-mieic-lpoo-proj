package controller.farm;

import controller.GameController;
import controller.menu.PopupMenuController;
import gui.GUI;
import model.InGameTime;
import model.Position;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Wallet;
import model.farm.building.Buildable;
import model.farm.building.Building;
import model.farm.building.BuildingSet;
import model.farm.building.Edifice;
import model.farm.building.crop_field.CropField;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.Weather;
import model.farm.data.item.Crop;
import model.farm.entity.Entity;
import model.region.RectangleRegion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FarmNewBuildingControllerTest {
    private Farm farm;
    private FarmNewBuildingController controller;
    private GameController gameController;
    private Buildable newBuilding;

    @BeforeEach
    public void setUp() {
        farm = new Farm(6, 8, Mockito.mock(BuildingSet.class));
        farm.setTime(new InGameTime(0, 0, 0));
        farm.setCurrentWeather(new Weather("SUNNY"));
        farm.setWallet(Mockito.mock(Wallet.class));
        newBuilding = Mockito.mock(CropField.class);
        Mockito.when(newBuilding.getBuildPrice()).thenReturn(Mockito.mock(Currency.class));
        Mockito.when(newBuilding.getTopLeftPosition()).thenReturn(new Position(0, 0));
        Mockito.when(newBuilding.getOccupiedRegion()).thenReturn(new RectangleRegion(new Position(0, 0), 1, 1));
        gameController = Mockito.mock(GameController.class);
        controller = new FarmNewBuildingController(farm, gameController, 1, newBuilding);
    }

    @Test
    public void back() {
        Mockito.verifyNoInteractions(gameController);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verify(gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(FarmWithFarmerController.class));
    }

    @Test
    public void newBuildingReactsToKeyboard() {
        Mockito.verifyNoInteractions(newBuilding);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Mockito.verify(newBuilding, Mockito.times(1)).getTopLeftPosition();
    }

    @Test
    public void reactInteractionOccupied() {
        Mockito.when(farm.getBuildings().isOccupied(Mockito.any())).thenReturn(true);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);

        Mockito.verifyNoInteractions(gameController);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        Mockito.verify(gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(PopupMenuController.class));
        Mockito.verifyNoInteractions(farm.getWallet());
    }

    @Test
    public void reactInteractionNotOccupied() {
        Mockito.when(farm.getBuildings().isOccupied(Mockito.any())).thenReturn(false);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);

        Mockito.verifyNoInteractions(gameController);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        Mockito.verify(gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(FarmWithFarmerController.class));
        Mockito.verify(farm.getWallet(), Mockito.times(1)).spend(newBuilding.getBuildPrice());
        Mockito.verify(farm.getBuildings(), Mockito.times(1)).addCropField((CropField) newBuilding);
    }
}
