package controller.farm;

import controller.GameController;
import controller.menu.PauseMenuController;
import controller.menu.PopupMenuController;
import gui.GUI;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.Building;
import model.farm.building.BuildingSet;
import model.farm.building.Edifice;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.Weather;
import model.farm.building.crop_field.CropField;
import model.farm.entity.Entity;
import model.region.RectangleRegion;
import model.region.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class FarmWithFarmerControllerTest {
    private Farm farm;
    private FarmWithFarmerController controller;
    private GameController gameController;

    @BeforeEach
    public void setUp() {
        BuildingSet buildings = new BuildingSet(
                Mockito.mock(Edifice.class), Mockito.mock(Edifice.class), Mockito.mock(Edifice.class));
        farm = new Farm(6, 8, buildings);
        farm.setTime(new InGameTime(0, 0, 0));
        farm.setCurrentWeather(new Weather("SUNNY"));
        farm.setFarmer(Mockito.mock(Entity.class));
        Mockito.when(farm.getFarmer().getPosition()).thenReturn(new Position(0, 0));
        gameController = Mockito.mock(GameController.class);
        controller = new FarmWithFarmerController(farm, gameController, 1);
    }

    @Test
    public void pause() {
        Mockito.verifyNoInteractions(gameController);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verify(gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(PauseMenuController.class));
    }

    @Test
    public void farmerReactsToKeyboard() {
        Entity farmer = farm.getFarmer();
        Mockito.verifyNoInteractions(farmer);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Mockito.verify(farmer, Mockito.times(1)).getPosition();
    }

    @Test
    public void reactInteraction() {
        farm.getBuildings().addCropField(Mockito.mock(CropField.class));
        farm.getBuildings().addStockyard(Mockito.mock(Stockyard.class));

        for (Building building: farm.getBuildings().getAllBuildings()) {
            Mockito.when(building.getInteractiveRegion()).thenReturn(position -> false);
        }

        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        for (Building building: farm.getBuildings().getAllBuildings()) {
            Mockito.verifyNoInteractions(building);
        }
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        for (Building building: farm.getBuildings().getAllBuildings()) {
            Mockito.verify(building, Mockito.times(1)).getInteractiveRegion();
        }
    }
}
