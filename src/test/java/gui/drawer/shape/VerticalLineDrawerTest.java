package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class VerticalLineDrawerTest {
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

        VerticalLineDrawer drawer = new VerticalLineDrawer(gui, BACK, FRONT, 'x');
        drawer.draw(new Position(0, 0), 5);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 && i <= 4) {
                    Assertions.assertEquals(BACK, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, foregroundColors[i][j]);
                    Assertions.assertEquals('x', characters[i][j]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[i][j]);
                    Assertions.assertEquals(WHITE, foregroundColors[i][j]);
                    Assertions.assertEquals(' ', characters[i][j]);
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

        VerticalLineDrawer drawer = new VerticalLineDrawer(gui, BACK, FRONT, 'x');
        drawer.draw(new Position(6, 3), 2);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 6 && i >= 3 && i <= 4) {
                    Assertions.assertEquals(BACK, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, foregroundColors[i][j]);
                    Assertions.assertEquals('x', characters[i][j]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[i][j]);
                    Assertions.assertEquals(WHITE, foregroundColors[i][j]);
                    Assertions.assertEquals(' ', characters[i][j]);
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
        Color BACK2 = new Color("#123457");
        Color FRONT2 = new Color("#FEDCBB");
        Color BACK3 = new Color("#123458");
        Color FRONT3 = new Color("#FEDCBC");

        VerticalLineDrawer drawer = new VerticalLineDrawer(gui, BACK, FRONT, 'x');
        VerticalLineDrawer drawer2 = new VerticalLineDrawer(gui, BACK2, FRONT2, 'y');
        VerticalLineDrawer drawer3 = new VerticalLineDrawer(gui, BACK3, FRONT3, 'z');
        drawer.draw(new Position(6, 3), 5);
        drawer2.draw(new Position(7, 2), 4);
        drawer3.draw(new Position(6, 6), 3);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 6 && i >= 3 && i <= 5) {
                    Assertions.assertEquals(BACK, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, foregroundColors[i][j]);
                    Assertions.assertEquals('x', characters[i][j]);
                } else if (j == 7 && i >= 2 && i <= 5) {
                    Assertions.assertEquals(BACK2, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT2, foregroundColors[i][j]);
                    Assertions.assertEquals('y', characters[i][j]);
                } else if (j == 6 && i >= 6 && i <= 8) {
                    Assertions.assertEquals(BACK3, backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT3, foregroundColors[i][j]);
                    Assertions.assertEquals('z', characters[i][j]);
                } else {
                    Assertions.assertEquals(BLACK, backgroundColors[i][j]);
                    Assertions.assertEquals(WHITE, foregroundColors[i][j]);
                    Assertions.assertEquals(' ', characters[i][j]);
                }
            }
        }
    }
}
