package model.menu.builder;

import model.Position;
import model.menu.Menu;

public abstract class MenuBuilder {
    public Menu buildMenu(Position position) {
        Menu menu = new Menu(this.getTitle(), position, this.getWidth(), this.getHeight());
        this.addButtonsAndLabels(menu);
        return menu;
    }

    protected abstract void addButtonsAndLabels(Menu menu);

    protected abstract int getHeight();

    protected abstract int getWidth();

    protected abstract String getTitle();
}
