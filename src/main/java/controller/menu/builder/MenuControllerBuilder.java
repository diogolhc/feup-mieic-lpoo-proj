package controller.menu.builder;

import controller.menu.element.ButtonController;
import controller.menu.MenuController;
import gui.Color;
import model.Position;
import model.menu.Menu;
import model.menu.label.Label;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuControllerBuilder {
    public final static Color DEFAULT_MENU_COLOR = new Color("#222222");

    public MenuController buildMenu(Position position) {
        Menu menu = new Menu();
        menu.setTitle(this.getTitle());
        menu.setTopLeftPosition(position);
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

    public MenuController buildMenuCentered(int windowWidth, int windowHeight) {
        Position position = new Position(
                (windowWidth - this.getWidth())/2,
                (windowHeight - this.getHeight())/2
        );
        return this.buildMenu(position);
    }

    protected abstract String getTitle();

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract MenuController getMenuController(Menu menu);

    protected List<ButtonController> getButtons() {
        return new ArrayList<>();
    }

    protected List<Label> getLabels() {
        return new ArrayList<>();
    }

    protected Color getColor() {
        return DEFAULT_MENU_COLOR;
    }
}
