package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import gui.GUI;
import model.Position;
import model.menu.Button;
import model.menu.Menu;
import viewer.GameViewer;
import viewer.menu.MenuViewer;

public class MenuController implements GameControllerState {
    private Menu menu;
    private GameController controller;

    public MenuController(Menu menu, GameController controller) {
        this.menu = menu;
        this.controller = controller;
    }

    protected Menu getMenu() {
        return this.menu;
    }

    @Override
    public void reactKeyboard(GUI.ACTION action) {
        // TODO
    }

    @Override
    public void reactMouseMovement(Position position) {
        ButtonController buttonController = new ButtonController();
        for (Button button: this.menu.getButtons()) {
            buttonController.reactMouseMovement(button, position.relativeTo(menu.getTopLeftPosition()));
        }
    }

    @Override
    public void reactMouseClick(Position position) {
        ButtonController buttonController = new ButtonController();
        for (Button button: this.menu.getButtons()) {
            buttonController.reactMouseClick(button, position.relativeTo(menu.getTopLeftPosition()));
        }
    }

    @Override
    public void reactTimePassed() {

    }

    @Override
    public GameViewer getViewer() {
        return new MenuViewer(this.menu);
    }
}
