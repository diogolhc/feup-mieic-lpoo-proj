package controller.farm;

import controller.GameController;
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
        this.farm = new Farm(6, 8, buildings);
        this.farm.setTime(new InGameTime(0, 0, 0));
        this.farm.setCurrentWeather(new Weather("SUNNY"));
        this.marker = Mockito.mock(Entity.class);
        Mockito.when(this.marker.getPosition()).thenReturn(new Position(0, 0));
        this.gameController = Mockito.mock(GameController.class);
        this.controller = new FarmDemolishController(this.farm, this.gameController, 1, this.marker);
    }

    @Test
    public void back() {
        Mockito.verifyNoInteractions(this.gameController);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verify(this.gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(FarmWithFarmerController.class));
    }

    @Test
    public void markerReactsToKeyboard() {
        Mockito.verifyNoInteractions(this.marker);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Mockito.verify(this.marker, Mockito.times(1)).getPosition();
    }

    @Test
    public void reactDemolish() {
        this.farm.getBuildings().addCropField(Mockito.mock(CropField.class));
        this.farm.getBuildings().addStockyard(Mockito.mock(Stockyard.class));

        for (Building building: this.farm.getBuildings().getAllBuildings()) {
            Mockito.when(building.getOccupiedRegion()).thenReturn(new RectangleRegion(new Position(0, 0), 1, 1));
        }

        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        for (Building building: this.farm.getBuildings().getAllBuildings()) {
            Mockito.verifyNoInteractions(building);
        }
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        for (Building building: this.farm.getBuildings().getAllBuildings()) {
            Mockito.verify(building, Mockito.times(1)).getOccupiedRegion();
        }
    }
}
