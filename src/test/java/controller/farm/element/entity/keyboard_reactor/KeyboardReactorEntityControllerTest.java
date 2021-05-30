package controller.farm.element.entity.keyboard_reactor;

import gui.GUI;
import model.Position;
import model.farm.entity.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KeyboardReactorEntityControllerTest {
    private KeyboardReactorEntityController entityController;
    private Entity entity;

    @BeforeEach
    public void setUp() {
        this.entity = new Entity(new Position(5, 5));
        this.entityController = new KeyboardReactorEntityController() {
            @Override
            protected Position getEntityPosition() {
                return KeyboardReactorEntityControllerTest.this.entity.getPosition();
            }

            @Override
            protected void moveEntity(Position position) {
                KeyboardReactorEntityControllerTest.this.entity.setPosition(position);
            }
        };
    }

    @Test
    public void moveUp() {
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(5, 4), this.entity.getPosition());
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_UP);
        Assertions.assertEquals(new Position(5, 3), this.entity.getPosition());
    }

    @Test
    public void moveDown() {
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(5, 6), this.entity.getPosition());
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_DOWN);
        Assertions.assertEquals(new Position(5, 7), this.entity.getPosition());
    }

    @Test
    public void moveLeft() {
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(4, 5), this.entity.getPosition());
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Assertions.assertEquals(new Position(3, 5), this.entity.getPosition());
    }

    @Test
    public void moveRight() {
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(6, 5), this.entity.getPosition());
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Assertions.assertEquals(new Position(7, 5), this.entity.getPosition());
    }

    @Test
    public void noMove() {
        this.entityController = new KeyboardReactorEntityController() {
            @Override
            protected Position getEntityPosition() {
                throw new RuntimeException("Entity should not be moved");
            }

            @Override
            protected void moveEntity(Position position) {
                throw new RuntimeException("Entity should not be moved");
            }
        };

        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.QUIT);
        this.entityController.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
    }
}
