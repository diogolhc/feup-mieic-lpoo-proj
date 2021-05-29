package gui;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color implements Serializable {
    public static final Color BLACK = new Color("#000000");
    public static final Color WHITE = new Color("#ffffff");
    public static final Color GRASS = new Color("#7ec850");
    public static final Color WOOD = new Color("#846f46");
    public static final Color HIGHLIGHTED_FLOOR = new Color("#be9b7b");

    private final String color;

    public Color(String color) {
        Pattern colorPattern = Pattern.compile("#([0-9a-fA-F]{6})");
        Matcher m = colorPattern.matcher(color);
        if (!m.matches()) {
            throw new IllegalArgumentException("Invalid color string");
        }

        this.color = color;
    }

    @Override
    public String toString() {
        return this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Color color1 = (Color) o;
        return Objects.equals(this.color, color1.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.color);
    }
}
