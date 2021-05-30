package controller.farm.element.entity.keyboard_reactor;

import controller.farm.element.entity.keyboard_reactor.FarmerController;
import gui.GUI;
import model.Position;
import model.farm.Farm;
import model.farm.entity.Entity;
import model.farm.building.crop_field.CropField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class FarmerControllerTest {
    private Farm farm;
    private Entity farmer;
    private FarmerController controller;

    @BeforeEach
    public void setUp() {
        farmer = new Entity(new Position(10, 10));
        farm = Mockito.mock(Farm.class);
        Mockito.when(farm.getFarmer()).thenReturn(farmer);
        Mockito.when(farm.isTraversable(Mockito.any())).thenReturn(true);

        controller = new FarmerController(farm);
    }

    @Test
    public void moveUp() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 9), farmer.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 8), farmer.getPosition());
    }

    @Test
    public void moveDown() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 11), farmer.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 12), farmer.getPosition());
    }

    @Test
    public void moveLeft() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), farmer.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(8, 10), farmer.getPosition());
    }

    @Test
    public void moveRight() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(11, 10), farmer.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(12, 10), farmer.getPosition());
    }

    @Test
    public void noMove() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.QUIT);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verifyNoInteractions(farm);
        Assertions.assertEquals(new Position(10, 10), farmer.getPosition());
    }

    @Test
    public void cantMove() {
        Mockito.when(farm.isTraversable(Mockito.any())).thenReturn(false);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 10), farmer.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), farmer.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), farmer.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(10, 10), farmer.getPosition());
    }

    @Test
    public void canOnlyMoveLeft() {
        Mockito.when(farm.isTraversable(Mockito.any())).thenReturn(false);
        Mockito.when(farm.isTraversable(new Position(9, 10))).thenReturn(true);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 10), farmer.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), farmer.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), farmer.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), farmer.getPosition());
    }
}
