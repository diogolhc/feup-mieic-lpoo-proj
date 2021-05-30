package controller.farm.element.entity.keyboard_reactor;

import controller.farm.element.entity.keyboard_reactor.FarmerController;
import gui.GUI;
import model.Position;
import model.farm.Farm;
import model.farm.entity.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FarmerControllerTest {
    private Farm farm;
    private Entity farmer;
    private FarmerController controller;

    @BeforeEach
    public void setUp() {
        this.farmer = new Entity(new Position(10, 10));
        this.farm = Mockito.mock(Farm.class);
        Mockito.when(this.farm.getFarmer()).thenReturn(this.farmer);
        Mockito.when(this.farm.isTraversable(Mockito.any())).thenReturn(true);

        this.controller = new FarmerController(this.farm);
    }

    @Test
    public void moveUp() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 9), this.farmer.getPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 8), this.farmer.getPosition());
    }

    @Test
    public void moveDown() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 11), this.farmer.getPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 12), this.farmer.getPosition());
    }

    @Test
    public void moveLeft() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), this.farmer.getPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(8, 10), this.farmer.getPosition());
    }

    @Test
    public void moveRight() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(11, 10), this.farmer.getPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(12, 10), this.farmer.getPosition());
    }

    @Test
    public void noMove() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.QUIT);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verifyNoInteractions(this.farm);
        Assertions.assertEquals(new Position(10, 10), this.farmer.getPosition());
    }

    @Test
    public void cantMove() {
        Mockito.when(this.farm.isTraversable(Mockito.any())).thenReturn(false);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 10), this.farmer.getPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), this.farmer.getPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), this.farmer.getPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(10, 10), this.farmer.getPosition());
    }

    @Test
    public void canOnlyMoveLeft() {
        Mockito.when(this.farm.isTraversable(Mockito.any())).thenReturn(false);
        Mockito.when(this.farm.isTraversable(new Position(9, 10))).thenReturn(true);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 10), this.farmer.getPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), this.farmer.getPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), this.farmer.getPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), this.farmer.getPosition());
    }
}
