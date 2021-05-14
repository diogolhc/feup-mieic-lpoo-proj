package controller.menu.builder;

import controller.menu.ButtonController;
import controller.menu.MenuController;
import model.Position;
import model.menu.Menu;
import model.menu.label.Label;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuControllerBuilder {
    public MenuController buildMenu(Position position) {
        Menu menu = new Menu(this.getTitle(), position, this.getWidth(), this.getHeight());
        for (Label label: this.getLabels()) {
            menu.addLabel(label);
        }
        MenuController menuController = this.getMenuController(menu);
        for (ButtonController buttonController: this.getButtons()) {
            menuController.addButton(buttonController);
        }
        return menuController;
    }

    protected MenuController getMenuController(Menu menu) {
        return new MenuController(menu);
    }

    protected List<ButtonController> getButtons() {
        return new ArrayList<>();
    }

    protected List<Label> getLabels() {
        return new ArrayList<>();
    }

    protected abstract int getHeight();

    protected abstract int getWidth();

    protected abstract String getTitle();
}
