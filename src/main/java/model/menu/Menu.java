package model.menu;

import gui.Color;
import model.Position;
import model.menu.label.Label;

import java.util.HashSet;
import java.util.Set;

public class Menu {
    private final Set<Button> buttons;
    private final Set<Label> labels;
    private String title;
    private Color color;
    private Position topLeft;
    private int width;
    private int height;

    public Menu() {
        this.buttons = new HashSet<>();
        this.labels = new HashSet<>();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return this.topLeft;
    }

    public void setTopLeftPosition(Position position) {
        this.topLeft = position;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
