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
        farm = new Farm(20, 20);
        farm.setTime(new InGameTime(0, 0, 0));
        farm.setCurrentWeather(new Weather("SUNNY"));
        cropField = Mockito.mock(CropField.class);
        Mockito.when(cropField.getRemainingTime()).thenReturn(new InGameTime(0));
        farm.getBuildings().addCropField(cropField);
        gameController = Mockito.mock(GameController.class);
        controller = new FarmController(farm, gameController, 1) {
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
        controller.reactTimePassed(67000);
        Assertions.assertEquals(new InGameTime(0, 1, 7), farm.getTime());
        controller.reactTimePassed(1000);
        Assertions.assertEquals(new InGameTime(0, 1, 8), farm.getTime());
        controller.reactTimePassed(1700);
        Assertions.assertEquals(new InGameTime(0, 1, 9), farm.getTime());
        controller.reactTimePassed(400);
        Assertions.assertEquals(new InGameTime(0, 1, 10), farm.getTime());
    }

    @Test
    public void reactTimePassedNotifyCropField() {
        controller.reactTimePassed(67000);
        Mockito.verify(cropField, Mockito.times(1)).setRemainingTime(Mockito.any(InGameTime.class));
    }
}
