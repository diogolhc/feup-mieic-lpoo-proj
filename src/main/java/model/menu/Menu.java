package model.menu;

import model.Position;
import model.menu.label.Label;

import java.util.HashSet;
import java.util.Set;

public class Menu {
    private final Set<Button> buttons;
    private final Set<Label> labels;
    private String title;
    private Position position;
    private int width;
    private int height;

    public Menu(String title, Position position, int width, int height) {
        this.title = title;
        this.position = position;
        this.width = width;
        this.height = height;
        this.buttons = new HashSet<>();
        this.labels = new HashSet<>();
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

    public Set<Label> getLabels() {
        return this.labels;
    }

    public void addLabel(Label label) {
        this.labels.add(label);
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
