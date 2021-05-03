package model.menu;

import model.Position;

import java.util.HashSet;
import java.util.Set;

public class Menu {
    private final Set<Button> buttons;
    private String title;
    private Position position;
    private int width;
    private int height;

    // TODO MenuBuilder or even FluentFactory
    public Menu(String title, Position position, int width, int height) {
        this.title = title;
        this.position = position;
        this.width = width;
        this.height = height;
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

    public Position getTopLeftPosition() {
        return this.position;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
