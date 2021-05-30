package controller.farm.element.entity.keyboard_reactor;

import gui.GUI;
import model.Position;

public abstract class KeyboardReactorEntityController {
    public void reactKeyboard(GUI.KEYBOARD_ACTION action) {
        if (action == GUI.KEYBOARD_ACTION.MOVE_UP) moveUp();
        if (action == GUI.KEYBOARD_ACTION.MOVE_RIGHT) moveRight();
        if (action == GUI.KEYBOARD_ACTION.MOVE_DOWN) moveDown();
        if (action == GUI.KEYBOARD_ACTION.MOVE_LEFT) moveLeft();
    }

    private void moveLeft() {
        this.moveEntity(this.getEntityPosition().getLeft());
    }

    private void moveRight() {
        this.moveEntity(this.getEntityPosition().getRight());
    }

    private void moveUp() {
        this.moveEntity(this.getEntityPosition().getUp());
    }

    private void moveDown() {
        this.moveEntity(this.getEntityPosition().getDown());
    }

    protected abstract Position getEntityPosition();
    protected abstract void moveEntity(Position position);
}
