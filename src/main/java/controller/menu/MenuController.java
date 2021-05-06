package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import gui.GUI;
import model.Position;
import model.farm.building.Building;
import model.menu.Button;
import model.menu.Menu;
import model.menu.builder.HarvestMenuBuilder;
import viewer.GameViewer;
import viewer.menu.MenuViewer;

public class MenuController implements GameControllerState {
    private Menu menu;

    public MenuController(Menu menu) {
        this.menu = menu;
    }

    protected Menu getMenu() {
        return this.menu;
    }

    @Override
    public void reactKeyboard(GUI.ACTION action) {}

    @Override
    public void reactMouseMovement(Position position) {
        ButtonController buttonController = new ButtonController();
        for (Button button: this.menu.getButtons()) {
            buttonController.reactMouseMovement(button, position.getRelativeTo(menu.getTopLeftPosition()));
        }
    }

    @Override
    public void reactMouseClick(Position position) {
        ButtonController buttonController = new ButtonController();
        for (Button button: this.menu.getButtons()) {
            buttonController.reactMouseClick(button, position.getRelativeTo(menu.getTopLeftPosition()));
        }
    }

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {}

    @Override
    public GameViewer getViewer() {
        return new MenuViewer(this.menu);
    }
}
