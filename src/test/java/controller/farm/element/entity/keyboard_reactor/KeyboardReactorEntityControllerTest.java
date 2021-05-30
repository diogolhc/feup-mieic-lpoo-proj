package controller.farm.element.entity.keyboard_reactor;

import gui.GUI;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.entity.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class KeyboardReactorEntityControllerTest {
    private KeyboardReactorEntityController entityController;
    private Entity entity;

    @BeforeEach
    public void setUp() {
        entity = new Entity(new Position(5, 5));
        entityController = new KeyboardReactorEntityController() {
            @Override
            protected Position getEntityPosition() {
                return entity.getPosition();
            }

            @Override
            protected void moveEntity(Position position) {
                entity.setPosition(position);
            }
        };
    }

    @Test
    public void moveUp() {
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(5, 4), entity.getPosition());
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(5, 3), entity.getPosition());
    }

    @Test
    public void moveDown() {
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(5, 6), entity.getPosition());
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(5, 7), entity.getPosition());
    }

    @Test
    public void moveLeft() {
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(4, 5), entity.getPosition());
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(3, 5), entity.getPosition());
    }

    @Test
    public void moveRight() {
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(6, 5), entity.getPosition());
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(7, 5), entity.getPosition());
    }

    @Test
    public void noMove() {
        entityController = new KeyboardReactorEntityController() {
            @Override
            protected Position getEntityPosition() {
                throw new RuntimeException("Entity should not be moved");
            }

            @Override
            protected void moveEntity(Position position) {
                throw new RuntimeException("Entity should not be moved");
            }
        };

        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.QUIT);
        entityController.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
    }
}
