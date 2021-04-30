package model.menu;

import java.util.HashSet;
import java.util.Set;

public class Menu {
    private final Set<Button> buttons;
    private String title;

    // TODO MenuBuilder or even FluentFactory
    public Menu(String title) {
        this.title = title;
        this.buttons = new HashSet<>();
    }

    public String getTitle() {
        return this.title;
    }

    public Set<Button> getButtons() {
        return this.buttons;
    }

    public void addButton(Button button) {
        this.buttons.add(button);
    }
}
