package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FilledRectangleDrawerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(backgroundColors, foregroundColors, characters).mock();
    }


    @Test
    void drawAtOrigin() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");

        FilledRectangleDrawer drawer = new FilledRectangleDrawer(gui, BACK, FRONT, '+');
        drawer.draw(new Position(0, 0), 5, 6);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i <= 4 && j <= 5) {
                    Assertions.assertEquals(BACK, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT, foregroundColors[j][i]);
                    Assertions.assertEquals('+', characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, foregroundColors[j][i]);
                    Assertions.assertEquals(' ', characters[j][i]);
                }
            }
        }
    }

    @Test
    void drawOtherPosition() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");

        FilledRectangleDrawer drawer = new FilledRectangleDrawer(gui, BACK, FRONT, '+');
        drawer.draw(new Position(3, 3), 2, 2);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i >= 3 && i <= 4 && j >= 3 && j <= 4) {
                    Assertions.assertEquals(BACK, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT, foregroundColors[j][i]);
                    Assertions.assertEquals('+', characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, foregroundColors[j][i]);
                    Assertions.assertEquals(' ', characters[j][i]);
                }
            }
        }
    }

    @Test
    void drawMultiple() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");

        FilledRectangleDrawer drawer = new FilledRectangleDrawer(gui, BACK, FRONT, '+');
        drawer.draw(new Position(2, 0), 2, 10);
        drawer.draw(new Position(1, 3), 6, 3);
        drawer.draw(new Position(5, 3), 2, 2);
        drawer.draw(new Position(7, 6), 1, 1);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i >= 2 && i <= 3) || (i >= 1 && i <= 6 && j >= 3 && j <= 5) || (i == 7 && j == 6)) {
                    Assertions.assertEquals(BACK, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT, foregroundColors[j][i]);
                    Assertions.assertEquals('+', characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, foregroundColors[j][i]);
                    Assertions.assertEquals(' ', characters[j][i]);
                }
            }
        }
    }

    @Test
    void drawDifferent() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK1 = new Color("#123456");
        Color FRONT1 = new Color("#FEDCBA");
        Color BACK2 = new Color("#123457");
        Color FRONT2 = new Color("#FEDCBB");
        Color BACK3 = new Color("#123458");
        Color FRONT3 = new Color("#FEDCBC");

        FilledRectangleDrawer drawer1 = new FilledRectangleDrawer(gui, BACK1, FRONT1, '+');
        FilledRectangleDrawer drawer2 = new FilledRectangleDrawer(gui, BACK2, FRONT2, 'x');
        FilledRectangleDrawer drawer3 = new FilledRectangleDrawer(gui, BACK3, FRONT3, ':');
        drawer1.draw(new Position(2, 0), 2, 10);
        drawer2.draw(new Position(5, 3), 2, 2);
        drawer3.draw(new Position(1, 3), 6, 3);
        drawer2.draw(new Position(7, 6), 1, 1);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i >= 2 && i <= 3 && (j <= 2 || j >= 6)) {
                    Assertions.assertEquals(BACK1, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT1, foregroundColors[j][i]);
                    Assertions.assertEquals('+', characters[j][i]);
                } else if (i == 7 && j == 6) {
                    Assertions.assertEquals(BACK2, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT2, foregroundColors[j][i]);
                    Assertions.assertEquals('x', characters[j][i]);
                } else if (i >= 1 && i <= 6 && j >= 3 && j <= 5) {
                    Assertions.assertEquals(BACK3, backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT3, foregroundColors[j][i]);
                    Assertions.assertEquals(':', characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, foregroundColors[j][i]);
                    Assertions.assertEquals(' ', characters[j][i]);
                }
            }
        }
    }
}
