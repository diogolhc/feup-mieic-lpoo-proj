package model.menu.label;

public class StringLabel implements LabelText {
    private final String text;

    public StringLabel(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
