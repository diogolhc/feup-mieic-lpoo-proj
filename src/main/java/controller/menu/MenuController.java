package controller.menu;

import controller.GameControllerState;
import controller.command.Command;
import gui.GUI;
import model.Position;
import model.menu.Button;
import model.menu.Menu;
import viewer.GameViewer;
import viewer.menu.MenuViewer;

import java.util.ArrayList;
import java.util.List;

public class MenuController implements GameControllerState {
    protected Menu menu;
    private List<ButtonController> buttonControllers;

    public MenuController(Menu menu) {
        this.menu = menu;
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
    public void reactKeyboard(GUI.ACTION action) {}

    @Override
    public void reactMouseMovement(Position position) {
        for (ButtonController buttonController: this.buttonControllers) {
            buttonController.reactMouseMovement(position.getRelativeTo(menu.getTopLeftPosition()));
        }
    }

    @Override
    public void reactMouseClick(Position position) {
        for (ButtonController buttonController: this.buttonControllers) {
            buttonController.reactMouseClick(position.getRelativeTo(menu.getTopLeftPosition()));
        }
    }

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {}

    @Override
    public GameViewer getViewer() {
        return new MenuViewer(this.menu);
    }
}
