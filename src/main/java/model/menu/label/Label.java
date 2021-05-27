package model.menu.label;

import model.Position;

public class Label {
    private final Position topLeft;
    private final LabelText labelText;

    public Label(Position position, LabelText labelText) {
        this.topLeft = position;
        this.labelText = labelText;
    }

    public Label(Position position, String text) {
        this(position, new StringLabel(text));
    }

    public Position getTopLeft() {
        return this.topLeft;
    }

    public String getString() {
        return this.labelText.getText();
    }
}
