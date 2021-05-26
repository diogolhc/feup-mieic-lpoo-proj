package controller.menu.builder;

import controller.menu.ButtonController;
import controller.menu.MenuController;
import gui.Color;
import model.Position;
import model.menu.Menu;
import model.menu.label.Label;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuControllerBuilder {
    public MenuController buildMenu(Position position) {
        Menu menu = new Menu();
        menu.setTitle(this.getTitle());
        menu.setPosition(position);
        menu.setSize(this.getWidth(), this.getHeight());
        menu.setColor(this.getColor());

        for (Label label: this.getLabels()) {
            menu.addLabel(label);
        }

        MenuController menuController = this.getMenuController(menu);
        for (ButtonController buttonController: this.getButtons()) {
            menuController.addButton(buttonController);
        }

        return menuController;
    }

    public MenuController buildMenu() {
        return this.buildMenu(new Position(0, 0));
    }

    protected abstract MenuController getMenuController(Menu menu);

    protected List<ButtonController> getButtons() {
        return new ArrayList<>();
    }

    protected List<Label> getLabels() {
        return new ArrayList<>();
    }

    protected abstract int getHeight();

    protected abstract int getWidth();

    protected abstract String getTitle();

    protected Color getColor() {
        return new Color("#222222");
    }
}
