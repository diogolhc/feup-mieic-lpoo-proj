package controller.farm.element.entity.keyboard_reactor;

import gui.GUI;
import model.Position;
import model.farm.Farm;
import model.farm.entity.Entity;
import model.region.RectangleRegion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class DemolishMarkerControllerTest {
    private Farm farm;
    private Entity demolishMarker;
    private DemolishMarkerController controller;

    @BeforeEach
    public void setUp() {
        demolishMarker = new Entity(new Position(10, 10));
        farm = Mockito.mock(Farm.class);
        Mockito.when(farm.getFarmer()).thenReturn(demolishMarker);
        RectangleRegion region = Mockito.mock(RectangleRegion.class);
        Mockito.when(farm.getInsideRegion()).thenReturn(region);
        Mockito.when(region.contains(Mockito.any(Position.class))).thenReturn(true);

        controller = new DemolishMarkerController(farm, demolishMarker);
    }

    @Test
    public void moveUp() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 9), demolishMarker.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 8), demolishMarker.getPosition());
    }

    @Test
    public void moveDown() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 11), demolishMarker.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 12), demolishMarker.getPosition());
    }

    @Test
    public void moveLeft() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), demolishMarker.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(8, 10), demolishMarker.getPosition());
    }

    @Test
    public void moveRight() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(11, 10), demolishMarker.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(12, 10), demolishMarker.getPosition());
    }

    @Test
    public void noMove() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.QUIT);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verifyNoInteractions(farm);
        Assertions.assertEquals(new Position(10, 10), demolishMarker.getPosition());
    }

    @Test
    public void cantMove() {
        Mockito.when(farm.getInsideRegion().contains(Mockito.any(Position.class))).thenReturn(false);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 10), demolishMarker.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), demolishMarker.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), demolishMarker.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(10, 10), demolishMarker.getPosition());
    }

    @Test
    public void canOnlyMoveLeft() {
        Mockito.when(farm.getInsideRegion().contains(Mockito.any(Position.class))).thenReturn(false);
        Mockito.when(farm.getInsideRegion().contains(new Position(9, 10))).thenReturn(true);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 10), demolishMarker.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), demolishMarker.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), demolishMarker.getPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), demolishMarker.getPosition());
    }
}
