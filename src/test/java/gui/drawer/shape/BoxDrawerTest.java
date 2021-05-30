package gui.drawer.shape;

import gui.Color;
import gui.GUI;
import gui.GUIMockTestHelper;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoxDrawerTest {
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(this.backgroundColors, this.foregroundColors, this.characters).mock();
    }

    @Test
    void drawBox() {
        Color DEFAULT = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");

        char HORIZONTAL_LINE = '-';
        char VERTICAL_LINE = '|';
        char CORNER_LINE = '+';
    
        BoxDrawer boxDrawer = new BoxDrawer(this.gui, BACK, FRONT);
        boxDrawer.draw(new Position(0, 0), 5, 5);

        Color expectedBg[][] = {
                {BACK, BACK, BACK, BACK, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, DEFAULT, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, DEFAULT, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, DEFAULT, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, BACK, BACK, BACK, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT}
        };

        Color expectedFg[][] = {
                {FRONT, FRONT, FRONT, FRONT, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, FRONT, FRONT, FRONT, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE}
        };

        char expectedChars[][] = {
                {CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' '},
                {CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
        }
    }

    @Test
    void drawMultipleBoxes() {
        Color DEFAULT = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK = new Color("#123456");
        Color BACK2 = new Color("#144778");
        Color FRONT = new Color("#FEDCBA");
        Color FRONT2 = new Color("#FEFBAD");

        char HORIZONTAL_LINE = '-';
        char VERTICAL_LINE = '|';
        char CORNER_LINE = '+';

        BoxDrawer boxDrawer = new BoxDrawer(this.gui, BACK, FRONT);
        BoxDrawer boxDrawer2 = new BoxDrawer(this.gui, BACK2, FRONT2);

        boxDrawer.draw(new Position(0, 0), 5, 5);
        boxDrawer2.draw(new Position(3, 3), 4, 4);
        boxDrawer.draw(new Position(2, 2), 1, 1);

        Color expectedBg[][] = {
                {BACK, BACK, BACK, BACK, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, DEFAULT, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, BACK, DEFAULT, BACK, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {BACK, DEFAULT, DEFAULT, BACK2, BACK2, BACK2, BACK2, DEFAULT, DEFAULT, DEFAULT},
                {BACK, BACK, BACK, BACK2, BACK, DEFAULT, BACK2, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, BACK2, DEFAULT, DEFAULT, BACK2, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, BACK2, BACK2, BACK2, BACK2, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT},
                {DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT}
        };

        Color expectedFg[][] = {
                {FRONT, FRONT, FRONT, FRONT, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, FRONT, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, FRONT2, FRONT2, FRONT2, FRONT2, WHITE, WHITE, WHITE},
                {FRONT, FRONT, FRONT, FRONT2, FRONT, WHITE, FRONT2, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, FRONT2, WHITE, WHITE, FRONT2, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, FRONT2, FRONT2, FRONT2, FRONT2, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE}
        };

        char expectedChars[][] = {
                {CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', CORNER_LINE, ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' '},
                {CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, VERTICAL_LINE, CORNER_LINE, ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {' ', ' ', ' ', CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
        }
    }

}
