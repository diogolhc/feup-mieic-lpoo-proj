package controller.farm;

import controller.GameController;
import controller.menu.PauseMenuController;
import gui.GUI;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.Building;
import model.farm.building.BuildingSet;
import model.farm.building.Edifice;
import model.farm.building.crop_field.CropField;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.Weather;
import model.farm.entity.Entity;
import model.region.RectangleRegion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FarmDemolishControllerTest {
    private Farm farm;
    private FarmDemolishController controller;
    private GameController gameController;
    private Entity marker;

    @BeforeEach
    public void setUp() {
        BuildingSet buildings = new BuildingSet(
                Mockito.mock(Edifice.class), Mockito.mock(Edifice.class), Mockito.mock(Edifice.class));
        farm = new Farm(6, 8, buildings);
        farm.setTime(new InGameTime(0, 0, 0));
        farm.setCurrentWeather(new Weather("SUNNY"));
        marker = Mockito.mock(Entity.class);
        Mockito.when(marker.getPosition()).thenReturn(new Position(0, 0));
        gameController = Mockito.mock(GameController.class);
        controller = new FarmDemolishController(farm, gameController, 1, marker);
    }

    @Test
    public void back() {
        Mockito.verifyNoInteractions(gameController);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verify(gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(FarmWithFarmerController.class));
    }

    @Test
    public void markerReactsToKeyboard() {
        Mockito.verifyNoInteractions(marker);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Mockito.verify(marker, Mockito.times(1)).getPosition();
    }

    @Test
    public void reactDemolish() {
        farm.getBuildings().addCropField(Mockito.mock(CropField.class));
        farm.getBuildings().addStockyard(Mockito.mock(Stockyard.class));

        for (Building building: farm.getBuildings().getAllBuildings()) {
            Mockito.when(building.getOccupiedRegion()).thenReturn(new RectangleRegion(new Position(0, 0), 1, 1));
        }

        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        for (Building building: farm.getBuildings().getAllBuildings()) {
            Mockito.verifyNoInteractions(building);
        }
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        for (Building building: farm.getBuildings().getAllBuildings()) {
            Mockito.verify(building, Mockito.times(1)).getOccupiedRegion();
        }
    }
}
