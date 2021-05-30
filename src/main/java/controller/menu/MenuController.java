package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.element.ButtonController;
import gui.GUI;
import model.Position;
import model.menu.Button;
import model.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuController implements GameControllerState {
    protected final GameController gameController;
    private final Menu menu;
    private final List<ButtonController> buttonControllers;

    public MenuController(Menu menu, GameController gameController) {
        this.menu = menu;
        this.gameController = gameController;
        this.buttonControllers = new ArrayList<>();
    }

    public void addButton(ButtonController buttonController) {
        this.buttonControllers.add(buttonController);
        this.menu.addButton(buttonController.getButton());
    }

    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public void reactKeyboard(GUI.KEYBOARD_ACTION action) {}

    @Override
    public void reactMouseMovement(Position position) {
        for (ButtonController buttonController: this.buttonControllers) {
            buttonController.reactMouseMovement(position.getRelativeTo(menu.getTopLeftPosition()));
        }
    }

    @Override
    public void reactMouseClick(Position position) {
        for (ButtonController buttonController: this.buttonControllers) {
            Position relative = position.getRelativeTo(menu.getTopLeftPosition());
            buttonController.reactMouseClick(position.getRelativeTo(menu.getTopLeftPosition()));
        }
    }

    @Override
    public void reactChangeState() {
        for (Button button: this.getMenu().getButtons()) {
            button.unselect();
        }
    }
}
