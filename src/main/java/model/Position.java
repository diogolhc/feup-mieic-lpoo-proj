package model;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position getLeft() {
        return new Position(x - 1, y);
    }

    public Position getRight() {
        return new Position(x + 1, y);
    }

    public Position getUp() {
        return new Position(x, y - 1);
    }

    public Position getDown() {
        return new Position(x, y + 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getTranslated(Position position) {
        return new Position(this.getX() + position.getX(), this.getY() + position.getY());
    }

    public Position getRelativeTo(Position position) {
        return this.getTranslated(new Position(-position.getX(), -position.getY()));
    }

    public Position getRandomNeighbour() {
        int n = (int) (Math.random() * 4);
        switch (n) {
            case 0:
                return this.getDown();
            case 1:
                return this.getRight();
            case 2:
                return this.getUp();
            default:
                return this.getLeft();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position(" + this.x + ", " + this.y + ")";
    }
}
