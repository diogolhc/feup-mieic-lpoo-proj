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
import model.farm.building.BuildingSet;
import model.farm.building.crop_field.CropField;
import model.farm.data.Weather;
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
        this.farm = new Farm(6, 8, Mockito.mock(BuildingSet.class));
        this.farm.setTime(new InGameTime(0, 0, 0));
        this.farm.setCurrentWeather(new Weather("SUNNY"));
        this.farm.setWallet(Mockito.mock(Wallet.class));
        this.newBuilding = Mockito.mock(CropField.class);
        Mockito.when(this.newBuilding.getBuildPrice()).thenReturn(Mockito.mock(Currency.class));
        Mockito.when(this.newBuilding.getTopLeftPosition()).thenReturn(new Position(0, 0));
        Mockito.when(this.newBuilding.getOccupiedRegion()).thenReturn(new RectangleRegion(new Position(0, 0), 1, 1));
        this.gameController = Mockito.mock(GameController.class);
        this.controller = new FarmNewBuildingController(this.farm, this.gameController, 1, this.newBuilding);
    }

    @Test
    public void back() {
        Mockito.verifyNoInteractions(this.gameController);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verify(this.gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(FarmWithFarmerController.class));
    }

    @Test
    public void newBuildingReactsToKeyboard() {
        Mockito.verifyNoInteractions(this.newBuilding);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Mockito.verify(this.newBuilding, Mockito.times(1)).getTopLeftPosition();
    }

    @Test
    public void reactInteractionOccupied() {
        Mockito.when(this.farm.getBuildings().isOccupied(Mockito.any())).thenReturn(true);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);

        Mockito.verifyNoInteractions(this.gameController);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        Mockito.verify(this.gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(PopupMenuController.class));
        Mockito.verifyNoInteractions(this.farm.getWallet());
    }

    @Test
    public void reactInteractionNotOccupied() {
        Mockito.when(this.farm.getBuildings().isOccupied(Mockito.any())).thenReturn(false);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);

        Mockito.verifyNoInteractions(this.gameController);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        Mockito.verify(this.gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(FarmWithFarmerController.class));
        Mockito.verify(this.farm.getWallet(), Mockito.times(1)).spend(this.newBuilding.getBuildPrice());
        Mockito.verify(this.farm.getBuildings(), Mockito.times(1)).addCropField((CropField) this.newBuilding);
    }
}
