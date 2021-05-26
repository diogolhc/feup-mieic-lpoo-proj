package model.menu;

import controller.command.Command;
import controller.command.NoOperationCommand;
import model.Position;

public class Button {
    private boolean selected;
    private final Position topLeft;
    private final String title;
    private final int width;
    private final int height;

    public Button(Position position, String title) {
        this(position, title, title.length() + 2);
    }

    public Button(Position position, String title, int width) {
        this.topLeft = position;
        this.title = title;
        this.width = width;
        this.height = 3;
        this.selected = false;
    }

    public boolean contains(Position position) {
        int x = position.getX();
        int y = position.getY();
        int buttonLeft = this.topLeft.getX();
        int buttonRight = buttonLeft + width - 1;
        int buttonTop = this.topLeft.getY();
        int buttonBottom = buttonTop + height - 1;
        return (x >= buttonLeft && x <= buttonRight && y >= buttonTop && y <= buttonBottom);
    }

    public void select() {
        this.selected = true;
    }

    public void unselect() {
        this.selected = false;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public Position getTopLeft() {
        return this.topLeft;
    }

    public String getTitle() {
        return this.title;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
