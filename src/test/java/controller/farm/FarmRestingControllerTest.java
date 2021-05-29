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
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FarmRestingControllerTest {
    private Farm farm;
    private FarmRestingController controller;
    private GameController gameController;

    @BeforeEach
    @BeforeProperty
    public void setUp() {
        BuildingSet buildings = new BuildingSet(
                Mockito.mock(Edifice.class), Mockito.mock(Edifice.class), Mockito.mock(Edifice.class));
        farm = new Farm(6, 8, buildings);
        farm.setTime(new InGameTime(0, 0, 0));
        farm.setCurrentWeather(new Weather("SUNNY"));
        farm.setFarmer(Mockito.mock(Entity.class));
        Mockito.when(farm.getFarmer().getPosition()).thenReturn(new Position(0, 0));
        gameController = Mockito.mock(GameController.class);
        controller = new FarmRestingController(farm, gameController, 1);
    }

    @Test
    public void noAction() {
        Mockito.verifyNoInteractions(gameController);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        Mockito.verifyNoInteractions(gameController);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        Mockito.verifyNoInteractions(gameController);
    }

    @Provide
    Arbitrary<GUI.KEYBOARD_ACTION> moveActions() {
        return Arbitraries.of(
                GUI.KEYBOARD_ACTION.BACK,
                GUI.KEYBOARD_ACTION.MOVE_RIGHT,
                GUI.KEYBOARD_ACTION.MOVE_UP,
                GUI.KEYBOARD_ACTION.MOVE_DOWN,
                GUI.KEYBOARD_ACTION.MOVE_LEFT,
                GUI.KEYBOARD_ACTION.INTERACT);
    }

    @Property
    public void reactKeyboard(@ForAll @From("moveActions") GUI.KEYBOARD_ACTION action) {
        controller.getTimeConverter().setRateMultiplier(5);
        Assertions.assertEquals(5, controller.getTimeConverter().getRate());
        Mockito.reset(gameController);
        controller.reactKeyboard(action);
        Mockito.verify(gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(FarmWithFarmerController.class));
        Assertions.assertEquals(1, controller.getTimeConverter().getRate());
    }
}
