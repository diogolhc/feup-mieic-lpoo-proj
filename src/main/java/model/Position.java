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
        return new Position(this.x - 1, this.y);
    }

    public Position getRight() {
        return new Position(this.x + 1, this.y);
    }

    public Position getUp() {
        return new Position(this.x, this.y - 1);
    }

    public Position getDown() {
        return new Position(this.x, this.y + 1);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
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
        return this.x == position.x && this.y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Position(" + this.x + ", " + this.y + ")";
    }
}
