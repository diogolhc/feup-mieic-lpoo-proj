package controller.farm.element.entity.keyboard_reactor;

import gui.GUI;
import model.Position;
import model.farm.Farm;
import model.farm.building.Building;
import model.farm.entity.Entity;
import model.region.RectangleRegion;
import model.region.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class NewBuildingControllerTest {
    private Farm farm;
    private Building newBuilding;
    private NewBuildingController controller;

    @BeforeEach
    public void setUp() {
        newBuilding = new Building(new Position(10, 10)) {
            @Override
            public int getWidth() {
                return 5;
            }

            @Override
            public int getHeight() {
                return 5;
            }

            @Override
            public Region getUntraversableRegion() {
                return null;
            }

            @Override
            public Region getInteractiveRegion() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }
        };
        farm = Mockito.mock(Farm.class);

        RectangleRegion region = Mockito.mock(RectangleRegion.class);
        Mockito.when(farm.getInsideRegion()).thenReturn(region);
        Mockito.when(region.contains(Mockito.any(RectangleRegion.class))).thenReturn(true);

        controller = new NewBuildingController(farm, newBuilding);
    }

    @Test
    public void moveUp() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 9), newBuilding.getTopLeftPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 8), newBuilding.getTopLeftPosition());
    }

    @Test
    public void moveDown() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 11), newBuilding.getTopLeftPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 12), newBuilding.getTopLeftPosition());
    }

    @Test
    public void moveLeft() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), newBuilding.getTopLeftPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(8, 10), newBuilding.getTopLeftPosition());
    }

    @Test
    public void moveRight() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(11, 10), newBuilding.getTopLeftPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(12, 10), newBuilding.getTopLeftPosition());
    }

    @Test
    public void noMove() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.QUIT);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verifyNoInteractions(farm);
        Assertions.assertEquals(new Position(10, 10), newBuilding.getTopLeftPosition());
    }

    @Test
    public void cantMove() {
        Mockito.when(farm.getInsideRegion().contains(Mockito.any(RectangleRegion.class))).thenReturn(false);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 10), newBuilding.getTopLeftPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), newBuilding.getTopLeftPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), newBuilding.getTopLeftPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(10, 10), newBuilding.getTopLeftPosition());
    }

    @Test
    public void canOnlyMoveLeft() {
        Mockito.when(farm.getInsideRegion().contains(Mockito.any(RectangleRegion.class))).thenReturn(false);
        Mockito.when(farm.getInsideRegion().contains(
                Mockito.eq(new RectangleRegion(new Position(9, 10), 5, 5)))).thenReturn(true);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 10), newBuilding.getTopLeftPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), newBuilding.getTopLeftPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), newBuilding.getTopLeftPosition());
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), newBuilding.getTopLeftPosition());
    }
}
