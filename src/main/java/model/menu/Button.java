package model.menu;

import model.Position;
import model.region.RectangleRegion;

public class Button {
    private boolean selected;
    private final Position topLeft;
    private final String title;
    private final int width;
    public static final int BUTTON_HEIGHT = 3;

    public Button(Position position, String title) {
        this(position, title, title.length() + 2);
    }

    public Button(Position position, String title, int width) {
        this.topLeft = position;
        this.title = title;
        this.width = width;
        this.selected = false;
    }

    public boolean contains(Position position) {
        return new RectangleRegion(this.topLeft, this.width, BUTTON_HEIGHT).contains(position);
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
        return BUTTON_HEIGHT;
    }
}
