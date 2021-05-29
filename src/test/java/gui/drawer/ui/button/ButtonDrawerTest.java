package gui.drawer.ui.button;

import gui.Color;
import gui.GUI;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ButtonDrawerTest {
/*
    private GUI gui;
    private Color backgroundColors[][] = new Color[10][10];
    private Color foregroundColors[][] = new Color[10][10];
    private char characters[][] = new char[10][10];

    @BeforeEach
    void setUp() {
        this.gui = new GUIMockTestHelper(backgroundColors, foregroundColors, characters).mock();
    }

    @Test
    void drawButton() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK = new Color("#123456");
        Color FRONT = new Color("#FEDCBA");
        
        ButtonDrawer buttonDrawer = new ButtonDrawer(gui, "Hello", 7, 7, BACK, FRONT);
        buttonDrawer.draw(new Position(0, 0));

        char HORIZONTAL_LINE = '-';
        char VERTICAL_LINE = '|';
        char CORNER_LINE = '+';

        Color expectedBg[][] = {
                {BACK, BACK, BACK, BACK, BACK, BACK, BACK, BLACK, BLACK, BLACK},
                {BACK, BACK, BACK, BACK, BACK, BACK, BACK, BLACK, BLACK, BLACK},
                {BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK, BLACK, BLACK},
                {BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK, BLACK, BLACK},
                {BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK, BLACK, BLACK},
                {BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK, BLACK, BLACK},
                {BACK, BACK, BACK, BACK, BACK, BACK, BACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK}
        };

        Color expectedFg[][] = {
                {FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, WHITE, WHITE, WHITE},
                {FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE},
                {FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE, WHITE, WHITE},
                {FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE}
        };

        char expectedChars[][] = {
                {CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' '},
                {VERTICAL_LINE, 'H', 'e', 'l', 'l', 'o', VERTICAL_LINE, ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' ', ' ', ' '},
                {CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' ', ' ', ' '},
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
    void drawButtonAtPosition() {
        Color BLACK = Color.BLACK;
        Color WHITE = Color.WHITE;
        Color BACK = new Color("#90a4ae");
        Color FRONT = new Color("#263238");

        ButtonDrawer buttonDrawer = new ButtonDrawer(gui, "Hello", 7, 7, BACK, FRONT);
        buttonDrawer.draw(new Position(2, 2));

        char HORIZONTAL_LINE = '-';
        char VERTICAL_LINE = '|';
        char CORNER_LINE = '+';

        Color expectedBg[][] = {
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK},
                {BLACK, BLACK, BACK, BACK, BACK, BACK, BACK, BACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BACK, BACK, BACK, BACK, BACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BLACK, BLACK, BLACK, BLACK, BLACK, BACK, BLACK},
                {BLACK, BLACK, BACK, BACK, BACK, BACK, BACK, BACK, BACK, BLACK},
                {BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK}
        };

        Color expectedFg[][] = {
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE},
                {WHITE, WHITE, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, WHITE},
                {WHITE, WHITE, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, WHITE},
                {WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE},
                {WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE},
                {WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE},
                {WHITE, WHITE, FRONT, WHITE, WHITE, WHITE, WHITE, WHITE, FRONT, WHITE},
                {WHITE, WHITE, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, FRONT, WHITE},
                {WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE}
        };

        char expectedChars[][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' '},
                {' ', ' ', VERTICAL_LINE, 'H', 'e', 'l', 'l', 'o', VERTICAL_LINE, ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' '},
                {' ', ' ', VERTICAL_LINE, ' ', ' ', ' ', ' ', ' ', VERTICAL_LINE, ' '},
                {' ', ' ', CORNER_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, HORIZONTAL_LINE, CORNER_LINE, ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

        for (int i = 0; i < 10; i++) {
            Assertions.assertArrayEquals(expectedBg[i], this.backgroundColors[i]);
            Assertions.assertArrayEquals(expectedChars[i], this.characters[i]);
            Assertions.assertArrayEquals(expectedFg[i], this.foregroundColors[i]);
        }
    }
*/
}
