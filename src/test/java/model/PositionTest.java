package model;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
        Assertions.assertEquals(new Position(0, 0), position.getTranslated(new Position(-4, -8)));
    }

    @Test
    void getRelativeTo() {
        Assertions.assertEquals(new Position(0, 3), position.getRelativeTo(new Position(4, 5)));
        Assertions.assertEquals(new Position(-1, -1), position.getRelativeTo(new Position(5, 9)));
    }

    @Property
    void getTranslatedZero(@ForAll int x, @ForAll int y) {
        position = new Position(x, y);
        Assertions.assertEquals(position, position.getTranslated(new Position(0, 0)));
    }

    @Property
    void getRelativeToItself(@ForAll int x, @ForAll int y) {
        position = new Position(x, y);
        Assertions.assertEquals(new Position(0, 0), position.getRelativeTo(position));
    }

    @Property
    void getRelativeToOrigin(@ForAll int x, @ForAll int y) {
        position = new Position(x, y);
        Assertions.assertEquals(position, position.getRelativeTo(new Position(0, 0)));
    }

    @Property
    void getLeftAndGetRightCancel(@ForAll int x, @ForAll int y, @ForAll List<@IntRange(max = 3) Integer> moves) {
        final int left = 0;
        final int right = 1;
        final int up = 2;
        final int down = 3;

        position = new Position(x, y);
        int numLeft = Collections.frequency(moves, left);
        int numRight = Collections.frequency(moves, right);
        int deltaRL = numRight - numLeft;

        for (int move: moves) {
            switch (move) {
                case left:
                    position = position.getLeft();
                    break;
                case right:
                    position = position.getRight();
                    break;
                case up:
                    position = position.getUp();
                    break;
                case down:
                    position = position.getDown();
                    break;
            }
        }

        Assertions.assertEquals(x + deltaRL, position.getX());
    }

    @Property
    void getUpAndGetDownCancel(@ForAll int x, @ForAll int y, @ForAll List<@IntRange(max = 3) Integer> moves) {
        final int left = 0;
        final int right = 1;
        final int up = 2;
        final int down = 3;

        position = new Position(x, y);
        int numUp = Collections.frequency(moves, up);
        int numDown = Collections.frequency(moves, down);
        int deltaUD = numDown - numUp;

        for (int move: moves) {
            switch (move) {
                case left:
                    position = position.getLeft();
                    break;
                case right:
                    position = position.getRight();
                    break;
                case up:
                    position = position.getUp();
                    break;
                case down:
                    position = position.getDown();
                    break;
            }
        }

        Assertions.assertEquals(y + deltaUD, position.getY());
    }
}
