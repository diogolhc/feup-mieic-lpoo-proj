package gui;

import java.io.Serializable;
import java.util.Objects;

public class Color implements Serializable {
    String color;
    public Color(String color) {
        // TODO check if color is valid
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
