package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HorizontalLineDrawerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(this.backgroundColors, this.foregroundColors, this.characters).mock();
    }


    @Test
    void drawAtOrigin() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");

        HorizontalLineDrawer drawer = new HorizontalLineDrawer(this.gui, BACK, FRONT, 'x');
        drawer.draw(new Position(0, 0), 5);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 && i <= 4) {
                    Assertions.assertEquals(BACK, this.backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT, this.foregroundColors[j][i]);
                    Assertions.assertEquals('x', this.characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, this.backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[j][i]);
                    Assertions.assertEquals(' ', this.characters[j][i]);
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

        HorizontalLineDrawer drawer = new HorizontalLineDrawer(this.gui, BACK, FRONT, 'x');
        drawer.draw(new Position(6, 3), 2);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 3 && i >= 6 && i <= 7) {
                    Assertions.assertEquals(BACK, this.backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT, this.foregroundColors[j][i]);
                    Assertions.assertEquals('x', this.characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, this.backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[j][i]);
                    Assertions.assertEquals(' ', this.characters[j][i]);
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

        HorizontalLineDrawer drawer = new HorizontalLineDrawer(this.gui, BACK, FRONT, 'x');
        drawer.draw(new Position(2, 3), 1);
        drawer.draw(new Position(4, 2), 6);
        drawer.draw(new Position(4, 7), 3);
        drawer.draw(new Position(4, 7), 6);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i >= 4 && i <= 9 && (j == 2 || j == 7)) || (i == 2 && j == 3)) {
                    Assertions.assertEquals(BACK, this.backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT, this.foregroundColors[j][i]);
                    Assertions.assertEquals('x', this.characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, this.backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[j][i]);
                    Assertions.assertEquals(' ', this.characters[j][i]);
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

        HorizontalLineDrawer drawer1 = new HorizontalLineDrawer(this.gui, BACK1, FRONT1, 'x');
        HorizontalLineDrawer drawer2 = new HorizontalLineDrawer(this.gui, BACK2, FRONT2, 'y');
        HorizontalLineDrawer drawer3 = new HorizontalLineDrawer(this.gui, BACK3, FRONT3, 'z');
        drawer1.draw(new Position(0, 0), 10);
        drawer2.draw(new Position(0, 1), 10);
        drawer3.draw(new Position(0, 2), 10);
        drawer1.draw(new Position(1, 3), 5);
        drawer1.draw(new Position(1, 4), 5);
        drawer2.draw(new Position(6, 3), 4);
        drawer2.draw(new Position(2, 4), 1);
        drawer3.draw(new Position(4, 4), 3);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 || (j == 3 && i >= 1 && i <= 5) || (j == 4 && (i == 1 || i == 3))) {
                    Assertions.assertEquals(BACK1, this.backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT1, this.foregroundColors[j][i]);
                    Assertions.assertEquals('x', this.characters[j][i]);
                } else if (j == 1 || (j == 3 && i >= 6) || (j == 4 && i == 2)) {
                    Assertions.assertEquals(BACK2, this.backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT2, this.foregroundColors[j][i]);
                    Assertions.assertEquals('y', this.characters[j][i]);
                } else if (j == 2 || (j == 4 && i >= 4 && i <= 6)) {
                    Assertions.assertEquals(BACK3, this.backgroundColors[j][i]);
                    Assertions.assertEquals(FRONT3, this.foregroundColors[j][i]);
                    Assertions.assertEquals('z', this.characters[j][i]);
                } else {
                    Assertions.assertEquals(BLACK, this.backgroundColors[j][i]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[j][i]);
                    Assertions.assertEquals(' ', this.characters[j][i]);
                }
            }
        }
    }
}
