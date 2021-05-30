package controller.farm.element.entity.keyboard_reactor;

import gui.GUI;
import model.Position;
import model.farm.Farm;
import model.farm.building.Building;
import model.region.RectangleRegion;
import model.region.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NewBuildingControllerTest {
    private Farm farm;
    private Building newBuilding;
    private NewBuildingController controller;

    @BeforeEach
    public void setUp() {
        this.newBuilding = new Building(new Position(10, 10)) {
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
        this.farm = Mockito.mock(Farm.class);

        RectangleRegion region = Mockito.mock(RectangleRegion.class);
        Mockito.when(this.farm.getInsideRegion()).thenReturn(region);
        Mockito.when(region.contains(Mockito.any(RectangleRegion.class))).thenReturn(true);

        this.controller = new NewBuildingController(this.farm, this.newBuilding);
    }

    @Test
    public void moveUp() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 9), this.newBuilding.getTopLeftPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 8), this.newBuilding.getTopLeftPosition());
    }

    @Test
    public void moveDown() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 11), this.newBuilding.getTopLeftPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 12), this.newBuilding.getTopLeftPosition());
    }

    @Test
    public void moveLeft() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), this.newBuilding.getTopLeftPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(8, 10), this.newBuilding.getTopLeftPosition());
    }

    @Test
    public void moveRight() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(11, 10), this.newBuilding.getTopLeftPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(12, 10), this.newBuilding.getTopLeftPosition());
    }

    @Test
    public void noMove() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.QUIT);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verifyNoInteractions(this.farm);
        Assertions.assertEquals(new Position(10, 10), this.newBuilding.getTopLeftPosition());
    }

    @Test
    public void cantMove() {
        Mockito.when(this.farm.getInsideRegion().contains(Mockito.any(RectangleRegion.class))).thenReturn(false);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 10), this.newBuilding.getTopLeftPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), this.newBuilding.getTopLeftPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), this.newBuilding.getTopLeftPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(10, 10), this.newBuilding.getTopLeftPosition());
    }

    @Test
    public void canOnlyMoveLeft() {
        Mockito.when(this.farm.getInsideRegion().contains(Mockito.any(RectangleRegion.class))).thenReturn(false);
        Mockito.when(this.farm.getInsideRegion().contains(
                Mockito.eq(new RectangleRegion(new Position(9, 10), 5, 5)))).thenReturn(true);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(10, 10), this.newBuilding.getTopLeftPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(10, 10), this.newBuilding.getTopLeftPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(10, 10), this.newBuilding.getTopLeftPosition());
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(9, 10), this.newBuilding.getTopLeftPosition());
    }
}
