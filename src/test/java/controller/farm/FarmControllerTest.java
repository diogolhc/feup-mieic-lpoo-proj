package controller.farm;

import controller.GameController;
import gui.GUI;
import model.InGameTime;
import model.farm.Farm;
import model.farm.building.crop_field.CropField;
import model.farm.data.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import viewer.GameViewer;

public class FarmControllerTest {
    private Farm farm;
    private CropField cropField;
    private FarmController controller;
    private GameController gameController;

    @BeforeEach
    public void setUp() {
        this.farm = new Farm(20, 20);
        this.farm.setTime(new InGameTime(0, 0, 0));
        this.farm.setCurrentWeather(new Weather("SUNNY"));
        this.cropField = Mockito.mock(CropField.class);
        Mockito.when(this.cropField.getRemainingTime()).thenReturn(new InGameTime(0));
        this.farm.getBuildings().addCropField(this.cropField);
        this.gameController = Mockito.mock(GameController.class);
        this.controller = new FarmController(this.farm, this.gameController, 1) {
            @Override
            public void reactKeyboard(GUI.KEYBOARD_ACTION action) {}

            @Override
            public GameViewer getViewer() {
                return null;
            }
        };
    }

    @Test
    public void reactTimePassed() {
        this.controller.reactTimePassed(67000);
        Assertions.assertEquals(new InGameTime(0, 1, 7), this.farm.getTime());
        this.controller.reactTimePassed(1000);
        Assertions.assertEquals(new InGameTime(0, 1, 8), this.farm.getTime());
        this.controller.reactTimePassed(1700);
        Assertions.assertEquals(new InGameTime(0, 1, 9), this.farm.getTime());
        this.controller.reactTimePassed(400);
        Assertions.assertEquals(new InGameTime(0, 1, 10), this.farm.getTime());
    }

    @Test
    public void reactTimePassedNotifyCropField() {
        this.controller.reactTimePassed(67000);
        Mockito.verify(this.cropField, Mockito.times(1)).setRemainingTime(Mockito.any(InGameTime.class));
    }
}
