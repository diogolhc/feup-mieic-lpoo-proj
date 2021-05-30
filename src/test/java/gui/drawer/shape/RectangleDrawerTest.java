package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RectangleDrawerTest {

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

        RectangleDrawer drawer = new RectangleDrawer(this.gui, BACK, FRONT, 'x');
        drawer.draw(new Position(0, 0), 5, 5);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 0 || i == 4) && j <= 4) {
                    Assertions.assertEquals(BACK, this.backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, this.foregroundColors[i][j]);
                    Assertions.assertEquals('x', this.characters[i][j]);
                } else if ( i <= 4 && (j == 0 || j == 4) ) {
                    Assertions.assertEquals(BACK, this.backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, this.foregroundColors[i][j]);
                    Assertions.assertEquals('x', this.characters[i][j]);
                } else {
                    Assertions.assertEquals(BLACK, this.backgroundColors[i][j]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[i][j]);
                    Assertions.assertEquals(' ', this.characters[i][j]);
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

        RectangleDrawer drawer = new RectangleDrawer(this.gui, BACK, FRONT, 'x');
        drawer.draw(new Position(3, 3), 5, 3);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 3 || i == 5) && (j >= 3 && j <= 7)) {
                    Assertions.assertEquals(BACK, this.backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, this.foregroundColors[i][j]);
                    Assertions.assertEquals('x', this.characters[i][j]);
                } else if ( (i >= 3 && i <= 5) && (j == 3 || j == 7) ) {
                    Assertions.assertEquals(BACK, this.backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, this.foregroundColors[i][j]);
                    Assertions.assertEquals('x', this.characters[i][j]);
                } else {
                    Assertions.assertEquals(BLACK, this.backgroundColors[i][j]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[i][j]);
                    Assertions.assertEquals(' ', this.characters[i][j]);
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


        RectangleDrawer drawer = new RectangleDrawer(this.gui, BACK, FRONT, 'x');
        RectangleDrawer drawer2 = new RectangleDrawer(this.gui, BACK2, FRONT2, 'y');
        drawer.draw(new Position(6, 3), 3, 3);
        drawer2.draw(new Position(5, 2), 5, 2);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 2 || i == 3) && (j >= 5 && j <= 9)) {
                    Assertions.assertEquals(BACK2, this.backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT2, this.foregroundColors[i][j]);
                    Assertions.assertEquals('y', this.characters[i][j]);
                } else if ((i >= 2 && i <= 3) && (j == 5 || j == 9)) {
                    Assertions.assertEquals(BACK2, this.backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT2, this.foregroundColors[i][j]);
                    Assertions.assertEquals('y', this.characters[i][j]);
                } else if ((i == 3 || i == 5) && (j >= 6 && j <= 8)) {
                    Assertions.assertEquals(BACK, this.backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, this.foregroundColors[i][j]);
                    Assertions.assertEquals('x', this.characters[i][j]);
                } else if ( (i >= 3 && i <= 5) && (j == 6 || j == 8) ) {
                    Assertions.assertEquals(BACK, this.backgroundColors[i][j]);
                    Assertions.assertEquals(FRONT, this.foregroundColors[i][j]);
                    Assertions.assertEquals('x', this.characters[i][j]);
                } else {
                    Assertions.assertEquals(BLACK, this.backgroundColors[i][j]);
                    Assertions.assertEquals(WHITE, this.foregroundColors[i][j]);
                    Assertions.assertEquals(' ', this.characters[i][j]);
                }
            }
        }
    }
}
