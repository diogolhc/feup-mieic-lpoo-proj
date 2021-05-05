package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    private Position position;

    @BeforeEach
    void setUp() {
        position = new Position(4, 8);
    }

    @Test
    void getLeft() {
        Assertions.assertEquals(3, position.getLeft().getX());
        Assertions.assertEquals(8, position.getLeft().getY());
    }

    @Test
    void getRight() {
        Assertions.assertEquals(5, position.getRight().getX());
        Assertions.assertEquals(8, position.getRight().getY());
    }

    @Test
    void getUp() {
        Assertions.assertEquals(4, position.getUp().getX());
        Assertions.assertEquals(7, position.getUp().getY());
    }

    @Test
    void getDown() {
        Assertions.assertEquals(4, position.getDown().getX());
        Assertions.assertEquals(9, position.getDown().getY());
    }

    @Test
    void getTranslated() {
        Assertions.assertEquals(new Position(10, 10), position.getTranslated(new Position(6, 2)));
        Assertions.assertEquals(new Position(4, 8), position.getTranslated(new Position(0, 0)));
        Assertions.assertEquals(new Position(0, 0), position.getTranslated(new Position(-4, -8)));
    }

    @Test
    void getRelativeTo() {
        Assertions.assertEquals(position, position.getRelativeTo(new Position(0, 0)));
        Assertions.assertEquals(new Position(0, 0), position.getRelativeTo(position));
        Assertions.assertEquals(new Position(0, 3), position.getRelativeTo(new Position(4, 5)));
        Assertions.assertEquals(new Position(-1, -1), position.getRelativeTo(new Position(5, 9)));
    }
}
